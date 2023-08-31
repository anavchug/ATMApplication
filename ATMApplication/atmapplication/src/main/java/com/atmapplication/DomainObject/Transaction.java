package com.atmapplication.DomainObject;

import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private int amount;
    private LocalDateTime localDateTime;

    public Transaction(String type, int amount, LocalDateTime localDateTime) {
        this.type = type;
        this.amount = amount;
        this.localDateTime = localDateTime;
    }
    
    public String getType() {
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public LocalDateTime getLocalDateTime(){
        return localDateTime;
    }
    
}

