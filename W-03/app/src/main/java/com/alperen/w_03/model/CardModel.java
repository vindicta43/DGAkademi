package com.alperen.w_03.model;

/**
 * Created by Alperen on 24.08.2022.
 */
public class CardModel {
    private String cardNumber;
    private String cardHolderName;

    public CardModel() {}

    public CardModel(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
