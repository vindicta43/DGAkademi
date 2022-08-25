package com.alperen.w_03.repository.locale;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alperen.w_03.model.CardModel;
import com.alperen.w_03.model.RecentTransactionsModel;
import com.alperen.w_03.model.UpcomingPaymentsModel;

/**
 * Created by Alperen on 25.08.2022.
 */
@Database(entities = {CardModel.class, RecentTransactionsModel.class, UpcomingPaymentsModel.class}, version = 1)
public abstract class W03Database extends RoomDatabase {
    public abstract W03Dao w03Dao();

    private static W03Database instance = null;

    public static W03Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context,
                            W03Database.class,
                            "app.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
