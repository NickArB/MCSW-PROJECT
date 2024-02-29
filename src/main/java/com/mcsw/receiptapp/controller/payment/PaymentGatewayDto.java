package com.mcsw.receiptapp.controller.payment;
import java.util.Date;

public class PaymentGatewayDto {
    private String paymentId;
    private Date deadLine;
    
    public PaymentGatewayDto() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    @Override
    public String toString() {
        return "PaymentGatewayDto [paymentId=" + paymentId + ", deadLine=" + deadLine + "]";
    }
}
