package com.mcsw.receiptapp.controller.card;

public class CardDto {
    String accountNumber;
    String expirationDate;
    String type;
    String cvc;
    String ownerName;

    public CardDto() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getType() {
        return type;
    }

    public String getCvc() {
        return cvc;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
