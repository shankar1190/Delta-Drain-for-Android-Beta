package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 */

public class Bugger {

	private String name;
	private String type;
	private int delta;
	private Transaction[] transactionHistory;
	private int numTransactions;
	
	// Constructor
	public Bugger(String buggerName, int initialDelta, String comment, String typ, String loc) {
		name = buggerName;
		delta = initialDelta;
		type = typ;
		transactionHistory = new Transaction[11];
		Transaction firstTransaction = new Transaction(initialDelta, comment, loc);
		transactionHistory[0] = firstTransaction;
		numTransactions = 1;
	}
	
	// Setter methods for bugger details
	public void setDelta(int amount) {
		delta = amount;
	}
	
	public void addDelta(int amount) {
		delta += amount;
	}
	
	public void resetDelta() {
		setDelta(0);
		numTransactions = 0;
	}
	
	public void insertTransaction(Transaction t) {
		delta += t.amount;
		
		for (int i = numTransactions - 1; i >= 0; i--) {
			transactionHistory[i + 1] = transactionHistory[i];
		}
		
		transactionHistory[0] = t;
		
		if (numTransactions < 10) numTransactions++;
	}
	
	// Getter methods
	public String getName() {
		return name;
	}
	
	public int getDelta() {
		return delta;
	}
	
	public String getType() {
		return type;
	}
	
	public String getLastTenTransactions() {
		String ret = new String("");
		for (int i = 0; i < numTransactions; i++) {
			ret += ("\n" + ((Integer)(i + 1)).toString()
					+ ". Amount: " + ((Integer)transactionHistory[i].amount).toString()
					+ "\nLocation: " + transactionHistory[i].location
					+ "\nDate: " + transactionHistory[i].date.toLocaleString()
					+ "\nComments: " + transactionHistory[i].comments + "\n");
		}
		return ret;
	}
}