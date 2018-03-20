package server;

import java.util.ArrayList;

/**
 * This class acts as a bank, with a collection of accounts in it as well as a name.
 * Functionality of this class includes being able to search through the list of accounts by their account balance,
 * account number or account balance, as well as adding or removing accounts from the account arraylist
 * 
 * @author Daniel Wierzbicki
 *
 */

public class Bank{
	
	private String bankName;
	ArrayList<Account> accounts = new ArrayList<Account>();
	

	/*
	 * Zero Argument Constructor
	 * Safe state of bank name is "Seneca@York"
	 */
	public Bank() {
		bankName ="Seneca@York";
	}
	

	/*
	 * 	One argument constructor 
	 * 
	 * @param s name of the bank
	 */
	public Bank(String s) {
		bankName =s;
	}
	
	/*
	 * This method adds an account to the arraylist in the current bank object, 
	 * if it's not null and if it's account number is not already in the arraylist
	 * 
	 * @param newAccount account object to be added to the bank's arraylist of accounts
	 * @return true if the account was successfully added
	 */
	public boolean addAccount( Account newAccount ){
		if(newAccount == null){
			return false;
		}
		
		
		for(int i=0; i<accounts.size();i++){
			if (accounts.get(i).getAccountNumber().equals(newAccount.getAccountNumber())){ 
				return false;
			}
		}
		accounts.add(newAccount);
		return true;
		
	}
	
	 /*
     * Equals operator checks to see if the banks are the same
     * 
	 * @param bank account being compared to this bank
     */
	public boolean equals( Object bnk ) {

		boolean result = false;
		
		if ( bnk instanceof Bank ) {

			Bank bnk2 = (Bank) bnk;
			
			for (int i=0;i<accounts.size();i++){
				if (bnk2.bankName.equals(bankName) && bnk2.accounts.equals(accounts)) {
					result = true;
				}
			}
		}
		return result;
	}
	
	/*
	 * Looks through the bank's arraylist of accounts and checks if there is any that match the parameter
	 * 
	 * @param balance balance being compared to the bank's arraylist of account's balances
	 * @return Account[] array of found accounts with parameter balance
	 */
	public Account[] searchByBalance(String balance){
		
		int isItEmpty=0;
		
		for(int i=0;i<accounts.size();i++){
			if(accounts.get(i).getAccountBalance().toString().equals(balance)){
				isItEmpty++;
			}
		}
		
		Account[] returnMe = new Account[isItEmpty];
		
		if(isItEmpty==0){
			return null;
		}
		
		int index=0;
		
		for(int i=0;i<accounts.size();i++){
			if(accounts.get(i).getAccountBalance().toString().equals(balance)){
				returnMe[index]=accounts.get(i);
				index++;
			}
		}
		
		return returnMe;
		
	}
	
	/*
	 * Looks through the bank's arraylist of accounts and checks if there is any that match the parameter
	 * 
	 * @param accountName account name being compared to the bank's arraylist of account's account names
	 * @return Account[] array of found accounts with parameter balance
	 */
	public Account [ ] searchByAccountName( String accountName ){
		int isItEmpty=0;
		
		for(int i=0;i<accounts.size();i++){
			if(accounts.get(i).getFullName().equals(accountName)){
				isItEmpty++;
			}
		}
		
		Account[] returnMe = new Account[isItEmpty];
		
		if(isItEmpty==0){
			return null;
		}
		
		int index=0;
		
		for(int i=0;i<accounts.size();i++){
			if(accounts.get(i).getFullName().equals(accountName)){
				returnMe[index]=accounts.get(i);
				index++;
			}
		}
		
		return returnMe;
		
	}
	
	/*
	 * Looks through the bank's arraylist of accounts and checks if there is any that match the parameter.
	 * Since each account has a unique accountNumber, only one account with and one accountNumber can exist
	 * 
	 * @param accountNumber number being compared to the bank's arraylist of unique account numbers
	 * @return Account account with an account number matching the paramaters'
	 */
	public Account searchByAccountNumber( String accountNumber ){
		if(!accountNumber.equals("") && !accountNumber.equals(null)){
			for(int i=0;i<accounts.size();i++){
				if(accounts.get(i).getAccountNumber().equals(accountNumber)){
					return accounts.get(i); //not sure if I can do this
				}
			}
		}
		return null;
	}
	
	/**
	 * This method removes an account to the arraylist in the current bank object, 
	 * if it's not null 
	 * 
	 * @param accountNumber account number of account to be removed from the arraylist of accounts
	 * @return Account which will not be null if the account was successfully removed
	 */
	public Account removeAccount( String accountNumber ){
		if(!accountNumber.equals("") && !accountNumber.equals(null)){
			for(int i=0;i<accounts.size();i++){
				if(accounts.get(i).getAccountNumber().equals(accountNumber)){
					Account temp;
					temp = accounts.get(i);
					accounts.remove(i);
					return temp; 
				}
			}
		}
		return null;
	}
	
	/**
	 * Calls the toString method of the inputted account
	 * 
	 * @param account account to be displayed
	 */
	public static void displayAccount( Account account ){
		System.out.println(account);
	}
	
	/**
	 * Loops through an array of accounts calling each index's respective toString method no matter the type of account
	 * @param listOfAccounts list of accounts to be displayed
	 */
	public static void listAccounts( Account [ ] listOfAccounts){
		for (int i=0; i<listOfAccounts.length; i++){
			System.out.println(listOfAccounts[i] + "\n");
		}
	}
	
	/**
	 * Loops through an array of accounts calling each index's respective toString method no matter the type of account
	 * @param listOfAccounts list of accounts to be displayed
	 */
	public Account[] arrayOfAccounts(){
		Account[] array = new Account[accounts.size()];
		for (int i=0; i<accounts.size(); i++){
			array[i] = accounts.get(i);
		}
		return array;
	}
	
	public int indexOfAccounts() {
		return accounts.size();
	}
	
	
	/* 
	 * @return String of all the information held in the Bank, looping through the arraylist printing the accounts one at a time
	 */
	public String toString() {
		String superCoolString;
		superCoolString = "\n\n*** Welcome to the Bank of "+bankName+" ***"+"\nIt has "+accounts.size()+" accounts.";
		for (int i=0; i<accounts.size(); i++){
			int temp = i+1;
			superCoolString+="\n"+temp+". number: "+accounts.get(i).getAccountNumber()+", name: "+accounts.get(i).getFullName()+", balance: $"+accounts.get(i).getAccountBalance();
		}
		return superCoolString;
	}
	
}