package com.alperen.w_01.network;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.alperen.w_01.models.base.Vehicle;
import com.alperen.w_01.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

/**
 * Created by Alperen on 3.08.2022.
 */
public class FirebaseInstance {
    private static final FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();

    public static void login(String email, String password, Activity loginActivity) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    Intent intent = new Intent(loginActivity, MainActivity.class);
                    loginActivity.startActivity(intent);
                })
                .addOnFailureListener(runnable -> {
                    new AlertDialog.Builder(loginActivity)
                            .setTitle("Error")
                            .setMessage(runnable.getMessage())
                            .setPositiveButton("Okay", (dialogInterface, i) -> {
                            })
                            .create()
                            .show();
                });
    }

    public static void register(String email, String password, Activity activity) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    setMessage("Account created successfully", activity);
                })
                .addOnFailureListener(runnable -> {
                    setMessage(runnable.getMessage(), activity);
                });
    }

    public static void produceCar(Vehicle car, Activity activity) {
        UUID randomId = UUID.randomUUID();
        db.getReference("cars")
                .child(randomId.toString())
                .setValue(car)
                .addOnSuccessListener(runnable -> {
                    setMessage("Car added successfully", activity);
                })
                .addOnFailureListener(runnable -> {
                    setMessage(runnable.getMessage(), activity);
                });
    }

    public static void clearDb(Activity activity) {
        db.getReference()
                .removeValue()
                .addOnSuccessListener(runnable -> {
                    setMessage("Database cleared successfully", activity);
                })
                .addOnFailureListener(runnable -> {
                    setMessage(runnable.getMessage(), activity);
                });
    }

    private static void setMessage(String errorText, Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Error")
                .setMessage(errorText)
                .setPositiveButton("Okay", (dialogInterface, i) -> {
                })
                .create()
                .show();
    }
}
