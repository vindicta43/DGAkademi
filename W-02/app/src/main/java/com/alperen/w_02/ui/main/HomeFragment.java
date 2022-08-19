package com.alperen.w_02.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.w_02.databinding.FragmentHomeBinding;
import com.alperen.w_02.models.ProductModel;
import com.alperen.w_02.utils.IRecycleViewEvent;
import com.alperen.w_02.utils.MainRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    IRecycleViewEvent event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        event = new IRecycleViewEvent() {
            @Override
            public void addItemToCart(ProductModel item) {
                MainActivity activity = (MainActivity) getActivity();
                activity.cartList.add(item);
                activity.setBadgeCount();
            }
        };


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<ProductModel> list = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("products")
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    Log.d("dataSnapshot", dataSnapshot.getChildren().toString());
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        list.add(item.getValue(ProductModel.class));
                    }

                    MainRecyclerAdapter adapter = new MainRecyclerAdapter(list, event);
                    RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    binding.recyclerMain.setAdapter(adapter);
                    binding.recyclerMain.setLayoutManager(manager);
                });
    }
}