package com.orange.xtreme.views;

import android.content.Context;


public interface RegisterView{
	String getUsername();
	String getPassword();
	String getPassword2();
	String getHint();
	String getAnswer();
	String getEmail();
	void cancel();
	void register();
	void passwordMismatch();
	void invalidEmail();
	void userExists();
	Context getBaseContext();
}
