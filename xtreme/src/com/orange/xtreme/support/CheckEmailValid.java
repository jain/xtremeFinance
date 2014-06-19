package com.orange.xtreme.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CheckEmailValid{
	private String email;
	public CheckEmailValid(String email){
		this.email = email.trim();
	}
	public boolean isValid(){
		return (checkRegex()&&checkLink());
	}
	public boolean checkRegex(){
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
	public boolean checkLink(){
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
}
