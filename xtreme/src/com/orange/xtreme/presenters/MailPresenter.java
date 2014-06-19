package com.orange.xtreme.presenters;

import com.orange.xtreme.model.Database;
import com.orange.xtreme.views.MailView;

public class MailPresenter{
	private MailView view;
	public MailPresenter(MailView v){
		view = v;
	}
	public void send(){
		String uname = view.getUsername();
		String email = view.getEmail();
		String password = Database.mail(uname, email);
		if(password == null){
			view.invalid();
			return;
		}
		sendMail(email, password);
		view.send();
	}
	// implement sending mail with the email and password
	public void sendMail(String email, String password){
		
	}
}
