package com.alperen.w_03.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Alperen on 24.08.2022.
 */
@Entity(tableName = "recent_transactions")
public class RecentTransactionsModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id = 0;

    @ColumnInfo(name = "transaction_name")
    private String transactionName;

    @ColumnInfo(name = "transaction_date")
    private String transactionDate;

    @ColumnInfo(name = "transaction_price")
    private double transactionPrice;

    public RecentTransactionsModel() {}

    public RecentTransactionsModel(String transactionName, String transactionDate, double transactionPrice) {
        this.transactionName = transactionName;
        this.transactionDate = transactionDate;
        this.transactionPrice = transactionPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTransactionPrice() {
        return transactionPrice;
    }

    // Formatted price by comma
    public String getTransactionPriceFormatted() {
        return NumberFormat.getNumberInstance(Locale.US).format(transactionPrice);
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}
