package com.mcsw.receiptapp.controller.bill;

import java.util.Date;

public class BillDto {
    private String userEmail;
    private String company;
    private String debt;
    private Date deadLine;
    private String paymentStatus;

    public BillDto(){}

    public Date getDeadLine() {
        return deadLine;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getCompany() {
        return company;
    }

    public String getDebt() {
        return debt;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "BillDto{" +
                "userEmail='" + userEmail + '\'' +
                ", company='" + company + '\'' +
                ", debt='" + debt + '\'' +
                ", deadLine=" + deadLine +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}