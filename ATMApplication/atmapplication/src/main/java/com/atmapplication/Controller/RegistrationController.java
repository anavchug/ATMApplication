package com.atmapplication.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.atmapplication.DomainObject.Account;
import com.atmapplication.Model.AccountModel;

@RestController
public class RegistrationController {
    @GetMapping("/register")
    public ResponseEntity<InputStreamResource> registerPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("com/atmapplication/resources/static/register.html");
        String htmlContent = new String(htmlFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(new ByteArrayInputStream(htmlContent.getBytes())));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam int accountNumber, @RequestParam String password) {
        // Check if the account already exists
        if (AccountModel.getAccountByNumber(accountNumber) != null) {
            return ResponseEntity.badRequest().body("Account already exists");
        }

        Account newAccount = new Account(accountNumber, password, 0); // Set initial balance to 0
        AccountModel.addAccount(newAccount);
        System.out.println("Account with number" + accountNumber + " created succesfully");
        
        // Redirect to the login page after successful registration
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login")); // Set the login page URL
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER); // Use SEE_OTHER status for redirection
    }
}
