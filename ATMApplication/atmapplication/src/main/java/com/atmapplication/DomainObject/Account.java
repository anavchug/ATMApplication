package com.atmapplication.DomainObject;

public class Account {
    private int accountNumber;
    private String password;
    private int balance;
    
    public Account(int accountNumber, int balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public int getAccountNumber(){
        return accountNumber;
    }

    public int getBalance(){
        return balance;
    }
    public void setBalance(int newBalance){
        this.balance = newBalance;
    }
    public String getPassword(){
        return password;
    }
  
}

