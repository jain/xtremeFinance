package com.orange.xtreme.activities;

import java.util.List;

import com.orange.xtreme.R;
import com.orange.xtreme.model.Database;
import com.orange.xtreme.presenters.HistoryPresenter;
import com.orange.xtreme.support.Global;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
public class History extends Activity {
private String account;
private HistoryPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		account = getIntent().getExtras().getString("name");
		setContentView(R.layout.history);
		presenter = new HistoryPresenter(this);
		List<List<String>> transHistory = presenter.getHistory(account);
		TextView txt = (TextView) findViewById(R.id.init);
			txt.setText(account);
			LinearLayout date = (LinearLayout) findViewById(R.id.amount);
			LinearLayout amount = (LinearLayout) findViewById(R.id.date);
			int i = 0;
			String b;
	        String d;
			TextView dat, amt;
			while(i<transHistory.size()){
				dat = new TextView(this);
				amt = new TextView(this);
				b = transHistory.get(i).get(0);
				d = transHistory.get(i).get(2);
				if(b.length()>1){
					b = new StringBuilder(b).insert(b.length()-2, ".").toString();
				}else{
					b = transHistory.get(i).get(1);
					b = new StringBuilder(b).insert(b.length()-2, ".").toString();
				}
				dat.setText(b);
				dat.setTextSize(25);	
				amt.setText(d.substring(0,10));
				amt.setTextSize(25);
				amount.addView(amt);
				date.addView(dat);
				i++;
				amt.setGravity(Gravity.TOP);
				dat.setGravity(Gravity.TOP);
			}
		}
	public void onClickBack(View v){
		Intent intent = new Intent(this, Accounts.class);
		finish();
		startActivity(intent);
	}
}
