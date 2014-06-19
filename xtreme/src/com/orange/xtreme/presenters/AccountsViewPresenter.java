package com.orange.xtreme.presenters;


import java.util.List;


import com.orange.xtreme.model.Database;
import com.orange.xtreme.model.Model;
import com.orange.xtreme.model.ModelInterface;
import com.orange.xtreme.views.AccountsView;

public class AccountsViewPresenter {
	private AccountsView view;
	private ModelInterface model;
	public AccountsViewPresenter(AccountsView view){
		this.view = view;
		model = new Model();
	}
	public void onClickCreate(){
		view.createAccount();
	}
	public void onClickDelete(){
		
	}
	public void onClickGenReport(){
		
	}
	public void onClickLogout(){
		Database.logout();
		view.exit();
	}
	public void logout(){
		//Database.logout();
		view.exit();
	}
	public List<String> getAccountNames(String username, String password){
		return model.generateAccountNames(username, password, view.getBaseContext());
	}
}
