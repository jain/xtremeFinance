package com.orange.xtreme.presenters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import android.annotation.SuppressLint;
import com.orange.xtreme.model.Database;
import com.orange.xtreme.model.Model;
import com.orange.xtreme.model.ModelInterface;
import com.orange.xtreme.support.CheckEmailValid;
import com.orange.xtreme.views.RegisterView;

@SuppressLint("SimpleDateFormat")
public class RegisterViewPresenter {
	private RegisterView view;
	private ModelInterface model;
	public RegisterViewPresenter(RegisterView v){
		view = v;
		model = new Model();
	}
	public void onClickRegister(){
		view.register();
	}
	public void onClickCancel(){
		view.cancel();
	}
	public void onClickCreate(){
		if(!view.getPassword().equals(view.getPassword2())){
			view.passwordMismatch();
			return;
		}
		CheckEmailValid cev = new CheckEmailValid(view.getEmail());
		if(!cev.isValid()){
			view.invalidEmail();
			return;
		}
		addNewUser();
	}
	public void addNewUser(){;
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	boolean cond = model.addNewUser(view.getUsername(), view.getPassword(), timeStamp, view.getEmail(), view.getHint(), view.getAnswer());
	//boolean cond = Database.addUser(view.getUsername(), view.getPassword(), timeStamp, view.getEmail());
	//Database.logout();
	
	if(!cond){
		view.userExists();
		return;
	}
	view.register();
	}
}
