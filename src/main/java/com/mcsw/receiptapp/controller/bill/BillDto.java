package com.mcsw.receiptapp.controller.bill;

import java.util.Date;

public class BillDto {
    private String id;
    private String userEmail;
    private String company;
    private String debt;
    private Date deadLine;
    private String paymentStatus;

    public BillDto(){}

    public Date getDeadLine() {
        return deadLine;
    }

    public String getId() {
        return id;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public String toString() {
        return "BillDto [id=" + id + ", userEmail=" + userEmail + ", company=" + company + ", debt=" + debt
                + ", deadLine=" + deadLine + ", paymentStatus=" + paymentStatus + "]";
    }
}