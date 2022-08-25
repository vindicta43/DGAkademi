package com.alperen.w_03.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Alperen on 24.08.2022.
 */
@Entity(tableName = "upcoming_payments")
public class UpcomingPaymentsModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id = 0;

    @ColumnInfo(name = "payment_name")
    private String paymentName;

    @ColumnInfo(name = "payment_type")
    private String paymentType;

    @ColumnInfo(name = "payment_price")
    private double paymentPrice;

    @ColumnInfo(name = "payment_date")
    private String paymentDate;

    @ColumnInfo(name = "payment_sender")
    private String paymentSender;

    public UpcomingPaymentsModel() {}

    public UpcomingPaymentsModel(String paymentName, String paymentType, double paymentPrice, String paymentDate, String paymentSender) {
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.paymentPrice = paymentPrice;
        this.paymentDate = paymentDate;
        this.paymentSender = paymentSender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getPaymentPrice() {
        return paymentPrice;
    }

    // Formatted price by comma
    public String getPaymentPriceFormatted() {
        return NumberFormat.getNumberInstance(Locale.US).format(paymentPrice);
    }

    public void setPaymentPrice(double paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentSender() {
        return paymentSender;
    }

    public void setPaymentSender(String paymentSender) {
        this.paymentSender = paymentSender;
    }
}
