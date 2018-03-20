package server;

import java.math.BigDecimal;
import java.util.ArrayList;


import server.Account;

/**
 * Specific type of account that has extra variables that accounts for a service charge made to the account everytime a transaction is made,
 * it keeps a list of all transactions made and keeps track of all the service charges
 * 
 * @author Jonathan De Groot
 *
 */

public class Chequing extends Account
{
	private BigDecimal serviceCharge;
	ArrayList<String> transactions = new ArrayList<String>();
	private int totalServiceCharge;

	
	/**
	 * Three argument Constructor that calls to account's three argument constructor
	 * 
	 * @param fN full name of account holder
	 * @param aN account number - will be unique
	 * @param sB starting balance of account - the amount that account was opened with
	 * @param sC service charge - amount being charged to the account whenever a transaction occurs
	 */
	public Chequing(String fN, String aN, String sB, double sC)
	{
		super(fN, aN, sB); 
		serviceCharge = new BigDecimal(sC);
	}

	/**
	 * Zero argument Constructor that calls to the Account's zero argument constructor
	 * and sets the service charge to a safe state
	 */
	public Chequing()
	{
		super(); 
		serviceCharge = new BigDecimal(0.25);
	}


	/* 
	 * This method expands on account's deposit, adding the transaction to record
	 * 
	 * @param amount amount to deposit
	 * @return boolean will return true if deposit is successful false if unsuccessful
	 * @see Account's deposit
	 */
	public boolean deposit(BigDecimal amount)
	{
		if(amount.intValue() > 0)
		{
			accountBalance = accountBalance.add(amount); 
			transactions.add(amount.toString()); 
			return true;
		}
		else
			return false;
	 }


	/* 
	 * This method expands on account's withdraw, adding the transaction to record and charging the account 
	 * a service charge for withdrawing their money, and recording that service charge as a transaction
	 *  
	 * @param amount amount to deposit
	 * @return boolean will return true if withdrawl is successful false if unsuccessful
	 * @see Account's withdraw
	 */
	public boolean withdraw(BigDecimal amount)
	{
		if(amount.intValue() > 0 && (accountBalance.subtract(amount)).intValue() > 0)
		{
			accountBalance = accountBalance.subtract(amount); 
      		accountBalance = accountBalance.subtract(serviceCharge);
      		int why = amount.intValue() * -1;
      		transactions.add(Integer.toString(why)); 
      		totalServiceCharge++;
      		return true;
		}
		else
			return false;
	}


	/* 
	 * Equals operator checks to see if the param is the same as the current object
	 * 
	 * @param acc object that is being to see it is of type chequings of account
	 * @return boolean returns true if it's the same account false if it's a different account
	 * 
	 */
	public boolean equals(Object acc)
	{
		boolean result = false;

		if ( acc instanceof Chequing)
		{
			Chequing acc2 = (Chequing) acc;

			if ( (acc2.fullName.equals(fullName)) &&
					(acc2.accountNum.equals(accountNum)) &&
					(acc2.accountBalance.equals(accountBalance)) &&
					(acc2.serviceCharge.intValue() == serviceCharge.intValue()) &&
					(acc2.transactions.equals(transactions)))

				result = true;
		}
		return result;
	}


	/* 
	 * Returns a string containing all the information contained in the chequing's account, calling it's parent's toString before appending the extra information to it
	 * 
	 * @return String containing all the information in this class
	 * @see Account's toString
	 */
	public String toString()
	{
		StringBuffer str = new StringBuffer(super.toString());
		str.append("type: CHEQUING" + "\n"
				+ "service charge: " + "$" + serviceCharge + "\n"
				+ "number of transactions: " + transactions.size() + "\n"
				+ "total amount of service charge: " + "$" + (totalServiceCharge * serviceCharge.doubleValue()));
   
		String str2 = new String(str); 
		return str2;
	}
}
