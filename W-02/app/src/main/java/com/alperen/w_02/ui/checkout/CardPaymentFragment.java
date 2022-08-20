package com.alperen.w_02.ui.checkout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentCardPaymentBinding;
import com.alperen.w_02.models.CardModel;
import com.alperen.w_02.models.ProductModel;
import com.alperen.w_02.ui.main.MainActivity;
import com.alperen.w_02.utils.FirebaseRepository;
import com.alperen.w_02.utils.INetworkStatus;
import com.alperen.w_02.utils.W02Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardPaymentFragment extends Fragment {
    FragmentCardPaymentBinding binding;
    private String fieldRequired = "";
    INetworkStatus network;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCardPaymentBinding.inflate(getLayoutInflater());
        fieldRequired = getResources().getString(R.string.required_field);

        network = new INetworkStatus() {
            @Override
            public void processing() {
                binding.progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void success(String action) {
                binding.progress.setVisibility(View.GONE);
                new AlertDialog.Builder(getContext())
                        .setTitle("Success")
                        .setMessage("Shopping completed")
                        .setPositiveButton("Okay", (dialogInterface, i) -> {
                            MainActivity.cartList.clear();
                            MainActivity.setBadgeCount();
                            getActivity().finish();
                        }).show();
            }

            @Override
            public void fail(String title, String msg) {
                binding.progress.setVisibility(View.GONE);
                W02Util.setDialog(getContext(), title, msg);
            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.btnPayCreditCard.setOnClickListener(view -> {
            String cardNumber = binding.etCardNumber.getText().toString();
            String cardCvv = binding.etCardCvv.getText().toString();
            String cardDate = binding.etCardDate.getText().toString();

            if (!cardNumber.isEmpty() && !cardCvv.isEmpty() && !cardDate.isEmpty()) {
                ProductModel[] productModels = CheckoutActivityArgs.fromBundle(getActivity().getIntent().getExtras()).getProductList();
                List<ProductModel> products = new ArrayList<ProductModel>(Arrays.asList(productModels));
                CardModel card = new CardModel(cardNumber, cardDate, cardCvv);
                FirebaseRepository.payWithCard(card, products, network);
            } else {
                W02Util.setError(true, binding.layoutCardNumber, fieldRequired);
                W02Util.setError(true, binding.layoutCardCvv, fieldRequired);
                W02Util.setError(true, binding.layoutCardDate, fieldRequired);
            }
        });

        binding.etCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                W02Util.setError(charSequence.length() == 0, binding.layoutCardNumber, fieldRequired);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etCardDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                W02Util.setError(charSequence.length() == 0, binding.layoutCardDate, fieldRequired);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etCardCvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                W02Util.setError(charSequence.length() == 0, binding.layoutCardCvv, fieldRequired);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}