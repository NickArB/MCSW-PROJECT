package com.mcsw.receiptapp.controller.bill;

public class BillDto {
    private String paymentID;
    private String costumerID;
    private String company;
    private String debtToPay;
    private String state;

    public BillDto(){}

    public String getCostumerID() {
        return costumerID;
    }

    public String getCompany() {
        return company;
    }

    public String getDebtToPay() {
        return debtToPay;
    }

    public String getState() {
        return state;
    }

    public String getPaymentID() {
        return paymentID;
    }

    @Override
    public String toString() {
        return "BillDto [paymentID=" + paymentID + ", costumerID=" + costumerID + ", company=" + company
                + ", debtToPay=" + debtToPay + ", state=" + state + "]";
    }
    
}
