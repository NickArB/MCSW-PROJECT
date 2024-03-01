package com.mcsw.receiptapp.controller.card;

import com.mcsw.receiptapp.controller.payment.PaymentGatewayDto;
import com.mcsw.receiptapp.model.Card;
import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.service.CardService;
import com.mcsw.receiptapp.service.PaymentGatewayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/cards" )
public class CardController {
    private final CardService cardService = new CardService();

    @PostMapping("/{userId}")
    public ResponseEntity<Card> create(@PathVariable String userId, @RequestBody CardDto cardDto){
        Card card = cardService.findCardByUserId(userId, cardDto);
        if (card != null) {
            return ResponseEntity.ok(cardService.findCardByUserId(userId, cardDto));
        }
        return ResponseEntity.notFound().build();

    }
}
