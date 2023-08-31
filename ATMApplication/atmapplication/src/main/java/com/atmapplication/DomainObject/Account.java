package com.atmapplication.DomainObject;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountNumber;
    private String password;
    private int balance;

    private List<Transaction> transactionHistory;
    
    public Account(int accountNumber, int balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public Account(int accountNumber, String password, int balance){
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;

        // Initialize transaction history list
        transactionHistory = new ArrayList<>();
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
    
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
  
}

