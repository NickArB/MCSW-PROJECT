package com.mcsw.receiptapp.controller.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.service.PaymentGatewayService;

@RestController
@RequestMapping( "/payments" )
public class PaymentGatewayController {

    private final PaymentGatewayService paymentService = new PaymentGatewayService();
    
    @PostMapping
    public ResponseEntity<PaymentGateway> create(@RequestBody PaymentGatewayDto paymentDto){
        return ResponseEntity.ok(paymentService.payBill(new PaymentGateway(paymentDto)));
    }
}
