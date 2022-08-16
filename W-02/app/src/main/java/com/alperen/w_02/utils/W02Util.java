package com.alperen.w_02.utils;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;

import com.alperen.w_02.R;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by Alperen on 15.08.2022.
 */
public class W02Util {
    public static void setError(boolean isEnabled, TextInputLayout view, String errorText) {
        if (isEnabled) {
            view.setErrorEnabled(true);
            view.setError(errorText);
        } else {
            view.setErrorEnabled(false);
            view.setError(null);
        }
    }

    public static void setDialog(Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Okay", (dialogInterface, i) -> {})
                .show();
    }
}
