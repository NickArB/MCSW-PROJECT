package com.mcsw.receiptapp;

import java.sql.Date;

public class Bill {
    private String paymentID;
    private User costumer;
    private String company;
    private Date billingDate;
    private Date deadLineDate;
    private String debtToPay;
    private String state;

    public Bill(String id, User customer, String company, Date biDate, Date deadDate, String debt, String state){
        this.paymentID = id;
        this.costumer = customer;
        this.company = company;
        this.billingDate = biDate;
        this.deadLineDate = deadDate;
        this.debtToPay = debt;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public User getCostumer() {
        return costumer;
    }

    public void setCostumer(User costumer) {
        this.costumer = costumer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Date getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public String getDebtToPay() {
        return debtToPay;
    }

    public void setDebtToPay(String debtToPay) {
        this.debtToPay = debtToPay;
    }
}
