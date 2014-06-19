package com.orange.xtreme.activities;

import java.util.ArrayList;
import java.util.List;

import com.orange.xtreme.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DeleteAccounts extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reports);
	}
	public void onClickWithdrawals(View v){
		Intent intent = new Intent(this, Withdrawals.class);
		finish();
		startActivity(intent);
	}
	public void onClickBack(View v){
		Intent intent = new Intent(this, Accounts.class);
		finish();
		startActivity(intent);
	}
	public void onClickDeposits(View v){
		Intent intent = new Intent(this, Deposits.class);
		finish();
		startActivity(intent);
	}
}
