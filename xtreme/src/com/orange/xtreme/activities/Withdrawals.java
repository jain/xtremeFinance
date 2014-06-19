package com.orange.xtreme.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orange.xtreme.R;
import com.orange.xtreme.activities.Accounts;
import com.orange.xtreme.presenters.GenReportPresenter;
import com.orange.xtreme.presenters.ReportsView;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Withdrawals extends Activity implements ReportsView{
	private GenReportPresenter presenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.genreport);
		presenter = new GenReportPresenter(this);
	}
	public void onClickReturn(View v){
		Intent intent = new Intent(this, Accounts.class);
		finish();
		startActivity(intent);
	}
	public void onClickGenerate(View v){
		String dat[] = new String[6];
		dat[0] = ((EditText) findViewById(R.id.date)).getText().toString();
		dat[1] = ((EditText) findViewById(R.id.date2)).getText().toString();
		dat[2] = ((EditText) findViewById(R.id.date3)).getText().toString();
		dat[3] = ((EditText) findViewById(R.id.date4)).getText().toString();
		dat[4] = ((EditText) findViewById(R.id.date5)).getText().toString();
		dat[5] = ((EditText) findViewById(R.id.date6)).getText().toString();
		for(int i = 0; i<6; i++){
			if(dat[i].equals("")) {
				return;
			}
		}
		int date[] = new int[6];
		Date start;
		Date end;
		try{
			for(int i =0; i<6; i++){
				date[i] = Integer.parseInt(dat[i]);
				if(date[i]<0){return;}
			}
			if(date[0]>12||date[3]>12||date[1]>31||date[4]>31)
			{return;}
			start = new Date(date[2], date[0], date[1]);
			end = new Date(date[5], date[3], date[4]);
			if(start.after(end)){return;}
		}catch(NumberFormatException e){return;}	
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> nameList = new ArrayList<String>();	
		List<List<String>> tmp = presenter.getAccounts();
		for (List<String> i : tmp) {
			List<List<String>> trans = presenter.getTransactions(i.get(0));
			for (List<String> l : trans) {
				String temp = l.get(2);
				String change = l.get(0);
				temp = temp.substring(0, 10);
				String[] dateBits = temp.split("[.]");
				Date curDate = new Date(Integer.parseInt(dateBits[0]), Integer.parseInt(dateBits[1]), Integer.parseInt(dateBits[2]));
				if (curDate.compareTo(start) >= 0 && curDate.compareTo(end) <= 0&&change.contains("-")) {
					list.add(l);
					nameList.add(i.get(0));
				}
			}	
		}
		setContentView(R.layout.report);
		TextView txt = (TextView) findViewById(R.id.init);
		LinearLayout l1 = (LinearLayout) findViewById(R.id.date);
		LinearLayout l2 = (LinearLayout) findViewById(R.id.amount);
		LinearLayout l3 = (LinearLayout) findViewById(R.id.account);
		int i = 0;
		String b;
		String name;
		TextView dat1, amt, nam;
		while(i<list.size()){
			dat1 = new TextView(this);
			amt = new TextView(this);
			nam = new TextView(this);
			b = list.get(i).get(0);
			name = nameList.get(i);
			dat1.setText(list.get(i).get(2).substring(0, 10));
			dat1.setTextSize(18);
			amt.setText("$"+ b);
			amt.setTextSize(18);
			nam.setText(name);
			nam.setTextSize(18);
			l2.addView(amt);
			l1.addView(dat1);
			l3.addView(nam);
			amt.setGravity(Gravity.TOP);
			dat1.setGravity(Gravity.TOP);
			nam.setGravity(Gravity.TOP);
			i++;
		}
	}
}
