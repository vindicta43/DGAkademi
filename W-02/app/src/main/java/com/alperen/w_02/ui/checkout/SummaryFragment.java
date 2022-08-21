package com.alperen.w_02.ui.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentSummaryBinding;
import com.alperen.w_02.models.ProductModel;
import com.alperen.w_02.ui.main.MainActivity;
import com.alperen.w_02.utils.FirebaseRepository;
import com.alperen.w_02.utils.INetworkStatus;
import com.alperen.w_02.utils.W02Util;

import java.util.Arrays;

public class SummaryFragment extends Fragment {
    FragmentSummaryBinding binding;
    INetworkStatus network;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSummaryBinding.inflate(getLayoutInflater());

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
                        .setMessage("Pass payment succeed")
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


        ProductModel[] productModels = CheckoutActivityArgs.fromBundle(getActivity().getIntent().getExtras()).getProductList();
        CheckoutRecyclerAdapter adapter = new CheckoutRecyclerAdapter(Arrays.asList(productModels));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.recyclerCheckout.setAdapter(adapter);
        binding.recyclerCheckout.setLayoutManager(manager);

        setSummaryText(productModels);

        binding.btnMakeCheckout.setOnClickListener(view -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Payment Method")
                    .setMessage(getResources().getString(R.string.checkout_method))
                    .setPositiveButton("Card", (dialogInterface, i) -> {
                        FragmentManager fragmentManager = getParentFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.containerCheckout, CardPaymentFragment.class, null)
                                .addToBackStack(null)
                                .commit();
                    })
                    .setNegativeButton("Pass payment (Debug)", (dialogInterface, i) -> {
                        FirebaseRepository.passPayment(Arrays.asList(productModels), network);
                    })
                    .show();
        });
    }

    private void setSummaryText(ProductModel[] productModels) {
        int summaryCount = 0;
        double summaryWeight = 0;
        double summaryPrice = 0;
        for (ProductModel model : productModels) {
            summaryPrice += model.price;
            summaryWeight += model.weight;
            summaryCount += model.count;
        }

        binding.tvSummaryCount.setText(String.valueOf(summaryCount));
        binding.tvSummaryWeight.setText(String.valueOf(summaryWeight));
        binding.tvSummaryPrice.setText(String.format("$%.2f", summaryPrice));
    }

    private void openPaymentPage() {

    }
}