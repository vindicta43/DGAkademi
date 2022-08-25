package com.alperen.w_03.model;

/**
 * Created by Alperen on 24.08.2022.
 */
public class RecentTransactionsModel {
    private String transactionName;
    private String transactionDate;
    private String transactionPrice;

    public RecentTransactionsModel() {}

    public RecentTransactionsModel(String transactionName, String transactionDate, String transactionPrice) {
        this.transactionName = transactionName;
        this.transactionDate = transactionDate;
        this.transactionPrice = transactionPrice;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}
