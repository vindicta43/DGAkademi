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
}
