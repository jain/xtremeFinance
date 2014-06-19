package com.orange.xtreme.presenters;


import com.orange.xtreme.views.WelcomeView;

public class WelcomeViewPresenter{
	private WelcomeView view;
	public WelcomeViewPresenter(WelcomeView v){
		view = v;
	}
	public void onClickRegister(){
		view.advanceRegister();
	}
	public void onClickLogin(){
		view.advanceLogin();
	}
	public void onClickExit(){
		view.exit();
	}
	public void onClickPlayTetris(){
		view.playTetris();
	}
	public void onClickPlayForgotPassword(){
		view.forgotPassword();
	}
}
