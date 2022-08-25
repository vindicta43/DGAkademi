package com.alperen.w_03.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Alperen on 24.08.2022.
 */
@Entity(tableName = "cards")
public class CardModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id = 0;

    @ColumnInfo(name = "card_number")
    private String cardNumber;

    @ColumnInfo(name = "card_holder_name")
    private String cardHolderName;

    public CardModel() {}

    public CardModel(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
