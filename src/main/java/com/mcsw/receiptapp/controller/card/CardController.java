package com.mcsw.receiptapp.controller.card;

import com.mcsw.receiptapp.controller.payment.PaymentGatewayDto;
import com.mcsw.receiptapp.exception.IncorrectCardInformationException;
import com.mcsw.receiptapp.exception.InsufficientFundsException;
import com.mcsw.receiptapp.exception.InvalidCardException;
import com.mcsw.receiptapp.model.Card;
import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.model.PaymentInfo;
import com.mcsw.receiptapp.service.CardService;
import com.mcsw.receiptapp.service.PaymentGatewayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/cards" )
public class CardController {
    private final CardService cardService = new CardService();

    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable String userId, @RequestBody PaymentInfo paymentInfo) {
        CardDto cardDto = paymentInfo.getCardDto();
        int billDebt = Integer.parseInt(paymentInfo.getBillDebt());
        try {
            Card card = cardService.checkCardByUserId(userId, cardDto, billDebt);
            return ResponseEntity.ok(card);
        } catch (InvalidCardException e) {
            return new ResponseEntity<>("Error, no existe la tarjeta indicada, verifique el número de cuenta y si es débito o crédito.", HttpStatus.NOT_FOUND);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>("No tiene fondos suficientes para pagar la factura.", HttpStatus.CONFLICT);
        } catch (IncorrectCardInformationException e) {
            return new ResponseEntity<>("Los datos de la tarjeta son incorrectos.", HttpStatus.CONFLICT);
        }

    }
}
