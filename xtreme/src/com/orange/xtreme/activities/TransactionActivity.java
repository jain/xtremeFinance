package com.orange.xtreme.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orange.xtreme.R;
import com.orange.xtreme.model.Database;
import com.orange.xtreme.presenters.TransactionPresenter;
import com.orange.xtreme.support.Global;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class TransactionActivity extends Activity {
private String account;
private int bal;
private Map<String, EditText> map;
private TransactionPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginsuccess);
		presenter = new TransactionPresenter(this);
		account = getIntent().getExtras().getString("name");
		TextView txt = (TextView) findViewById(R.id.name);
		txt.setText(account);
		txt = (TextView) findViewById(R.id.balance);
		String li = presenter.getBalance(account);
		bal = Integer.parseInt(li);
		String bal1 = ""+ bal/100;
		String bal2 = "" + bal%100;
		if(bal2.length()==0){
			bal2 = "00";
		}else if(bal2.length()==1){
			bal2 += "0";
		}
		String balan = "Current Balance: $" + bal1 + "." + bal2;
		txt.setText(balan);	
		map = new HashMap<String, EditText>();
		map.put("bal1", (EditText)findViewById(R.id.trans1));
		map.put("bal2", (EditText)findViewById(R.id.trans2));
	}
	public void onClickBack(View v){
		accountScreen();
	}
	public void onClickHistory(View v){
		Intent intent = new Intent(this, History.class);
		intent.putExtra("name", account);
		finish();
		startActivity(intent);
	}
	public void onClickWithdraw(View v){
		int bali;
		try{
			int b1 = Integer.parseInt(map.get("bal1").getText().toString());
			int b2 = Integer.parseInt(map.get("bal2").getText().toString());
			if(b2>=100){
				failure();
				return;
			}
			bali = b1*100 + b2;
			bal = bal - bali;
			if(bal<0){
				failure();
				return;
			}
		}catch(Exception e){
			failure();
			return;
		}
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		presenter.addTransaction(account, "-"+bali, "" + bal, timeStamp);
		accountScreen();
	}
	public void onClickDeposit(View v){
		int bali;
		try{
			int b1 = Integer.parseInt(map.get("bal1").getText().toString());
			int b2 = Integer.parseInt(map.get("bal2").getText().toString());
			if(b2>=100){
				failure();
				return;
			}
			bali = b1*100 + b2;
			bal = bal + bali;
		}catch(Exception e){
			failure();
			return;
		}
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		presenter.addTransaction(account, "+"+bali, "" + bal, timeStamp);
		accountScreen();
	}
	public void failure(){
		
	}
	public void accountScreen(){
		Intent intent = new Intent(this, Accounts.class);
		finish();
		startActivity(intent);
	}
}
