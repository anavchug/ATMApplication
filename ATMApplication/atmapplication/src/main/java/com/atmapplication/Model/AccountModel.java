package com.atmapplication.Model;
import java.util.*;
import com.atmapplication.DomainObject.Account;

public class AccountModel {
    private static ArrayList<Account> accounts = new ArrayList<Account>();

    public AccountModel(){
    //Default data
    accounts.add(new Account(123,"hello", 2000));
    accounts.add(new Account(246, "january", 3000));
    }

    public static ArrayList<Account> getAllAccounts(){
        return accounts;
    }

    public static Account getAccountByNumber(int accountNumber) {
        for(Account a: accounts){
            if(a.getAccountNumber() == accountNumber){
                return a;
            }
        }
        return null;
    }
   
    public static void addAccount(Account acc) {
        accounts.add(acc);
    }

     public static void removeAccount(Account acc) {
        for(Account a: accounts){
            if(a.equals(acc)){
                accounts.remove(acc);
            }
        }
    }
    //this function is just to test 
    public static int getInitialBalance() {
        if (!accounts.isEmpty()) {
            return accounts.get(0).getBalance();
        }
        return 0; // Return a default value if no accounts are available
    }
    public static void deposit(Account account, int deposit ){
        Account acc = getAccountByNumber(account.getAccountNumber());
        int currBalance = acc.getBalance();
        currBalance += deposit;
        acc.setBalance(currBalance);
     }
 
      public static void withdraw(Account account, int withdraw ){
        Account acc = getAccountByNumber(account.getAccountNumber());
        int currBalance = acc.getBalance();

        if(currBalance - withdraw > 0){
            currBalance -= withdraw;
            acc.setBalance(currBalance);
        }
        else{
            System.out.println("Amount withdrawn is greater than current balance");
        }

}
}
