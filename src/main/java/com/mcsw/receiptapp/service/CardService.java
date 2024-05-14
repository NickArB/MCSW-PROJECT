package com.mcsw.receiptapp.service;

import com.mcsw.receiptapp.controller.card.CardDto;
import com.mcsw.receiptapp.exception.IncorrectCardInformationException;
import com.mcsw.receiptapp.exception.InsufficientFundsException;
import com.mcsw.receiptapp.exception.InvalidCardException;
import com.mcsw.receiptapp.model.Card;
import com.mcsw.receiptapp.model.PaymentGateway;
import com.mcsw.receiptapp.repository.CardRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CardService {
    CardRepository cardRepository = new CardRepository();

    public Card checkCardByUserId(String userId, CardDto cardDto, int billDebt) throws InvalidCardException, InsufficientFundsException, IncorrectCardInformationException{
        Card card = cardRepository.findByUserId(userId, new Card(cardDto));
        System.out.println(userId);
        System.out.println(cardDto.getAccountNumber());
        System.out.println(BCrypt.hashpw( cardDto.getAccountNumber(), BCrypt.gensalt() ));
        if (card != null && card.getType().equals(cardDto.getType())) {
            if (!card.getExpirationDate().equals(cardDto.getExpirationDate().split("T")[0])) {
                throw new IncorrectCardInformationException();
            }
            if (card.getType().equals("credito")) {
                if (!BCrypt.checkpw( cardDto.getCvc(), card.getCvc() )){
                    throw new IncorrectCardInformationException();
                }
                if ((!card.getOwnerName().equals(cardDto.getOwnerName()))) {
                    throw new IncorrectCardInformationException();
                }

            }
            if (card.getAvailableBalance() < billDebt) {
                throw new InsufficientFundsException();
            }
            return card;
        } else {
            throw new InvalidCardException();
        }

    }
}
