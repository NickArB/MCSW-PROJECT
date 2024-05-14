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

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    
    public int getAvailableBalance() { return availableBalance; }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

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
