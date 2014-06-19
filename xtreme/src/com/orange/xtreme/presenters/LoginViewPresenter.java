package com.orange.xtreme.presenters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.orange.xtreme.model.Database;
import com.orange.xtreme.model.Model;
import com.orange.xtreme.model.ModelInterface;
import com.orange.xtreme.views.LoginView;
import com.orange.xtreme.support.Global;
import com.orange.xtreme.support.SQLHelper;
public class LoginViewPresenter{
	private LoginView view;
	private ModelInterface model;
	public LoginViewPresenter(LoginView v){
		view = v;
		model = new Model();
	}
	public void onClickRegister(){
		view.register();
	}
	public void onClickLogin(){
		/*if(view.checkDatabase()){
			Global.setIDatabase(true);
			view.advance();
			return;
		}*/
		if(!view.getRemember()){
			if(model.login(view.getUsername(), view.getPassword(), view.getBaseContext())){
				view.advance();
			}else{
				view.invalid();
			}
		}else{
			if(model.addMe(view.getUsername(), view.getPassword(), view.getBaseContext())){
				view.advance();
			}else{
				view.invalid();
			}
		}
	}
	public void onClickBack(){
		view.back();
	}
	public Map<String, String> getSavedUsers(){
		return model.getSavedUsers(view.getBaseContext());
	}
}
