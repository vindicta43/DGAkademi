package com.alperen.w_02.utils;

import com.alperen.w_02.models.ProductModel;

/**
 * Created by Alperen on 18.08.2022.
 */
public interface IRecyclerViewEvent {
    void addItemToCart(ProductModel item);
    void removeItemFromCart(ProductModel item);
}
