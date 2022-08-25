package com.alperen.w_03.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alperen.w_03.R;
import com.alperen.w_03.model.UpcomingPaymentsModel;

/**
 * Created by Alperen on 25.08.2022.
 */
public class UpcomingPaymentDialogFragment extends DialogFragment {
    UpcomingPaymentsModel model;

    public UpcomingPaymentDialogFragment(UpcomingPaymentsModel model) {
        this.model = model;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.layout_payment_dialog, container, false);

        TextView tvPaymentDialogName = view.findViewById(R.id.tvPaymentDialogName);
        TextView tvPaymentDialogType = view.findViewById(R.id.tvPaymentDialogType);
        TextView tvDialogPrice = view.findViewById(R.id.tvDialogPrice);
        EditText etDialogSenderName = view.findViewById(R.id.etDialogSenderName);
        EditText etDialogReceiveDate = view.findViewById(R.id.etDialogReceiveDate);
        ImageButton ibCancelDialog = view.findViewById(R.id.ibCancelDialog);

        tvPaymentDialogName.setText(model.getPaymentName());
        tvPaymentDialogType.setText(model.getPaymentType());
        tvDialogPrice.setText("$" + model.getPaymentPriceFormatted());
        etDialogSenderName.setText(model.getPaymentSender());
        etDialogReceiveDate.setText(model.getPaymentDate());

        ibCancelDialog.setOnClickListener(view1 -> {
            getDialog().dismiss();
        });

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }
}
