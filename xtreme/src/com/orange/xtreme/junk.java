/*package com.orange.xtreme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
//import com.orange.xtreme.DefaultUser.Node;



import java.util.*;

import com.orange.xtreme.R;

/*public class junk extends Activity{
    private List<String> accList;
    private int accNumber;
	private Button exit;
	private Button login;
	private Button register;
	private Button create;
	private Button createAccount;
	private Button createAccount1;
	private Button deleteAccount;
	private Button logout;
	private Button cancel;
	private Button back;
	private Button withdraw;
	private Button deposit;
	private Button history;
	private Button back2;
    private Button backHist;
	private EditText confirmPass;
	private EditText username;
	private EditText password;
	private Button loginButton;
	private DefaultUser user;
	private TextView loginAttempt;
	private TextView registerAttempt;
	private ArrayList<Button> accounts;
	private Map<String, Button> buttons;


	OnKeyListener myKeyListener = new OnKeyListener() {
		 
		public boolean onKey(View view, int i, KeyEvent keyEvent) {
			return false;
		}
	};

	OnClickListener trollClickListener = new OnClickListener() {
		 
		public void onClick(View v) {
		    accNumber = (Integer) v.getTag();
		    Log.d(null, "tag = " + accNumber);
			accList = Database.getAccounts(user.getUsername(), user.getPassword()).get(accNumber);
			Log.d(null, "length = " + accList.size());
			setContentView(R.layout.loginsuccess);
			TextView txt = (TextView) findViewById(R.id.name);
			txt.setText(accList.get(0));
			txt = (TextView) findViewById(R.id.balance);
			Log.d(null, accList.get(2));
            String[] balanceSplit = new String[2];
            String balance2 = new String();
            String balance1 = new String();
            if (accList.get(2).contains(".")) {
            	balanceSplit = accList.get(2).split("[.]");
            	balance1 = balanceSplit[0];
            	balance2 = balanceSplit[1];
    			if (balance2.length() == 1) {
    				balance2 = "0" + balance2;
    			}
            } else {
            	balance1 = accList.get(2) + "";
            	balance2 = "00";
            }
			txt.setText("Current Balance: $" + balance1 + "." + balance2);
			withdraw = (Button) findViewById(R.id.withdraw);
			deposit = (Button) findViewById(R.id.deposit);
			history = (Button) findViewById(R.id.history);
			back2 = (Button) findViewById(R.id.back);
			withdraw.setOnClickListener(myClickListener);
			deposit.setOnClickListener(myClickListener);
			history.setOnClickListener(myClickListener);
			back2.setOnClickListener(myClickListener);
		}

	};
	OnClickListener myClickListener = new OnClickListener() {
		 
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == exit) {
				Database.logout();
				user = null;
				accList = null;
				accNumber = 0;
				finish();
			} else if (v == login) {
				login();
			} else if (v == back ) {
				setContentView(R.layout.welcome);
				exit = (Button) findViewById(R.id.exit);
				login = (Button) findViewById(R.id.login);
				register = (Button) findViewById(R.id.register);
				logout = (Button) findViewById(R.id.logout);
				exit.setOnClickListener(myClickListener);
				login.setOnClickListener(myClickListener);
				register.setOnClickListener(myClickListener);
				logout.setOnClickListener(myClickListener);
				setContentView(R.layout.login);
				username = (EditText) findViewById(R.id.username);
				password = (EditText) findViewById(R.id.password);
				loginButton = (Button) findViewById(R.id.abc);
				loginButton.setOnClickListener(myClickListener);
				loginAttempt = (TextView) findViewById(R.id.registerAttempt);
				register.setOnClickListener(myClickListener);
				password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
			} else if(v == loginButton){
				if (Database.login(username.getText().toString(), password.getText().toString())) {
					user = new DefaultUser(username.getText().toString(), password.getText().toString(), false);
					setContentView(R.layout.selectaccount);
					onLoginSuccess();
				} else {
					loginAttempt.setText("Try Again");
					username.setText("");
					password.setText("");
				}
			} else if (v == register) {
				setContentView(R.layout.register);
				initializeRegisterScreen();
			} else if (v == create) {
				if (username.getText().toString().equals("") || password.getText().toString().equals("") || confirmPass.getText().toString().equals("")) {
					registerAttempt.setText("Please re-enter information. One or more fields is empty.");
				} else {
					if ((password.getText().toString().equals(confirmPass.getText().toString()))) {
						if (true) {
							user = new DefaultUser(username.getText().toString(), password.getText().toString(), false);
							Database.addUser(username.getText().toString(), password.getText().toString(), "", "");
							Database.login("", "");
							login();
						} else {registerAttempt.setText("Please pick another username: this one already exists.");}
					} else {
						registerAttempt.setText("Please re-enter the passwords.");
						password.setText("");
						confirmPass.setText("");
					}
				}
			} else if (v == createAccount) {
				setContentView(R.layout.createaccount);
				createAccount1 = (Button) findViewById(R.id.create);
				cancel = (Button) findViewById(R.id.cancel);
				createAccount1.setOnClickListener(myClickListener);
				cancel.setOnClickListener(myClickListener);
			} else if (v == createAccount1) {
				createAccount();
			} else if (v == cancel) {
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
			} else if(v == withdraw){
				transaction(false);
			} else if(v == deposit){
				transaction(true);
			} else if(v == history){
				setContentView(R.layout.history);
				history();
			}  else if (v == backHist) {
                setContentView(R.layout.loginsuccess);
                onLoginSuccess();
            } else if(v == back2){
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
			} else if(v == buttons.get("genReport")){
				setContentView(R.layout.genreport);
				genReport();
			} else if(v == buttons.get("genReportBack")){
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
			} else if(v == buttons.get("genReportGenerate")){
				generate();
			} else if (v == logout) {
				Database.logout();
				setContentView(R.layout.welcome);
				user = null;
				accList = null;
				accNumber = 0;
				setContentView(R.layout.welcome);
				exit = (Button) findViewById(R.id.exit);
				login = (Button) findViewById(R.id.login);
				register = (Button) findViewById(R.id.register);
				exit.setOnClickListener(myClickListener);
				login.setOnClickListener(myClickListener);
				register.setOnClickListener(myClickListener);
				//password.setOnClickListener(myClickListener);
				buttons = new HashMap<String, Button>();
			} else if(v == buttons.get("reportBack")){
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
			}else if(v == buttons.get("reportlogOut")){
				user = null;

				recreate();
			}
		}
	};
    public void login(){
		setContentView(R.layout.login);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		loginButton = (Button) findViewById(R.id.abc);
		loginButton.setOnClickListener(myClickListener);
		loginAttempt = (TextView) findViewById(R.id.loginAttempt);
		password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
	}
    @SuppressWarnings("deprecation")
	public void genReport() {
		buttons.put("genReportBack", (Button) findViewById(R.id.back));
		buttons.get("genReportBack").setOnClickListener(myClickListener);
		buttons.put("genReportGenerate", (Button) findViewById(R.id.generate));
		buttons.get("genReportGenerate").setOnClickListener(myClickListener);
	}
    public void generate(){
		String dat[] = new String[6];
		dat[0] = ((EditText) findViewById(R.id.date)).getText().toString();
		dat[1] = ((EditText) findViewById(R.id.date2)).getText().toString();
		dat[2] = ((EditText) findViewById(R.id.date3)).getText().toString();
		dat[3] = ((EditText) findViewById(R.id.date4)).getText().toString();
		dat[4] = ((EditText) findViewById(R.id.date5)).getText().toString();
		dat[5] = ((EditText) findViewById(R.id.date6)).getText().toString();
		for(int i = 0; i<6; i++){
			if(dat[i].equals("")) {setContentView(R.layout.selectaccount); onLoginSuccess();return;}
		}
		int date[] = new int[6];
		Date start;
		Date end;
		try{
			for(int i =0; i<6; i++){
				date[i] = Integer.parseInt(dat[i]);
				if(date[i]<0){setContentView(R.layout.selectaccount); onLoginSuccess();return;}
			}
			if(date[0]>12||date[3]>12||date[1]>31||date[4]>31)
			{setContentView(R.layout.selectaccount); onLoginSuccess();return;}
			start = new Date(date[2], date[0], date[1]);
			end = new Date(date[5], date[3], date[4]);
			if(start.after(end)){setContentView(R.layout.selectaccount); onLoginSuccess();return;}
		}catch(NumberFormatException e){setContentView(R.layout.selectaccount); onLoginSuccess();return;}	
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> nameList = new ArrayList<String>();
		for (List<String> i : Database.getAccounts(user.getUsername(), user.getPassword())) {
			List<List<String>> trans = Database.getTransactions(user.getUsername(), user.getPassword(), i.get(0));
			for (List<String> l : trans) {
				String[] dateBits = l.get(2).split("[/]");
				Date curDate = new Date(Integer.parseInt(dateBits[2]), Integer.parseInt(dateBits[0]), Integer.parseInt(dateBits[1]));
				if (curDate.compareTo(start) >= 0 && curDate.compareTo(end) <= 0) {
					Log.d(null, "compare: " + curDate + " to " + start);
					list.add(l);
					nameList.add(i.get(0));
				}
			}	
		}	
		setContentView(R.layout.report);
		buttons.put("reportBack", (Button) findViewById(R.id.back));
		buttons.put("reportlogOut", (Button) findViewById(R.id.logout));
		buttons.get("reportBack").setOnClickListener(myClickListener);
		buttons.get("reportlogOut").setOnClickListener(myClickListener);
		TextView txt = (TextView) findViewById(R.id.init);
		//txt.setText("Start Date: " + start.toString());
		//txt = (TextView) findViewById(R.id.fin);
		//txt.setText("End Date: " + end.toString());
		LinearLayout l1 = (LinearLayout) findViewById(R.id.date);
		LinearLayout l2 = (LinearLayout) findViewById(R.id.amount);
		LinearLayout l3 = (LinearLayout) findViewById(R.id.account);
		int i = 0;
		String b;
		String name;
		TextView dat1, amt, nam;
		while(i<list.size()){
			dat1 = new TextView(this);
			amt = new TextView(this);
			nam = new TextView(this);
			b = list.get(i).get(0);
			name = nameList.get(i);
			dat1.setText(list.get(i).get(2));
			dat1.setTextSize(18);
			amt.setText("$"+ b);
			amt.setTextSize(18);
			nam.setText(name);
			nam.setTextSize(18);
			l2.addView(amt);
			l1.addView(dat1);
			l3.addView(nam);
			amt.setGravity(Gravity.TOP);
			dat1.setGravity(Gravity.TOP);
			nam.setGravity(Gravity.TOP);
			i++;
		}
	}
	 
    public void errorCreateAccount(){
		TextView temp = (TextView) findViewById(R.id.AccountText);
		temp.setText("One of the fields has invalid inputs or is empty or Account" +
				"already exists");
		confirmPass.setText("");
		confirmPass = (EditText) findViewById(R.id.date2);
		confirmPass.setText("");
		confirmPass = (EditText) findViewById(R.id.date);
		confirmPass.setText("");
		confirmPass = (EditText) findViewById(R.id.accountname);
		confirmPass.setText("");
		confirmPass = (EditText) findViewById(R.id.initialbalance);
		confirmPass.setText("");
		confirmPass = (EditText) findViewById(R.id.decBalance);
		confirmPass.setText("");


    }
    public void transaction(boolean add){
		confirmPass = (EditText) findViewById(R.id.trans1);
		String balance = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.trans2);
		String decBalance = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.date);
		String date = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.date2);
		String date2 = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.date3);
		String date3 = confirmPass.getText().toString();
		int dat = 0;
		int dat2 = 0;
		int dat3 = 0;
		int bal = 0;
		int bal2 = 0;
		if (date.equals("") || balance.equals("")||date2.equals("") || date3.equals("" )||
				decBalance.equals("") || !(date.length()<=2)|| !(date2.length()<=2)||!(date3.length()<=4)||
				decBalance.length()!=2) {
			setContentView(R.layout.selectaccount);
			onLoginSuccess();
			return;
		} else {
			try{
				dat = Integer.parseInt(date);
				dat2 = Integer.parseInt(date2);
				dat3 = Integer.parseInt(date3);
				bal = Integer.parseInt(balance);
				bal2 = Integer.parseInt(decBalance);
                double change = bal + (1.00/100) * bal2;
                String balance2 = new String();
                String balance1 = new String();
                if (accList.get(2).contains(".")) {
                	String[] balanceSplit = accList.get(2).split("[.]");
                	balance1 = balanceSplit[0];
                	balance2 = balanceSplit[1];
        			if (balance2.length() == 1) {
        				balance2 = "0" + balance2;
        			}
                } else {
                	balance1 = accList.get(2) + "";
                	balance2 = "00";
                }
                double curBal =  Double.parseDouble(balance1) + (1.00 / 100) * Double.parseDouble(balance2);
                if (!add && change > curBal) {
                    setContentView(R.layout.selectaccount);
                    onLoginSuccess();
                    return;
                }
                String changeString = bal + "." + bal2;
                String dateString = dat + "/" + dat2 + "/" + dat3;
				if(dat>12||dat<1||dat2>31||dat2<1||dat3<0){
					setContentView(R.layout.selectaccount);
					onLoginSuccess();
					return;
				}
				if(add) {
                    curBal += change;
                    String newBal = new String(curBal + "");
                    String bal3 = new String();
                    String bal4 = new String();
                    if (accList.get(2).contains(".")) {
                    	String[] balanceSplit = newBal.split("[.]");
                    	bal3 = balanceSplit[0];
                    	bal4 = balanceSplit[1];
            			if (bal4.length() == 1) {
            				bal4 = "0" + bal4;
            			}
                    } else {
                    	bal3 = newBal + "";
                    	bal4 = "00";
                    }
                    Database.addTransaction(
                            user.getUsername(), user.getPassword(), accList.get(0), "+" + changeString,
                            "" + bal3 + "." + bal4, dateString);
                } else{
                    curBal -= change;
                    String newBal = new String(curBal + "");
                    String bal3 = new String();
                    String bal4 = new String();
                    if (accList.get(2).contains(".")) {
                    	String[] balanceSplit = newBal.split("[.]");
                    	bal3 = balanceSplit[0];
                    	bal4 = balanceSplit[1];
            			if (bal4.length() == 1) {
            				bal4 = "0" + bal4;
            			}
                    } else {
                    	bal3 = newBal + "";
                    	bal4 = "00";
                    }
                    Database.addTransaction(
                    
                    
                    
                    
                            user.getUsername(), user.getPassword(), accList.get(0), "-" + changeString,
                            "" + bal3 + "." + bal4, dateString);
                            
                            
                }
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
			}catch(NumberFormatException e){
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
				return;
			}
		}
	}
	
	
	
	
	
	
	
	 
    public void createAccount() {
		confirmPass = (EditText) findViewById(R.id.accountname);
		// comment
		String name = confirmPass.getText().toString();
		if(user.checkAccount(name)){ errorCreateAccount(); }
		confirmPass = (EditText) findViewById(R.id.initialbalance);
		String balance = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.decBalance);
		String decBalance = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.date);
		String date = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.date2);
		String date2 = confirmPass.getText().toString();
		confirmPass = (EditText) findViewById(R.id.date3);
		String date3 = confirmPass.getText().toString();
		int dat = 0;
		int dat2 = 0;
		int dat3 = 0;
		int bal = 0;
		int bal2 = 0;
		if (date.equals("") || name.equals("" )|| balance.equals("")||date2.equals("") || date3.equals("" )||
				decBalance.equals("") || !(date.length()<=2)|| !(date2.length()<=2)||!(date3.length()<=4)||
				decBalance.length()!=2) {
			errorCreateAccount();
		} else {
			try{
				dat = Integer.parseInt(date);
				dat2 = Integer.parseInt(date2);
				dat3 = Integer.parseInt(date3);
				bal = Integer.parseInt(balance);
				bal2 = Integer.parseInt(decBalance);
				if(dat>12||dat<1||dat2>31||dat2<0||dat3<0){
					errorCreateAccount();
				}
                String balanceString = bal + "." + bal2;
                String dateString = dat + "/" + dat2 + "/" + dat3;
                Database.addAccount(user.getUsername(), user.getPassword(), name, balanceString, dateString);
                Database.login(user.getUsername(), user.getPassword());
				setContentView(R.layout.selectaccount);
				onLoginSuccess();
			}catch(NumberFormatException e){
				errorCreateAccount();
			}
		}
	}

	 
    public void onLoginSuccess() {
		createAccount = (Button) findViewById(R.id.createAccount);
		createAccount.setOnClickListener(myClickListener);
		deleteAccount = (Button) findViewById(R.id.createAccount);
		deleteAccount.setOnClickListener(myClickListener);
		logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(myClickListener);
		Button genReport = (Button) findViewById(R.id.genReport);
		genReport.setOnClickListener(myClickListener);
		buttons.put("genReport", genReport);
		LinearLayout layout = (LinearLayout) findViewById(R.id.accountArray);
		TextView account = new TextView(this);
		Point size = new Point();
		Display display = ((android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		display.getSize(size);
		Button temp;
		accounts = new ArrayList<Button>();
		Log.d(null, "Is name null? " + (user.getUsername() == null));
		Log.d(null, "Is pass null? " + (user.getUsername() == null));
		if(Database.getAccounts(user.getUsername(), user.getPassword()) != null &&
				Database.getAccounts(user.getUsername(), user.getPassword()).size()>0){
			account.setBackgroundColor(Color.BLACK);
			account.setHeight(2);
			account.setWidth(size.x);
			layout.addView(account);
			System.out.println("I GOT HERE");
			for(int i = 0; i<Database.getAccounts(user.getUsername(), user.getPassword()).size(); i++){
				temp = new Button(this);
				temp.setText(Database.getAccounts(user.getUsername(), user.getPassword()).get(i).get(0));
				temp.setGravity(Gravity.CENTER);
				temp.setOnClickListener(trollClickListener);
				temp.setTag(i);
				accounts.add(temp);
				layout.addView(temp);
				account = new TextView(this);
				account.setWidth(size.x/2);
				account.setBackgroundColor(Color.BLACK);
				account.setHeight(2);
				layout.addView(account);
			}
		}
	}

	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		user = null;
		accList = null;
		accNumber = 0;
		setContentView(R.layout.welcome);
		exit = (Button) findViewById(R.id.exit);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		exit.setOnClickListener(myClickListener);
		login.setOnClickListener(myClickListener);
		register.setOnClickListener(myClickListener);
		//password.setOnClickListener(myClickListener);
		buttons = new HashMap<String, Button>();
	}


	 
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	 
    public void initializeRegisterScreen() {
		username =  (EditText) findViewById(R.id.username2);
		password = (EditText) findViewById(R.id.password2);
		confirmPass = (EditText) findViewById(R.id.password3);
		create = (Button) findViewById(R.id.create);
		create.setOnClickListener(myClickListener);
		registerAttempt = (TextView) findViewById(R.id.registerAttempt);
		password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
		confirmPass.setTransformationMethod(new AsteriskPasswordTransformationMethod());
		back = (Button) findViewById(R.id.back2);
		back.setOnClickListener(myClickListener);
	}

	public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
		 
		public CharSequence getTransformation(CharSequence source, View view) {
			return new PasswordCharSequence(source);
		}

		private class PasswordCharSequence implements CharSequence {

			private CharSequence mSource;

			public PasswordCharSequence(CharSequence source) {
				mSource = source;
			}

			public char charAt(int index) {
				return '*';
			}

			public int length() {
				return mSource.length();
			}

			public CharSequence subSequence(int start, int end) {
				return mSource.subSequence(start, end);
			}
		}
	}
	 
    public void history(){
		TextView txt = (TextView) findViewById(R.id.init);
        List<List<String>> transHistory = Database.getTransactions(user.getUsername(), user.getPassword(), accList.get(0));
		txt.setText("Initial:" + transHistory.get(0).get(1) +" on " + transHistory.get(0).get(2));
		//txt = (TextView) findViewById(R.id.fin);
		txt.setText(accList.get(2));
		LinearLayout date = (LinearLayout) findViewById(R.id.amount);
		LinearLayout amount = (LinearLayout) findViewById(R.id.date);
        backHist = (Button) findViewById(R.id.histBack);
		int i = 0;
		String b;
        String d;
		TextView dat, amt;
		while(i<transHistory.size()){
			dat = new TextView(this);
			amt = new TextView(this);
			b = transHistory.get(i).get(0);
			d = transHistory.get(i).get(2);
			dat.setText(b);
			dat.setTextSize(25);
			amt.setText(d);
			amt.setTextSize(25);
			amount.addView(amt);
			date.addView(dat);
			i++;
			amt.setGravity(Gravity.TOP);
			dat.setGravity(Gravity.TOP);
		}
	}
}*/
