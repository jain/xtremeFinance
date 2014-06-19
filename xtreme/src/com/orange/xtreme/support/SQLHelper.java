package com.orange.xtreme.support;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "fgtDB";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE1 =
		    "CREATE TABLE if not exists users (username VARCHAR, "
		    + "password VARCHAR, email VARCHAR, date VARCHAR, hint VARCHAAR, answer VARCHAR, " +
		    "PRIMARY KEY (username));";
	private static final String DATABASE_CREATE2 =
			"CREATE TABLE if not exists accounts (username VARCHAR,"
				    + "account VARCHAR, change VARCHAR, balance VARCHAR, date VARCHAR, " +
				    "gps VARCHAR);";
	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
        try
        {
            db.execSQL(DATABASE_CREATE1);
            db.execSQL(DATABASE_CREATE2);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void clear(SQLiteDatabase db){
		db.execSQL("delete from users");
		db.execSQL("delete from accounts");
	}

}