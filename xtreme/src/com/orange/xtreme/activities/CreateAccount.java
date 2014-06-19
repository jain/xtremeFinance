package com.orange.xtreme.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.orange.xtreme.R;
import com.orange.xtreme.model.Database;
import com.orange.xtreme.presenters.CreateAccountPresenter;
import com.orange.xtreme.support.Global;
import com.orange.xtreme.views.CreateAccountView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class CreateAccount extends Activity implements CreateAccountView{
	private Map<String, EditText> map;
	private CreateAccountPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount);
		map = new HashMap<String, EditText>();
		map.put("name", (EditText)findViewById(R.id.accountname));
		map.put("bal1", (EditText)findViewById(R.id.initialbalance));
		map.put("bal2", (EditText)findViewById(R.id.decBalance));
		presenter = new CreateAccountPresenter(this);
	}
	public void onClickCreate(View v){
	int bal;
		try{
			int b1 = Integer.parseInt(map.get("bal1").getText().toString());
			int b2 = Integer.parseInt(map.get("bal2").getText().toString());
			if(b2>=100){
				balanceFailure();
				return;
			}
			bal = b1*100 + b2;
		}catch(Exception e){
			balanceFailure();
			return;
		}
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		String username = Global.getUsername();
		String password = Global.getPassword();
		boolean success = presenter.createAccount(username, password, map.get("name").getText().toString(), ""+ bal, timeStamp);
		Database.login(username, password);
		//boolean success = Database.addAccount(username, password, map.get("name").getText().toString(), ""+ bal, timeStamp);
		Database.logout();
		if(success){
			cancel();
		}else{
			failure();
		}
	}
	public void balanceFailure(){
		
	}
	public void failure(){
		
	}
	public void cancel(){
		Intent intent = new Intent(this, Accounts.class);
		finish();
		startActivity(intent);
	}
	public void onClickCancel(View v){
		cancel();
	}
}
