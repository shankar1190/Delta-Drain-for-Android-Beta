package com.shankar1190.deltadrain;

/*
 * @author: shankar1190
 */

import java.util.Date;

public class Transaction {
	public int amount;
	public String comments;
	public Date date;
	public String location;
	public Transaction(int amt, String com, String loc) {
		amount = amt;
		comments = com;
		date = new Date();
		location = loc;
	}
}
