package com.orange.xtreme;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vikram on 2/1/14.
 */
public class DefaultUser{
    private String username;
    private String password;
    private boolean isAdmin;
    private Set<String> accounts;
    private ArrayList<Node> withdrawals;

    public DefaultUser(String u, String p, boolean b){
        username = u;
        password = p;
        isAdmin = b;
        accounts = new HashSet<String>();
        withdrawals = new ArrayList<Node>();
    }
    public ArrayList<Node> getTrans(Date start, Date end){
    	ArrayList<Node> ret = new ArrayList<Node>();
    	for(int i = 0; i<withdrawals.size(); i++){
    		if(end.after(withdrawals.get(i).getDate())&&start.before(withdrawals.get(i).getDate())){
    			ret.add(withdrawals.get(i));
    		}
    	}
    	return ret;
    }

    public void deposit(Date date, int bal, int bal2, String acc, int bal3, int bal4){
    	withdrawals.add(new Node(date, bal, bal2, acc, bal3, bal4));
    }

    public boolean checkAccount(String name){
        return (accounts.contains(name)?true:false); // true if contains
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public int compareTo(DefaultUser other) {
        return this.username.compareTo(other.getUsername());
    }
    public class Node{
    	private Date date;
    	private int bal;
    	private int bal2;
    	private int bal3;
    	private int bal4;
    	/**
		 * @return the bal3
		 */
		public int getBal3() {
			return bal3;
		}
		/**
		 * @param bal3 the bal3 to set
		 */
		public void setBal3(int bal3) {
			this.bal3 = bal3;
		}
		/**
		 * @return the bal4
		 */
		public int getBal4() {
			return bal4;
		}
		/**
		 * @param bal4 the bal4 to set
		 */
		public void setBal4(int bal4) {
			this.bal4 = bal4;
		}
		private String acc;
    	public Node(Date date, int bal, int bal2, String acc, int bal3, int bal4){
    		this.date = date;
    		this.bal = bal;
    		this.setBal2(bal2);
    		this.setAcc(acc);
    		this.bal3 = bal3;
    		this.bal4 = bal4;
    	}
		public String getAcc() {
			return acc;
		}
		public void setAcc(String acc) {
			this.acc = acc;
		}
		public int getBal2() {
			return bal2;
		}
		public void setBal2(int bal2) {
			this.bal2 = bal2;
		}
		/**
		 * @return the date
		 */
		public Date getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(Date date) {
			this.date = date;
		}
		/**
		 * @return the bal
		 */
		public int getBal() {
			return bal;
		}
		/**
		 * @param bal the bal to set
		 */
		public void setBal(int bal) {
			this.bal = bal;
		}
    }
}
