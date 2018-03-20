package server;

import java.math.BigDecimal;

import server.Account;

import java.lang.Math;

/**
 * Specific type of account that has extra variables that accounts for a period of investment and an interest rate
 * deposits and withdrawls cannot be made from this account
 * 
 * @author Jonathan De Groot
 *
 */

public class GIC extends Account
{
	private double periodOfInvestment;
	private double interestRate;


	/**
	 * Three argument Constructor that calls to account's three argument constructor
	 * 
	 * @param fN full name of account holder
	 * @param aN account number - will be unique
	 * @param sB starting balance of account - the amount that account was opened with
	 * @param pOI period of investment - length of time the account will be investing for 
	 * @param iR interest rate - rate at which the account gains interest
	 */
	public GIC(String fN, String aN, String sB, double pOI, double iR)
	{
		super(fN, aN, sB);
		periodOfInvestment = pOI;
		interestRate = iR;
	}

	/**
	 * Zero argument Constructor that calls to the Account's zero argument constructor
	 * setting period of investment and interest rate to a safe state 
	 */
	public GIC()
	{
		super(); 
		periodOfInvestment = 1;
		interestRate = 1.25;
	}

	
	/**
	 * This method expands on account's deposit, stopping transations from occuring
	 * 
	 * @param amount amount to deposit
	 * @return boolean always returns false since transations can't be made on a GIC account
	 */
	public boolean deposit(String amount){
		System.out.println("No deposits may be made on a GIC account");
		return false;
	}

	/**
	 * This method expands on account's withdraw, stopping transations from occuring
	 * 
	 * @param amount amount to withdraw
	 * @return boolean always returns false since transations can't be made on a GIC account
	 */
	public boolean withdraw(String amount){
		System.out.println("No withdrawals may be made on a GIC account");
		return false;
	}

	/**
	 * This method calculates the amount that will be in the account at the end of it's investment period 
	 * using the formula : future value = principal * ( 1 + rate )^(number of periods)
	 * 
	 * @return BigDecimal What the balance will be at maturity of the account
	 */
	@SuppressWarnings("deprecation")
	public BigDecimal getBalanceAtMaturity(){
		double futureBalance = (getAccountBalance().doubleValue()* Math.pow((1 + (interestRate/12)), periodOfInvestment));
		BigDecimal bd = new BigDecimal(futureBalance);
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/* 
	 * Equals operator checks to see if the param is the same as the current object
	 * 
	 * @param acc object that is being to see it is of type chequings of account
	 * @return boolean returns true if it's the same account false if it's a different account
	 */
	public boolean equals(Object acc){
    boolean result = false;

    if ( acc instanceof GIC ){
    	GIC acc2 = (GIC) acc;
    	
    	if ( (acc2.fullName.equals(fullName)) &&
    		(acc2.accountNum.equals(accountNum)) &&
    		(acc2.accountBalance.equals(accountBalance)) &&
    		(acc2.periodOfInvestment == periodOfInvestment &&
    		(acc2.interestRate == interestRate)))

    		result = true;
    }
    return result;
	}

	/* 
	 * Returns a string containing all the information contained in the GIC account, calling it's parent's toString before appending the extra information to it
	 * 
	 * @return String containing all the information in this class
	 * @see Account's toString
	 */
	public String toString()
	{
		StringBuffer str = new StringBuffer(super.toString());
		str.append("type: GIC " + "annual interest rate: " + interestRate + "%" + "\n" +
				"period of investment: " + (int)periodOfInvestment + " years" + "\n" +
				"new balance at maturity: " + "$" + getBalanceAtMaturity());
    
		String str2 = new String(str); 
		return str2;
	}

}
