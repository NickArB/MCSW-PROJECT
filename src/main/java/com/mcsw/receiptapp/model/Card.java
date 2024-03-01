package com.mcsw.receiptapp.model;

import com.mcsw.receiptapp.controller.card.CardDto;

public class Card {
    String accountNumber;
    String expirationDate;
    String type;
    String cvc;
    String ownerName;
    String ownerId;

    public Card() {

    }

    public Card(String accountNumber, String expirationDate, String type, String cvc, String ownerName, String ownerId ) {
        this.accountNumber = accountNumber;
        this.expirationDate = expirationDate;
        this.type = type;
        this.cvc = cvc;
        this.ownerName = ownerName;
        this.ownerId = ownerId;
    }

    public Card(CardDto cardDto){
        this.accountNumber = cardDto.getAccountNumber();
        this.expirationDate = cardDto.getExpirationDate();
        this.type = cardDto.getType();
        this.cvc = cardDto.getCvc();
        this.ownerName = cardDto.getOwnerName();
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
