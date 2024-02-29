package com.mcsw.receiptapp.service;

import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.repository.PaymentGatewayRepository;

public class PaymentGatewayService {
    private static final PaymentGatewayRepository repo = new PaymentGatewayRepository();

    public PaymentGateway payBill(PaymentGateway payment){
        try {
            repo.applyPayment(payment);
        } catch (Exception e) {
            payment.setFinalState("FALLIDO");
        }
        return payment;
    }
}
