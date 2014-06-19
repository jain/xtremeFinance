package com.orange.xtreme.activities;

import com.orange.xtreme.R;
import com.orange.xtreme.presenters.RegisterViewPresenter;
import com.orange.xtreme.support.AsteriskPasswordTransformationMethod;
import com.orange.xtreme.views.RegisterView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity implements RegisterView{
	private RegisterViewPresenter presenter;
	private EditText username;
	private EditText password;
	private EditText password2;
	private EditText email;
	private EditText hint;
	private EditText answer;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		presenter = new RegisterViewPresenter(this);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		password2 = (EditText)findViewById(R.id.password2);
		email = (EditText)findViewById(R.id.email);
		hint = (EditText)findViewById(R.id.hint);
		answer = (EditText)findViewById(R.id.password);
		password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
		password2.setTransformationMethod(new AsteriskPasswordTransformationMethod());
	}
	@Override
	public String getUsername(){
		return username.getText().toString();
	}
	@Override
	public String getPassword(){
		return password.getText().toString();
	}
	@Override
	public String getPassword2(){
		return password2.getText().toString();
	}
	@Override
	public String getEmail(){
		return email.getText().toString();
	}
	@Override
	public String getHint(){
		return hint.getText().toString();
	}
	@Override
	public String getAnswer(){
		return answer.getText().toString();
	}
	@Override
	public void onBackPressed(){
		cancel();
	}
	@Override
	public void cancel(){
		Intent intent = new Intent(this,Welcome.class);
		finish();
		startActivity(intent);
	}
	@Override
	public void register(){
		Intent intent = new Intent(this,Accounts.class);
		finish();
		startActivity(intent);
	}
	@Override
	public void passwordMismatch(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Your passwords do not match!!!");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				password.setText("");
				password2.setText("");
			}
		});
		alert.show();
	}
	@Override
	public void invalidEmail(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Warning your Email is invalid!!!");
		alert.setPositiveButton("Change Email Id", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				email.setText("");
			}
		})
		.setNegativeButton("Continue Anyway", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				presenter.addNewUser();
			}
		});
		alert.show();
	}
	@Override
	public void userExists(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("This username has been taken!!!");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				username.setText("");
			}
		});
		alert.show();
	}
	public void onClickCancel(View v){
		presenter.onClickCancel();
	}

	public void onClickCreate(View v){
		presenter.onClickCreate();
	}
}
