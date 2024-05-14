package com.mcsw.receiptapp.model;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.mcsw.receiptapp.controller.payment.PaymentGatewayDto;

public class PaymentGateway {
    private String paymentId;
    private Date deadLine;
    private String finalState;
    private String debt;
    private String accountNumber;


    public PaymentGateway(String paymentId, Date deadLine, String finalState, String debt, String accountNumber) {
        this.paymentId = paymentId;
        this.deadLine = deadLine;
        this.finalState = finalState;
        this.debt = debt;
        this.accountNumber = accountNumber;
    }

    public PaymentGateway() {
    }

    public PaymentGateway(PaymentGatewayDto dto){
        Long dateDiff = dto.getDeadLine().getTime() - new Date().getTime();
        this.paymentId = dto.getPaymentId();
        this.deadLine = dto.getDeadLine();
        this.finalState = (dateDiff >= 0) ? "OK" : "PAGO TARDIO";
        this.debt = dto.getDebt();
        this.accountNumber = dto.getAccountNumber();
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

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "PaymentGateway [paymentId=" + paymentId + ", deadLine=" + deadLine + ", finalState=" + finalState + "]";
    }
}
