package com.mcsw.receiptapp.controller.card;

import com.mcsw.receiptapp.model.Card;
import com.mcsw.receiptapp.service.CardService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/cards" )
public class CardController {
    private final CardService cardService = new CardService();

    @PostMapping("/{userId}")
    public ResponseEntity<Card> create(@PathVariable String userId, @RequestBody CardDto cardDto){
        cardDto = sanitize(cardDto);
        Card card = cardService.findCardByUserId(userId, cardDto);
        if (card != null) {
            return ResponseEntity.ok(cardService.findCardByUserId(userId, cardDto));
        }
        return ResponseEntity.notFound().build();
    }

    private CardDto sanitize(CardDto input){
        input.setAccountNumber(Jsoup.clean(input.getAccountNumber(), Safelist.relaxed()));
        input.setCvc(Jsoup.clean(input.getCvc(), Safelist.relaxed()));
        input.setExpirationDate(Jsoup.clean(input.getExpirationDate(), Safelist.relaxed()));
        input.setOwnerName(Jsoup.clean(input.getOwnerName(), Safelist.relaxed()));
        input.setType(Jsoup.clean(input.getType(), Safelist.relaxed()));
        return input;
    }
}
