package com.alperen.w_02.models;

/**
 * Created by Alperen on 20.08.2022.
 */
public class CardModel {
    public String cardNumber;
    public String cardDate;
    public String cardCvv;

    public CardModel(String cardNumber, String cardDate, String cardCvv) {
        this.cardNumber = cardNumber;
        this.cardDate = cardDate;
        this.cardCvv = cardCvv;
    }
}
