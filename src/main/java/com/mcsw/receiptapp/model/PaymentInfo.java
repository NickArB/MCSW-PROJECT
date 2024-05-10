package com.mcsw.receiptapp.model;

import com.mcsw.receiptapp.controller.card.CardDto;

public class PaymentInfo {
    CardDto cardDto;
    String billDebt;
    public PaymentInfo() {
    }

    public PaymentInfo(CardDto cardDto, String billDebt) {
        this.cardDto = cardDto;
        this.billDebt = billDebt;
    }

    public void setCardDto(CardDto cardDto) {
        this.cardDto = cardDto;
    }

    public void setBillDebt(String billDebt) {
        this.billDebt = billDebt;
    }

    public CardDto getCardDto() {
        return cardDto;
    }

    public String getBillDebt() {
        return billDebt;
    }
}
