package server;

import java.math.BigDecimal;


/**
 * Class that is meant to be the base for different types of bank accounts.
 * Contains variables to hold the full name of the account holder, a unique number as their account number, 
 * a variable for current account balance and starting account balance.
 * Also has setters and getter for every variable other than starting balance which is only used in the constructors
 * 
 * @author Daniel Wierzbicki
 *
 */

public class Account{

	protected String fullName, accountNum;
	protected BigDecimal accountBalance, startingBalance;


	//Zero Argument Constructor
	public Account() {
		fullName = "";
		accountNum = "";
		accountBalance = new BigDecimal(0);
	}

	//Three Argument Constructor
	/**checks each entered value, if null or -1 is entered, enters that variable into "safe state"
	 * @param name full name of client
	 * @param aNum account number
	 * @param aBalance account balance / current balance
	 */
	public Account(String name, String aNum, String aBalance) {

		if(name==null) {fullName = "";}
		else {fullName = name;}

		if(aNum==null) {accountNum = "";}
		else {accountNum = aNum;}

		if(aBalance.equals("-1") || aBalance == null) {aBalance.equals("0");}
		else {accountBalance = new BigDecimal(aBalance); startingBalance = new BigDecimal(aBalance);}
		
		

	}

	//SETTERS

	/**
	 * Setter for fullName
	 * @param name full name
	 */
	public void setFullName(String name) {
		this.fullName = name;
	}


	/**
	 * Setter for accountNum
	 * @param num account number
	 */
	public void setAccountNumber(String num) {
		this.accountNum = num;
	}


	/**
	 * Setter for accountBalance
	 * @param balance
	 */
	public void setAccountBalance(BigDecimal balance) {
		this.accountBalance = balance;
	}

	//GETTERS


	/**
	 * Getter for fullName
	 * @return
	 */
	public String getFullName() {
		return fullName;
	}


	/**
	 * Getter for accountNum
	 * @return
	 */
	public String getAccountNumber() {
		return accountNum;
	}


	/**
	 * Getter for accountBalance
	 * @return
	 */
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	
    /*
     * Equals operator checks to see if the accounts are the same
	 * @param acc account being compared to this account
     */
    public boolean equals( Object acc ) {

		boolean result = false;

		if ( acc instanceof Account ) {

			Account acc2 = (Account) acc;

			if ( (acc2.fullName.equals(fullName)) &&
			     (acc2.accountNum.equals(accountNum)) &&
			     (acc2.accountBalance.equals(accountBalance)))

			     result = true;
		}

		return result;
	}

	/**
	 * Attempts to increase the accountBalance
	 * 
	 * @param amount amount to increase account balance
	 * @return boolean returns true if deposit was sucessful
	 */
	public boolean deposit( BigDecimal amount ){
		if(amount.doubleValue() > 0){
			accountBalance = accountBalance.add(amount);
			return true;
		}
		return false;
	}

	/**
	 *  Attempts to decrease the accountBalance
	 * 
	 * @param amount amount of money to withdraw from account
	 * @return boolean returns true if withdrawl was sucessful
	 */
	public boolean withdraw( BigDecimal amount ){
		if(amount.intValue() > 0 && (accountBalance.subtract(amount)).intValue() > 0){
			accountBalance = accountBalance.subtract(amount);
			return true;
		}
		return false;
	}

	/* 
	 * 
	 * @return String of all the information held in the account
	 */
	public String toString() {
		String superCoolString = "number: " + accountNum + ", " + "name: " + fullName + "\n"
		+ "starting balance: " + "$" + startingBalance + ", " + "current balance: " + "$" + accountBalance + "\n";
		return superCoolString;
	}

}
