package com.atmapplication.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmapplication.DomainObject.Account;
import com.atmapplication.Model.AccountModel;

@RestController
public class TransactionController {

    @PostMapping("/deposit")
    public ResponseEntity<String> depositFunds(@RequestParam int accountNumber, @RequestParam int depositAmount) {
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println("Old balance of account " +accountNumber +": "+ account.getBalance());
            AccountModel.deposit(account, depositAmount);
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
            System.out.println("New balance of account " +accountNumber + ": " + account.getBalance());
            return ResponseEntity.ok("Withdrawn successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }
}
