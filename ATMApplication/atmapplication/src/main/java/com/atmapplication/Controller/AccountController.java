package com.atmapplication.Controller; // Updated package name to follow Java conventions

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmapplication.DomainObject.Account;
import com.atmapplication.Model.AccountModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class AccountController {

    @GetMapping("/account")
    public ResponseEntity<InputStreamResource> accountPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("com/atmapplication/resources/static/account.html");
        String htmlContent = new String(htmlFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        
        int initialBalance = AccountModel.getInitialBalance();
        System.out.println("Initial Balance: " + initialBalance);
        htmlContent = htmlContent.replace("{{initialBalance}}", String.valueOf(initialBalance));

        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(new ByteArrayInputStream(htmlContent.getBytes())));
    }
    @PostMapping("/deposit")
    public ResponseEntity<String> depositFunds(@RequestParam int accountNumber, @RequestParam int depositAmount) {
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println("Old balance of account" +accountNumber + " was " + account.getBalance());
            AccountModel.deposit(account, depositAmount);
            System.out.println("New balance of account " +accountNumber + " is " + account.getBalance());
            return ResponseEntity.ok("Deposited successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFunds(@RequestParam int accountNumber, @RequestParam int withdrawAmount) {
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println("Old balance of account" +accountNumber + " was " + account.getBalance());
            AccountModel.withdraw(account, withdrawAmount);
            System.out.println("New balance of account " +accountNumber + " is " + account.getBalance());
            return ResponseEntity.ok("Withdrawn successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

}

