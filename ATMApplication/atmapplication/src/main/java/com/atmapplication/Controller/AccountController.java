package com.atmapplication.Controller; // Updated package name to follow Java conventions

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmapplication.DomainObject.Account;
import com.atmapplication.Model.AccountModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class AccountController {

    //displays the account page for a given customer
    @GetMapping("/account")
    public ResponseEntity<InputStreamResource> accountPage(@RequestParam int accountNumber) throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/account.html");
        String htmlContent = new String(htmlFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        
        Account loggedInAccount = AccountModel.getAccountByNumber(accountNumber);
        if (loggedInAccount != null) {
            int initialBalance = loggedInAccount.getBalance();
            System.out.println("Initial Balance: " + initialBalance);
            htmlContent = htmlContent.replace("{{initialBalance}}", String.valueOf(initialBalance));
        } else {
            // Handle the case where the account doesn't exist
            // You could redirect the user to a login page or display an error message
            
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(new ByteArrayInputStream(htmlContent.getBytes())));
    }
}

