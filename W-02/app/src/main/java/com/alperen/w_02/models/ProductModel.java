package com.alperen.w_02.models;

import androidx.annotation.Nullable;

/**
 * Created by Alperen on 18.08.2022.
 */
public class ProductModel {
    public String id;
    public String name;
    public String image;
    public double price;
    public double weight;
    public int count;

    public ProductModel(String id, String name, String image, double price, double weight, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.weight = weight;
        this.count = count;
    }

    public ProductModel() {
    }

    public String getId() {
        return id;
    }
}
