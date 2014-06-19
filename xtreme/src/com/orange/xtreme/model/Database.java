package com.orange.xtreme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author Vikram
 *
 */
public class Database {
	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public static String mail(final String username, final String email){
		final List<String> list = new ArrayList<String>();
		Thread thread;
		try{
			thread = new Thread(new Runner(){
				public void run(){
					try{
						setup();
						for(String trans: getPass(username)){
							list.add(trans);
						}
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thread.start();
			thread.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()==1){
			return null;
		}
		if (list.get(1).equals(email)){
			return list.get(0);
		}
		return null;
	}
	public static boolean login(final String username, final String password){
		Thread thread;
		final AtomicBoolean	cond = new AtomicBoolean(false);
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						cond.set(login(username, password));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cond.get();
	}
	/**
	 *
	 * @param username
	 * @param password
	 * @param date
	 * @param email
	 * @return
	 */
	public static boolean addUser(final String username, final String password, final String date, final String email, 
			final String hint, final String answer){
		Thread thread;
		final AtomicBoolean	cond = new AtomicBoolean(false);
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						cond.set(createUser(username, password, date, email, hint, answer));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cond.get();
	}
	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	/// format {name, date created, balance, date last accessed (transactions happen only incrementally)}
	public static List<List<String>> getAccounts(final String username, final String password){
		Thread thread;
		final List<List<String>> list = new ArrayList<List<String>>();
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						if(login(username, password)){
							for(List<String> name: getAccounts()){
								list.add(name);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 *
	 * @param username
	 * @param password
	 * @param name
	 * @return
	 */
	// format: {change, balance, date} - note first entry = creation
	public static List<List<String>> getTransactions(final String username, final String password,
			final String name){
		Thread thread;
		final List<List<String>> list = new ArrayList<List<String>>();
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						if(login(username, password)){
							for(List<String> trans: getTransactions(name)){
								list.add(trans);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 *
	 * @param username
	 * @param password
	 * @param name
	 * @param change
	 * @param balance
	 * @param date
	 */
	public static void addTransaction(final String username, final String password,
			final String name, final String change, final String balance, final String date, final String cood){
		Thread thread;
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						if(login(username, password)){
							addTransaction(name, change, balance, date, cood);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * @param username
	 * @param password
	 * @param name
	 * @param balance
	 * @param date
	 * @return
	 */
	public static boolean addAccount(final String username, final String password,
			final String name, final String balance, final String date, final String cood){
		Thread thread;
		final AtomicBoolean	cond = new AtomicBoolean(false);
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						if(login(username, password)){
							cond.set(addAccount(name, balance, date, cood));
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cond.get();
	}
	public static void logout(){
		Thread thread;
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						logout();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// username, password, email, date, hint, answer
	public static List<List<String>> getUsers(){
		Thread thread;
		final List<List<String>> list = new ArrayList<List<String>>();
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						for(List<String> name: getUsers()){
							list.add(name);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static boolean deleteAccount(final String account){
		Thread thread;
		final AtomicBoolean	cond = new AtomicBoolean(false);
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						setup();
						cond.set(deleteAccount(account));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cond.get();
	}
	public static void resetPass(final String username, final String newPassword){
		Thread thread;
		try {
			thread = new Thread(new Runner(){
				public void run(){
					try {
						resetPass(username, newPassword);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					);
			thread.start();
			thread.join();
			//return thread.getCond();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}