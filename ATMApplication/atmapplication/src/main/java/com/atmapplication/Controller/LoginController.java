package com.atmapplication.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpHeaders;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atmapplication.DomainObject.Account;
import com.atmapplication.Model.AccountModel;

@RestController
public class LoginController {
    @GetMapping("/login")
    public ResponseEntity<InputStreamResource> loginPage() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/login.html");
        String htmlContent = new String(htmlFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .body(new InputStreamResource(new ByteArrayInputStream(htmlContent.getBytes())));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam int accountNumber, @RequestParam String password) { 
        Account account = AccountModel.getAccountByNumber(accountNumber);
        if (account != null) {
            // Redirect to the account page after successful login
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/account?accountNumber=" + accountNumber));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

}