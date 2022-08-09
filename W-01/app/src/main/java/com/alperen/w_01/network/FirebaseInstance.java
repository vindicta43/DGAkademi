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

    public static void login(String email, String password, Activity activity) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                })
                .addOnFailureListener(runnable -> {
                    setMessage("Error", runnable.getMessage(), activity);
                });
    }

    public static void register(String email, String password, Activity activity) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    setMessage("Success", "Account created successfully", activity);
                })
                .addOnFailureListener(runnable -> {
                    setMessage("Error", runnable.getMessage(), activity);
                });
    }

    public static void produceCar(Vehicle car, Activity activity) {
        UUID randomId = UUID.randomUUID();
        db.getReference("cars")
                .child(randomId.toString())
                .setValue(car)
                .addOnSuccessListener(runnable -> {
                    setMessage("Success", "Car added successfully", activity);
                })
                .addOnFailureListener(runnable -> {
                    setMessage("Error", runnable.getMessage(), activity);
                });
    }

    public static void clearDb(Activity activity) {
        db.getReference()
                .removeValue()
                .addOnSuccessListener(runnable -> {
                    setMessage("Success", "Database cleared successfully", activity);
                })
                .addOnFailureListener(runnable -> {
                    setMessage("Error", runnable.getMessage(), activity);
                });
    }

    // Set error message or situation (success of fail)
    private static void setMessage(String title, String message, Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Okay", (dialogInterface, i) -> {
                })
                .create()
                .show();
    }
}
