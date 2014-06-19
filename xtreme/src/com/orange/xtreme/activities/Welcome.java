package com.orange.xtreme.activities;
import com.orange.xtreme.R;
import com.orange.xtreme.presenters.WelcomeViewPresenter;
import com.orange.xtreme.support.SQLHelper;
import com.orange.xtreme.views.WelcomeView;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

//import com.orange.xtreme.DefaultUser.Node;

public class Welcome extends Activity implements WelcomeView{
	private WelcomeViewPresenter presenter;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		presenter = new WelcomeViewPresenter(this);
		/*SQLHelper dbe = new SQLHelper(getBaseContext());
		SQLiteDatabase db = dbe.getReadableDatabase();
		dbe.clear(db);*/
		SQLHelper dbe = new SQLHelper(getBaseContext());
		SQLiteDatabase db = dbe.getReadableDatabase();
		dbe.onCreate(db);
		db.execSQL ("drop table accounts");
		db.execSQL ("drop table users");
		dbe.onCreate(db);
	}
	@Override
	public void onBackPressed(){
		finish();
	}
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		finish();
	}
	@Override
	public void advanceLogin() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Login.class);
		finish();
		startActivity(intent);
	}
	@Override
	public void advanceRegister() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Register.class);
		finish();
		startActivity(intent);		
	}
	@Override
	public void playTetris() {
		// implement tetris
	}
	@Override
	public void forgotPassword() {
		Intent intent = new Intent(this, MailActivity.class);
		finish();
		startActivity(intent);
	}
	public void onClickRegister(View v){
		presenter.onClickRegister();
	}
	public void onClickLogin(View v){
		presenter.onClickLogin();
	}
	public void onClickExit(View v){
		presenter.onClickExit();
	}
	public void onClickPlayTetris(View v){
		presenter.onClickPlayTetris();
	}
	public void onClickForgotPassword(View v){
		presenter.onClickPlayForgotPassword();
	}
}
