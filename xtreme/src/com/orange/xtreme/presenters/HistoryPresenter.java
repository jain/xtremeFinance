package com.orange.xtreme.presenters;

import java.util.List;

import com.orange.xtreme.activities.History;
import com.orange.xtreme.model.Database;
import com.orange.xtreme.model.Model2;
import com.orange.xtreme.support.Global;

public class HistoryPresenter {
	private History view;
	private Model2 model;
	public HistoryPresenter(History v){
		view = v;
		model = new Model2();
	}
	public List<List<String>> getHistory(String account){
		return model.getHistory(account, view.getBaseContext());
	}
}
