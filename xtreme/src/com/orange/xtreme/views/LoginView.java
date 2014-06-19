package com.orange.xtreme.views;

import android.content.Context;

public interface LoginView {
	String getUsername();
	String getPassword();
	void invalid();
	void advance();
	void back();
	void register();
	boolean getRemember();
	Context getBaseContext();
}
