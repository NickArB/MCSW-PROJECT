package com.mcsw.receiptapp.model;
import java.util.Date;

import com.mcsw.receiptapp.controller.payment.PaymentGatewayDto;

public class PaymentGateway {
    private String paymentId;
    private Date deadLine;
    private String finalState;

    public PaymentGateway(String paymentId, Date deadLine, String finalState) {
        this.paymentId = paymentId;
        this.deadLine = deadLine;
        this.finalState = finalState;
    }

    public PaymentGateway() {
    }

    public PaymentGateway(PaymentGatewayDto dto){
        Long dateDiff = dto.getDeadLine().getTime() - new Date().getTime();
        this.paymentId = dto.getPaymentId();
        this.deadLine = dto.getDeadLine();
        this.finalState = (dateDiff >= 0) ? "OK" : "PAGO TARDIO";
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    @Override
    public String toString() {
        return "PaymentGateway [paymentId=" + paymentId + ", deadLine=" + deadLine + ", finalState=" + finalState + "]";
    }
}
