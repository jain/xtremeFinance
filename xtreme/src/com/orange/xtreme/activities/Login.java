package com.orange.xtreme.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.orange.xtreme.R;
import com.orange.xtreme.support.AsteriskPasswordTransformationMethod;
import com.orange.xtreme.support.Global;
import com.orange.xtreme.support.SQLHelper;
import com.orange.xtreme.presenters.LoginViewPresenter;
import com.orange.xtreme.views.LoginView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
public class Login extends Activity implements LoginView{
private LoginViewPresenter presenter;
private AutoCompleteTextView username;
private EditText password;
private List<String> usr;
private Map<String, String> map;
private boolean remember;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		presenter = new LoginViewPresenter(this);
		username = (AutoCompleteTextView)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
		remember = false;
		genSavedUsers();
	}

	public void savedUsers(){
		usr = new ArrayList<String>();
		map = presenter.getSavedUsers();
		for(Entry<String, String> it:map.entrySet()){
			usr.add(it.getKey());
		}
		SQLHelper dbe = new SQLHelper(getBaseContext());
		SQLiteDatabase db = dbe.getReadableDatabase();
		String query = "Select * from users";
		Cursor c = db.rawQuery(query, null);
		while(c.moveToNext()){
			usr.add(c.getString(c.getColumnIndex("username")));
			map.put(c.getString(c.getColumnIndex("username")), c.getString(c.getColumnIndex("password")));
		}
	}
	public void genSavedUsers(){
		savedUsers();
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, R.layout.adapter, usr);
		username.setAdapter(ad);
		username.setOnItemClickListener(new OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
	                long id) {
	              //Toast.makeText(check.this," selected", Toast.LENGTH_LONG).show();
	        		password.setText(map.get(username.getText().toString()));
	        }
	    });
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username.getText().toString();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password.getText().toString();
	}
	@Override
	public void onBackPressed(){
		back();
	}

	@Override
	public void invalid() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Your login credentials are incorrect!!!");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				username.setText("");
				password.setText("");
			}
		});
		alert.show();
	}

	@Override
	public void advance() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Accounts.class);
		finish();
		startActivity(intent);
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Welcome.class);
		finish();
		startActivity(intent);
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,Register.class);
		finish();
		startActivity(intent);
	}
	public void onClickRegister(View v){
		presenter.onClickRegister();
	}
	public void onClickLogin(View v){
		presenter.onClickLogin();
	}
	public void onClickBack(View v){
		presenter.onClickBack();
	}
	public void onCheckboxClicked(View v){
		remember = !remember;
	}
	@Override
	public boolean getRemember(){
		return remember;
	}
	/*public boolean checkDatabase(){
		String uname = username.getText().toString();
		String psw = password.getText().toString();
		if(map.containsKey(uname)){
			if(map.get(uname).equals(psw)){
				return true;
			}
		}
		return false;
	}*/
	/*public void rem(){
		String uname = username.getText().toString();
		String psw = password.getText().toString();
		if(!map.containsKey(username.getText().toString())){
			SQLHelper dbe = new SQLHelper(getBaseContext());
			SQLiteDatabase db = dbe.getWritableDatabase();
			dbe.onCreate(db);
			ContentValues values = new ContentValues();
			values.put("username", uname);
			values.put("password", psw);
			db.insert("users", null, values);
			List<String> accounts = presenter.getAccountNames(uname, psw);
			for(String tmp: accounts){
				values = new ContentValues();
				values.put("username", uname);
				values.put("account", tmp);
				db.insert("accounts", null, values);
			}
			Global.setIDatabase(true);
		}
	}*/
}
