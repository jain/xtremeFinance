package com.orange.xtreme.model;

import java.util.List;
import java.util.Map;

import android.content.Context;

public interface ModelInterface {
	boolean login(String username, String password, Context context);
	boolean addMe(String username, String password, Context context);
	Map<String, String> getSavedUsers(Context context);
	boolean addNewUser(String username, String password, String time, String mail, String hint, String answer);
	List<String> generateAccountNames (String username, String password, Context context);
	boolean addNewAccount(String username, String password, String name, String balance, String time, Context context);
}
