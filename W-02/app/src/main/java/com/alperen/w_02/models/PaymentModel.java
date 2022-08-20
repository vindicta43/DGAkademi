package com.alperen.w_02.models;

import java.util.List;

/**
 * Created by Alperen on 20.08.2022.
 */
public class PaymentModel {
    public CardModel card;
    public List<ProductModel> cartList;

    public PaymentModel(CardModel card, List<ProductModel> cartList) {
        this.card = card;
        this.cartList = cartList;
    }

    public PaymentModel() {
    }

    @Override
    public String toString() {
        if (card == null) {
            return "Card: " + "(Pass payment method)" + "\n" +
                    "Shopping Cart: " + cartList.toString() + "\n";
        } else {
            return "Card: " + card.toString() + "\n" +
                    "Shopping Cart: " + cartList.toString() + "\n\n";
        }
    }
}
