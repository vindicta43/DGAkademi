package com.alperen.w_02.utils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alperen.w_02.models.CardModel;
import com.alperen.w_02.models.PaymentModel;
import com.alperen.w_02.models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alperen on 15.08.2022.
 */
public class FirebaseRepository {
    public static void signUp(String email, String password, INetworkStatus network) {
        network.processing();
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    network.success("signUp");
                })
                .addOnFailureListener(e -> {
                    network.fail("Error", e.getMessage());
                });
    }

    public static void signIn(String email, String password, INetworkStatus network) {
        network.processing();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    network.success("signIn");
                })
                .addOnFailureListener(e -> {
                    network.fail("Error", e.getMessage());
                });
    }

    public static void sendResetMail(String email, INetworkStatus network) {
        network.processing();
        FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnSuccessListener(runnable -> {
                    network.success("resetMail");
                })
                .addOnFailureListener(e -> {
                    network.fail("Error", e.getMessage());
                });
    }

    public static LiveData<List<ProductModel>> getAllProducts() {
        MutableLiveData<List<ProductModel>> result = new MutableLiveData<>();
        FirebaseDatabase.getInstance()
                .getReference("products")
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    List<ProductModel> temp = new ArrayList<>();
                    Log.d("dataSnapshot", dataSnapshot.getChildren().toString());
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        temp.add(item.getValue(ProductModel.class));
                    }
                    result.postValue(temp);
                });

        return result;
    }

    public static void payWithCard(CardModel card, List<ProductModel> productModels, INetworkStatus network) {
        FirebaseDatabase.getInstance()
                .getReference("payments")
                .child(FirebaseAuth.getInstance().getUid())
                .child(UUID.randomUUID().toString())
                .setValue(new PaymentModel(card, productModels))
                .addOnSuccessListener(runnable -> {
                    network.success("success");
                })
                .addOnFailureListener(e -> {
                    network.fail("Error", e.getMessage());
                });
    }
}
