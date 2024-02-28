package com.mcsw.receiptapp.model;

import java.util.Date;

import com.mcsw.receiptapp.controller.bill.BillDto;

public class Bill {
    private String paymentID;
    private String costumerID;
    private String company;
    private Date billingDate;
    private Date deadLine;
    private String debtToPay;
    private String state;

    public Bill(){}

    public Bill(String id, String customer, String company, Date biDate, Date deadDate, String debt, String state){
        this.paymentID = id;
        this.costumerID = customer;
        this.company = company;
        this.billingDate = biDate;
        this.deadLine = deadDate;
        this.debtToPay = debt;
        this.state = state;
    }

    public Bill(BillDto dto){
        this.paymentID = dto.getPaymentID();
        this.costumerID = dto.getCostumerID();
        this.company = dto.getCompany();
        this.billingDate = new Date();
        this.deadLine = new Date();
        this.debtToPay = dto.getDebtToPay();
        this.state = dto.getState();
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

    public String getCostumer() {
        return costumerID;
    }

    public void setCostumer(String costumer) {
        this.costumerID = costumer;
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

    public String getDebtToPay() {
        return debtToPay;
    }

    public void setDebtToPay(String debtToPay) {
        this.debtToPay = debtToPay;
    }

    @Override
    public String toString() {
        return "Bill [paymentID=" + paymentID + ", costumerID=" + costumerID + ", company=" + company + ", billingDate="
                + billingDate + ", deadLine=" + deadLine + ", debtToPay=" + debtToPay + ", state=" + state + "]";
    }
}
