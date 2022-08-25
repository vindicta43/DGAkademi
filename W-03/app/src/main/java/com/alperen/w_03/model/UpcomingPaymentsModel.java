package com.alperen.w_03.model;

/**
 * Created by Alperen on 24.08.2022.
 */
public class UpcomingPaymentsModel {
    private String paymentName;
    private String paymentType;
    private String paymentPrice;

    public UpcomingPaymentsModel() {}

    public UpcomingPaymentsModel(String paymentName, String paymentType, String paymentPrice) {
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.paymentPrice = paymentPrice;
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

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }
}
