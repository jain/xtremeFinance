package com.orange.xtreme.presenters;

import java.util.List;

import com.orange.xtreme.activities.GenReport;
import com.orange.xtreme.model.Model2;

public class GenReportPresenter {
	private ReportsView view;
	private Model2 model;
	public GenReportPresenter(ReportsView v){
		view = v;
		model = new Model2();
	}
	public List<List<String>> getTransactions(String account){
		return model.getHistory(account, view.getBaseContext());
	}
	public List<List<String>> getAccounts(){
		return model.getAccounts(view.getBaseContext());
	}
}