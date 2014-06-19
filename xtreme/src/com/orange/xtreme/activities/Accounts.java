package com.orange.xtreme.activities;


import java.util.List;

import com.orange.xtreme.R;
import com.orange.xtreme.presenters.AccountsViewPresenter;
import com.orange.xtreme.support.Global;
import com.orange.xtreme.views.AccountsView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Accounts extends Activity implements AccountsView{
	private AccountsViewPresenter presenter;
	private String username;
	private String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//username = savedInstanceState.getString("username");
		//password = savedInstanceState.getString("password");
		username = Global.getUsername();
		password = Global.getPassword();
		presenter = new AccountsViewPresenter(this);
		setContentView(R.layout.accountsii);
		generateScreen();
	}

	public void generateScreen(){
		List<String> names = presenter.getAccountNames(username, password);
		LinearLayout layout = (LinearLayout) findViewById(R.id.accountArray);
		TextView account = new TextView(this);
		Point size = new Point();
		Display display = ((android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		display.getSize(size);
		Button temp;
		if(!(names.size()>0)){
			return;
		}
		account.setBackgroundColor(Color.BLACK);
		account.setHeight(2);
		account.setWidth(size.x);
		layout.addView(account);
		for(String name:names){
			temp = new Button(this);
			temp.setText(name);
			temp.setGravity(Gravity.CENTER);
			temp.setOnClickListener(AccountClickListener);
			layout.addView(temp);
			account = new TextView(this);
			account.setWidth(size.x);
			account.setBackgroundColor(Color.BLACK);
			account.setHeight(2);
			layout.addView(account);
		}
	}
	public void createAccount(){
		Intent intent = new Intent(this, CreateAccount.class);
		finish();
		startActivity(intent);
	}
	public void onClickCreate(View v){
		presenter.onClickCreate();
	}
	public void onClickDelete(View v){
		//presenter.onClickDelete();
		Intent intent = new Intent(this,DeleteAccounts.class);
		finish();
		startActivity(intent);
	}
	public void onClickGenReport(View v){
		//presenter.onClickGenReport();
		Intent intent = new Intent(this,GenReport.class);
		finish();
		startActivity(intent);
	}
	public void onClickLogout(View v){
		presenter.onClickLogout();
	}
	public void genAccount(String name){
		Intent intent = new Intent(this,TransactionActivity.class);
		intent.putExtra("name", name);
		finish();
		startActivity(intent);
	}
	@Override
	public void exit(){
		finish();
	}
	@Override
	public void onBackPressed(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Warning you shall be logged out!!!");
		alert.setPositiveButton("Pressed by Mistake", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		})
		.setNegativeButton("Log out", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				presenter.logout();
			}
		});
		alert.show();
	}
	OnClickListener AccountClickListener = new OnClickListener() {	 
		public void onClick(View v) {
			genAccount(((Button)v).getText().toString());
		}
	};
}
