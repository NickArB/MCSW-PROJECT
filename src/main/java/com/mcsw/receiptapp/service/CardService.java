package com.mcsw.receiptapp.service;

import com.mcsw.receiptapp.controller.card.CardDto;
import com.mcsw.receiptapp.model.Card;
import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.repository.CardRepository;

public class CardService {
    CardRepository cardRepository = new CardRepository();

    public Card findCardByUserId(String userId, CardDto cardDto){
        Card card = cardRepository.findByUserId(userId, new Card(cardDto));
        if (card.getType().equals(cardDto.getType())){
            return card;
        }
        return null;
    }
}
