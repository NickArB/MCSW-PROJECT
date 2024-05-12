package com.mcsw.receiptapp.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.mcsw.receiptapp.controller.card.CardDto;

public class Card {
    String accountNumber;
    String expirationDate;
    String type;
    String cvc;
    String ownerName;
    String ownerId;
    int availableBalance;

    public Card() {

    }

    public Card(String accountNumber, String expirationDate, String type, String cvc, String ownerName, String ownerId, int availableBalance) {
        this.accountNumber = accountNumber;
        this.expirationDate = expirationDate;
        this.type = type;
        this.cvc = cvc;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
        this.availableBalance = availableBalance;
    }

    public Card(CardDto cardDto){
        this.accountNumber = BCrypt.hashpw( cardDto.getAccountNumber(), BCrypt.gensalt() );
        this.expirationDate = cardDto.getExpirationDate();
        this.type = cardDto.getType();
        if (this.type.equals("credito")) {
            this.cvc = BCrypt.hashpw(cardDto.getCvc(), BCrypt.gensalt());
        } else {
            this.cvc = null;
        }

        this.ownerName = cardDto.getOwnerName();
        this.availableBalance = cardDto.getAvailableBalance();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "accountNumber='" + accountNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", type='" + type + '\'' +
                ", cvc='" + cvc + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
