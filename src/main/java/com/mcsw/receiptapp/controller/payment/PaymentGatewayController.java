package com.mcsw.receiptapp.controller.payment;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.service.PaymentGatewayService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping( "/payments" )
public class PaymentGatewayController {

    private final PaymentGatewayService paymentService = new PaymentGatewayService();
    
    @PostMapping
    public ResponseEntity<PaymentGateway> create(@RequestBody PaymentGatewayDto paymentDto) throws ParseException{
        paymentDto = sanitize(paymentDto);
        return ResponseEntity.ok(paymentService.payBill(new PaymentGateway(paymentDto)));
    }

    private PaymentGatewayDto sanitize(PaymentGatewayDto input) throws ParseException{
        input.setPaymentId(Jsoup.clean(input.getPaymentId(), Safelist.relaxed()));
        return input;
    }
}
