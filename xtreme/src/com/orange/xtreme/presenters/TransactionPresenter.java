package com.orange.xtreme.presenters;

import java.util.List;

import com.orange.xtreme.activities.TransactionActivity;
import com.orange.xtreme.model.Database;
import com.orange.xtreme.model.Model2;
import com.orange.xtreme.support.Global;

public class TransactionPresenter {
	private TransactionActivity view;
	private Model2 model;
	public TransactionPresenter(TransactionActivity v){
		view = v;
		model = new Model2();
	}
	public String getBalance(String account){
		return model.getBalance(account, view.getBaseContext());
	}
	public void addTransaction(String account,String bali, String bal, String timeStamp){
		model.addTransaction(account, bali, bal, timeStamp, view.getBaseContext());
	}
}
