package com.mcsw.receiptapp.controller.card;

public class CardDto {
    String accountNumber;
    String expirationDate;
    String type;
    String cvc;
    String ownerName;
    int availableBalance;

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
    public int getAvailableBalance() { return availableBalance; }

    @Override
    public String toString() {
        return "CardDto{" +
                "accountNumber='" + accountNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", type='" + type + '\'' +
                ", cvc='" + cvc + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", availableBalance=" + availableBalance +
                '}';
    }
}
