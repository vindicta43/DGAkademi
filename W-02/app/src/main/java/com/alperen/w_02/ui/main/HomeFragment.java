package com.alperen.w_02.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alperen.w_02.databinding.FragmentHomeBinding;
import com.alperen.w_02.models.ProductModel;
import com.alperen.w_02.utils.FirebaseRepository;
import com.alperen.w_02.utils.IRecyclerViewEvent;
import com.alperen.w_02.utils.MainRecyclerAdapter;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    IRecyclerViewEvent event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        event = new IRecyclerViewEvent() {
            @Override
            public void addItemToCart(ProductModel item) {
                MainActivity activity = (MainActivity) getActivity();
                activity.cartList.add(item);
                activity.setBadgeCount();
            }

            @Override
            public void removeItemFromCart(ProductModel item) {

            }
        };

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        FirebaseRepository.getAllProducts().observe(getViewLifecycleOwner(), productModels -> {
            if (productModels != null && productModels.size() > 0) {
                MainRecyclerAdapter adapter = new MainRecyclerAdapter(productModels, event);
                RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                binding.recyclerMain.setAdapter(adapter);
                binding.recyclerMain.setLayoutManager(manager);
            }
        });
    }
}