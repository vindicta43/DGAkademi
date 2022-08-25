package com.alperen.w_03.repository.network;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Alperen on 24.08.2022.
 */
public class FirebaseRepository {

    public static void sendResetEmail(String email, INetworkActions actions) {
        actions.processing();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnSuccessListener(runnable -> {
                    actions.success("Success", "Your reset email has been sent successfully");
                })
                .addOnFailureListener(e -> {
                    actions.fail("Error", e.getMessage());
                });
    }

    public static void signIn(String email, String password, INetworkActions actions) {
        actions.processing();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    actions.success("Success", "Signed in successfully");
                })
                .addOnFailureListener(e -> {
                    actions.fail("Error", e.getMessage());
                });
    }

    public static void signUp(String email, String password, INetworkActions actions) {
        actions.processing();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    actions.success("Success", "Signed up successfully. You can login now");
                })
                .addOnFailureListener(e -> {
                    actions.fail("Error", e.getMessage());
                });
    }
}
