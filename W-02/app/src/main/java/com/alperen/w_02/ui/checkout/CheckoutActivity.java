package com.alperen.w_02.ui.checkout;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.ActivityCheckoutBinding;

public class CheckoutActivity extends FragmentActivity {
    ActivityCheckoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.containerCheckout, SummaryFragment.class, null)
                .commit();


//        ProductModel[] productModels = CheckoutActivityArgs.fromBundle(getIntent().getExtras()).getProductList();
//        CheckoutRecyclerAdapter adapter = new CheckoutRecyclerAdapter(Arrays.asList(productModels));
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        binding.recyclerCheckout.setAdapter(adapter);
//        binding.recyclerCheckout.setLayoutManager(manager);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage(getResources().getString(R.string.cancel_checkout))
                .setNegativeButton("No", (dialogInterface, i) -> {
                })
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    finish();
                })
                .show();
    }
}