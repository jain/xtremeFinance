package com.orange.xtreme.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.orange.xtreme.support.Global;
import com.orange.xtreme.support.SQLHelper;

public class Model implements ModelInterface{

	@Override
	public boolean login(String username, String password, Context context) {
		// TODO Auto-generated method stub
		SQLHelper dbe = new SQLHelper(context);
		SQLiteDatabase db = dbe.getReadableDatabase();
		String query = "Select * from users WHERE username = '" + username +"'";
		Cursor c = db.rawQuery(query, null);
		if(c.moveToNext()){
			if(c.getString(c.getColumnIndex("password")).equals(password)){
				Global.setUsername(username);
				Global.setPassword(password);
				Global.setIDatabase(true);
				return true;
			}else{
				return false;
			}
		}
		boolean poss = Database.login(username, password);
		if(poss){
			Global.setUsername(username);
			Global.setPassword(password);
		}
		Database.logout();
		return poss;
	}

	@Override
	public boolean addMe(String username, String password, Context context) {
		// TODO Auto-generated method stub
		SQLHelper dbe = new SQLHelper(context);
		SQLiteDatabase db = dbe.getReadableDatabase();
		String query = "Select * from users WHERE username = '" + username +"'";
		Cursor c = db.rawQuery(query, null);
		if(c.moveToNext()){
			return login(username, password, context);
		}
		boolean poss = Database.login(username, password);
		if(!poss){
			Database.logout();
			return false;
		}
		db = dbe.getWritableDatabase();
		dbe.onCreate(db);
		ContentValues values = new ContentValues();
		Database.logout();
		List<List<String>> list = Database.getUsers();
		Database.login(username, password);
		List<String> user = new ArrayList<String>();
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).get(0).equals(username)){
				user = list.get(i);
			}
		}
		values.put("username", username);
		values.put("password", password);
		values.put("email", user.get(2));
		values.put("date", user.get(3));
		values.put("hint", user.get(4));
		values.put("answer", user.get(5));
		db.insert("users", null, values);
		Global.setUsername(username);
		Global.setPassword(password);
		List<String> accounts = getAccountNames(username, password);
		List<String> tran = new ArrayList<String>();
		for(String tmp: accounts){
			list = Database.getTransactions(username, password, tmp);
			for(int i = 0; i<list.size(); i++){
				tran = list.get(i);
				values = new ContentValues();
				values.put("username", username);
				values.put("account", tmp);
				values.put("change", tran.get(0));
				values.put("balance", tran.get(1));
				values.put("date", tran.get(2));
				values.put("gps", tran.get(3));
				db.insert("accounts", null, values);
			}
		}
		Global.setIDatabase(true);
		Database.logout();
		return true;
	}
	public List<String> getAccountNames(String username, String password){
		List<String> names = new ArrayList<String>();
		Database.login(username, password);
		List<List<String>> list = Database.getAccounts(username, password);
		Database.logout();
		for(List<String> accounts : list){
			names.add(accounts.get(0));
		}
		Collections.sort(names);
		return names;
	}
	public Map<String, String> getSavedUsers(Context context){
		Map<String, String> map = new HashMap<String, String>();
		SQLHelper dbe = new SQLHelper(context);
		SQLiteDatabase db = dbe.getReadableDatabase();
		String query = "Select * from users";
		Cursor c = db.rawQuery(query, null);
		while(c.moveToNext()){
			map.put(c.getString(c.getColumnIndex("username")), c.getString(c.getColumnIndex("password")));
		}
		return map;
	}
	public boolean addNewUser(String username, String password, String time, String mail, String hint, String answer){
		boolean cond = Database.addUser(username, password, time, mail, hint, answer);
		Database.logout();
		Global.setUsername(username);
		Global.setPassword(password);
		return cond;
	}
	public List<String> generateAccountNames (String username, String password, Context context){
		List<String> names = new ArrayList<String>();
		if(Global.getIDatabase()){
			SQLHelper dbe = new SQLHelper(context);
			SQLiteDatabase db = dbe.getReadableDatabase();
			String query = "Select * from accounts";
			Cursor c = db.rawQuery(query, null);
			while(c.moveToNext()){
				String name = c.getString(c.getColumnIndex("account"));
				if(!names.contains(name)){
					names.add(name);
				}
			}
			Collections.sort(names);
			return names;
		}
		Database.login(username, password);
		List<List<String>> list = Database.getAccounts(username, password);
		Database.logout();
		for(List<String> accounts : list){
			names.add(accounts.get(0));
		}
		Collections.sort(names);
		return names;
	}
	public boolean addNewAccount(final String username, final String password, final String name, final String balance, final String time, Context context){
		final String cood = "";
		if(!Global.getIDatabase()){
			Database.login(username, password);
			boolean success = Database.addAccount(username, password, name, balance, time, cood);
			Database.logout();
			return success;
		}else{
			SQLHelper dbe = new SQLHelper(context);
			SQLiteDatabase db = dbe.getReadableDatabase();
			String query = "Select * from accounts WHERE username = '" + username +"' AND account = '" + name + "'";
			Cursor c = db.rawQuery(query, null);
			if(c.moveToNext()){
				return false;
			}else{
				db = dbe.getWritableDatabase();
				ContentValues values = new ContentValues();
				values = new ContentValues();
				values.put("username", username);
				values.put("account", name);
				values.put("change", ""+0);
				values.put("balance", balance);
				values.put("date", time);
				values.put("gps", cood);
				db.insert("accounts", null, values);
				Thread thread = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Database.login(username, password);
						Database.addAccount(username, password, name, balance, time, cood);
						Database.logout();
					}
					
				});
				thread.start();
				return true;
			}
		}
	}
}
