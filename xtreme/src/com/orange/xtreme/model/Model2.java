package com.orange.xtreme.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orange.xtreme.support.Global;
import com.orange.xtreme.support.SQLHelper;

public class Model2 {
	public String getBalance(String account, Context context){
		if(!Global.getIDatabase()){
			Database.login(Global.getUsername(), Global.getPassword());
			List<List<String>> list = Database.getAccounts(Global.getUsername(), Global.getPassword());
			List<String> li = null;
			for(List i : list){
				if(i.get(0).equals(account)){
					li = i;
					break;
				}
			}
			return li.get(2);
		}
		SQLHelper dbe = new SQLHelper(context);
		SQLiteDatabase db = dbe.getReadableDatabase();
		String query = "Select * from accounts WHERE username = '" + Global.getUsername() +"' AND account = '" + account + "'";
		Cursor c = db.rawQuery(query, null);
		String bal = "0";
		while(c.moveToNext()){
			bal = c.getString(c.getColumnIndex("balance"));
		}
		return bal;
	}
	public void addTransaction(final String account, final String bali, final String bal, final String timeStamp, Context context){
		final String username = Global.getUsername();
		final String password = Global.getPassword();
		final String cood = "";
		if(!Global.getIDatabase()){
			Database.login(username, password);
			Database.addTransaction(username, password, account, bali, bal, timeStamp, cood);
			Database.logout();
			return;
		}
		SQLHelper dbe = new SQLHelper(context);
		SQLiteDatabase db = dbe.getWritableDatabase();
		ContentValues values = new ContentValues();
		values = new ContentValues();
		values.put("username", username);
		values.put("account", account);
		values.put("change", bali);
		values.put("balance", bal);
		values.put("date", timeStamp);
		values.put("gps", cood);
		db.insert("accounts", null, values);
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Database.login(username, password);
				Database.addTransaction(username, password, account, bali, bal, timeStamp, cood);
				Database.logout();
			}

		});
		thread.start();
	}
	public List<List<String>> getHistory(String account, Context context){
		List<List<String>> transHistory = new ArrayList<List<String>>();
		String username = Global.getUsername();
		String password = Global.getPassword();
		if(!Global.getIDatabase()){
			Database.login(username, password);
			transHistory = Database.getTransactions(username, password, account);
			Database.logout();
			return transHistory;
		}
		SQLHelper dbe = new SQLHelper(context);
		SQLiteDatabase db = dbe.getReadableDatabase();
		String query = "Select * from accounts WHERE username = '" + username +"' AND account = '" + account + "'";
		Cursor c = db.rawQuery(query, null);
		List<String> tmp;
		while(c.moveToNext()){
			tmp = new ArrayList<String>();
			tmp.add(c.getString(c.getColumnIndex("change")));
			tmp.add(c.getString(c.getColumnIndex("balance")));
			tmp.add(c.getString(c.getColumnIndex("date")));
			tmp.add(c.getString(c.getColumnIndex("gps")));
			transHistory.add(tmp);
		}
		return transHistory;
	}
	public List<List<String>> getAccounts(Context context){
		String username = Global.getUsername();
		String password = Global.getPassword();
		List<List<String>> accounts = new ArrayList<List<String>>();
		List<String> names;
		if(Global.getIDatabase()){
			SQLHelper dbe = new SQLHelper(context);
			SQLiteDatabase db = dbe.getReadableDatabase();
			String query = "Select * from accounts WHERE username = '" + Global.getUsername() +"'";
			Cursor c = db.rawQuery(query, null);
			while(c.moveToNext()){
				names = new ArrayList<String>();
				names.add(c.getString(c.getColumnIndex("account")));
				names.add(c.getString(c.getColumnIndex("date")));
				names.add(c.getString(c.getColumnIndex("balance")));
				names.add(c.getString(c.getColumnIndex("date")));
			}
			return accounts;
		}
		Database.login(username, password);
		List<List<String>> list = Database.getAccounts(username, password);
		Database.logout();
		return list;
	}
}
