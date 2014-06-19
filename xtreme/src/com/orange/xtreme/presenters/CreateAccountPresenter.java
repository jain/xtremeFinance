package com.orange.xtreme.presenters;

import android.content.Context;
import android.view.View;

import com.orange.xtreme.model.Model;
import com.orange.xtreme.model.ModelInterface;
import com.orange.xtreme.views.CreateAccountView;

public class CreateAccountPresenter {
	private CreateAccountView view;
	private ModelInterface model;
	public CreateAccountPresenter(CreateAccountView v){
		view = v;
		model = new Model();
	}
	public boolean createAccount(String username, String password, String name, String balance, String time){
		return model.addNewAccount(username, password, name, balance, time, view.getBaseContext());
	}
}
