package com.mcsw.receiptapp.controller.payment;
import java.util.Date;

public class PaymentGatewayDto {
    private String paymentId;
    private Date deadLine;
    private String debt;
    private String accountNumber;
    
    public PaymentGatewayDto() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Date getDeadLine() {
        return deadLine;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getDebt() {
        return debt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "PaymentGatewayDto [paymentId=" + paymentId + ", deadLine=" + deadLine + "]";
    }
}
