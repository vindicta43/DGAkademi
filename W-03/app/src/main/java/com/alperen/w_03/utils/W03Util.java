package com.alperen.w_03.utils;

import android.content.Context;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by Alperen on 24.08.2022.
 */
public class W03Util {
    public static void setEditTextError(String errorText, EditText editText) {
        editText.setError(errorText);
    }

    public static void createDialog(String title, String msg, Context context) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Okay", (dialogInterface, i) -> {})
                .show();
    }
}
