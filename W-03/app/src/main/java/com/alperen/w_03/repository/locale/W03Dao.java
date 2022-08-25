package com.alperen.w_03.repository.locale;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alperen.w_03.model.CardModel;
import com.alperen.w_03.model.RecentTransactionsModel;
import com.alperen.w_03.model.UpcomingPaymentsModel;

import java.util.List;

/**
 * Created by Alperen on 25.08.2022.
 */
@Dao
public interface W03Dao {
    @Query("select * from cards")
    List<CardModel> getCards();

    @Query("select * from upcoming_payments")
    List<UpcomingPaymentsModel> getUpcomingPayments();

    @Query("select * from recent_transactions")
    List<RecentTransactionsModel> getRecentTransactions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCards(CardModel... cards);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUpcomingPayments(UpcomingPaymentsModel... upcomingPayments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecentTransactions(RecentTransactionsModel... recentTransactions);

    @Query("delete from cards")
    void deleteCards();

    @Query("delete from upcoming_payments")
    void deleteUpcomingPayments();

    @Query("delete from recent_transactions")
    void deleteRecentTransactions();
}
