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

import java.util.Arrays;

public class SummaryFragment extends Fragment {
    FragmentSummaryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSummaryBinding.inflate(getLayoutInflater());
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
                        // TODO: buy instant
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
        binding.tvSummaryPrice.setText("$" + String.valueOf(summaryPrice));
    }

    private void openPaymentPage() {

    }
}