package com.orange.xtreme.activities;

import com.orange.xtreme.R;
import com.orange.xtreme.presenters.MailPresenter;
import com.orange.xtreme.views.MailView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class MailActivity extends Activity implements MailView{
private MailPresenter presenter;
private EditText uname;
private EditText email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mail);
		presenter = new MailPresenter(this);
		email = (EditText)findViewById(R.id.email);
		uname = (EditText)findViewById(R.id.username);
	}
	public void onClickSend(View v){
		presenter.send();
	}
	public void onClickBack(View v){
		back();
	}
	public void onBackPressed(){
		back();
	}
	public void back(){
		Intent intent = new Intent(this, Welcome.class);
		finish();
		startActivity(intent);
	}
	public void send(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("An email with your password has been sent to your email!!!");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				back();
			}
		});
		alert.show();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return uname.getText().toString();
	}
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email.getText().toString();
	}
	@Override
	public void invalid() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Your username and email id do not match!!!");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				uname.setText("");
				email.setText("");
			}
		});
		alert.show();
	}

}
