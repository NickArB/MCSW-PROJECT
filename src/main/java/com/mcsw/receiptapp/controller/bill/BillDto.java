package com.mcsw.receiptapp.controller.bill;

public class BillDto {
    private String id;
    private String userEmail;
    private String company;
    private String debt;
    private String paymentStatus;

    public BillDto(){}

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
                + ", paymentStatus=" + paymentStatus + "]";
    }
    
}
