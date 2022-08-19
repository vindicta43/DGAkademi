package com.alperen.w_02.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_02.databinding.FragmentCartBinding;
import com.alperen.w_02.models.ProductModel;
import com.alperen.w_02.utils.CartRecyclerAdapter;
import com.alperen.w_02.utils.IRecycleViewEvent;

public class CartFragment extends Fragment {
    FragmentCartBinding binding;
    IRecycleViewEvent event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater());

        event = new IRecycleViewEvent() {
            @Override
            public void addItemToCart(ProductModel item) {
                MainActivity activity = (MainActivity) getActivity();
                activity.cartList.add(item);
                activity.setBadgeCount();
            }

            @Override
            public void removeItemFromCart(ProductModel item) {
                MainActivity activity = (MainActivity) getActivity();
                activity.cartList.remove(item);
                activity.setBadgeCount();
            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity activity = (MainActivity) getActivity();
        CartRecyclerAdapter adapter = new CartRecyclerAdapter(activity.cartList, event);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());

        binding.recyclerCart.setLayoutManager(manager);
        binding.recyclerCart.setAdapter(adapter);

        binding.btnCartCheckout.setOnClickListener(view -> {

        });
    }
}