package com.alperen.w_02.utils;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Alperen on 15.08.2022.
 */
public class FirebaseRepository {
    public static void signUp(String email, String password, IAuthInterface iAuthInterface) {
        iAuthInterface.processing();
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    iAuthInterface.success("signUp");
                })
                .addOnFailureListener(e -> {
                    iAuthInterface.fail("Error", e.getMessage());
                });
    }

    public static void signIn(String email, String password, IAuthInterface iAuthInterface) {
        iAuthInterface.processing();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    iAuthInterface.success("signIn");
                })
                .addOnFailureListener(e -> {
                    iAuthInterface.fail("Error", e.getMessage());
                });
    }

    public static void sendResetMail(String email, IAuthInterface iAuthInterface) {
        iAuthInterface.processing();
        FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnSuccessListener(runnable -> {
                    iAuthInterface.success("resetMail");
                })
                .addOnFailureListener(e -> {
                    iAuthInterface.fail("Error", e.getMessage());
                });
    }
}
