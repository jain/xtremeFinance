package com.orange.xtreme.support;

import android.app.Application;

public class Global extends Application {

    private static String username;
    private static String password;
    private static boolean inDatabase = false;
	public static String getUsername() {
		return username;
	}
	public static void setIDatabase(boolean val){
		inDatabase = val;
	}
	public static boolean getIDatabase(){
		return inDatabase;
	}
	public static void setUsername(String username) {
		Global.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		Global.password = password;
	}
}