package com.atmapplication.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmapplication.DomainObject.Account;
import com.atmapplication.DomainObject.Transaction;
import com.atmapplication.Model.AccountModel;

@RestController
public class TransactionController {

    @PostMapping("/deposit")
    public ResponseEntity<String> depositFunds(@RequestParam int accountNumber, @RequestParam int depositAmount) {
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println("Old balance of account " +accountNumber +": "+ account.getBalance());
            AccountModel.deposit(account, depositAmount);

            // Add a deposit transaction to the account's history
            Transaction deposit = new Transaction("Deposit", depositAmount, LocalDateTime.now());
            account.addTransaction(deposit);

            System.out.println("Transaction time: " + deposit.getLocalDateTime());
            System.out.println("New balance of account " +accountNumber + ":" + account.getBalance());
            return ResponseEntity.ok("Deposited successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFunds(@RequestParam int accountNumber, @RequestParam int withdrawAmount) {
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println("Old balance of account " +accountNumber + ": " + account.getBalance());
            AccountModel.withdraw(account, withdrawAmount);

            // Add a withdrawal transaction to the account's history
            account.addTransaction(new Transaction("Withdrawal", withdrawAmount, LocalDateTime.now()));

            System.out.println("New balance of account " +accountNumber + ": " + account.getBalance());
            return ResponseEntity.ok("Withdrawn successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    @GetMapping("/transaction-history")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@RequestParam int accountNumber) {
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            List<Transaction> transactionHistory = account.getTransactionHistory();
            return ResponseEntity.ok(transactionHistory);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
