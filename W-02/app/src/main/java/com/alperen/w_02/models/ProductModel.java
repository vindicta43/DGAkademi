package com.alperen.w_02.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alperen on 18.08.2022.
 */
public class ProductModel implements Parcelable {
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

    public ProductModel(Parcel parcel) {
        String[] data = new String[6];

        parcel.readStringArray(data);
        this.id = data[0];
        this.name = data[1];
        this.image = data[2];
        this.price = Double.parseDouble(data[3]);
        this.weight = Double.parseDouble(data[4]);
        this.count = Integer.parseInt(data[5]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{this.id,
                this.name,
                this.image,
                String.valueOf(this.price),
                String.valueOf(this.weight),
                String.valueOf(this.count)});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<ProductModel>() {

        @Override
        public ProductModel createFromParcel(Parcel parcel) {
            return new ProductModel(parcel);
        }

        @Override
        public ProductModel[] newArray(int i) {
            return new ProductModel[i];
        }
    };
}
