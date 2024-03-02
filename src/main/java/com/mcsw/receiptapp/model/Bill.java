package com.mcsw.receiptapp.model;

import java.util.Date;

import com.mcsw.receiptapp.controller.bill.BillDto;

public class Bill {
    private String id;
    private String userEmail;
    private String company;
    private Date billingDate;
    private Date deadLine;
    private String debt;
    private String paymentStatus;

    public Bill(){}

    public Bill(String id, String customerEmail, String company, Date biDate, Date deadDate, String debt, String state){
        this.id = id;
        this.userEmail = customerEmail;
        this.company = company;
        this.billingDate = biDate;
        this.deadLine = deadDate;
        this.debt = debt;
        this.paymentStatus = state;
    }

    public Bill(BillDto dto){
        this.userEmail = dto.getUserEmail();
        this.company = dto.getCompany();
        this.billingDate = new Date();
        this.deadLine = dto.getDeadLine();
        this.debt = dto.getDebt();
        this.paymentStatus = "PENDIENTE";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Bill [id=" + id + ", userEmail=" + userEmail + ", company=" + company + ", billingDate=" + billingDate
                + ", deadLine=" + deadLine + ", debt=" + debt + ", paymentStatus=" + paymentStatus + "]";
    }
}
