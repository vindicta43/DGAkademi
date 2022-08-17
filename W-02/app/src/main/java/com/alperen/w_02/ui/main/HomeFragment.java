package com.alperen.w_02.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentHomeBinding;
import com.alperen.w_02.utils.MainRecyclerAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<String> list = new ArrayList<String>();

        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");

        MainRecyclerAdapter adapter = new MainRecyclerAdapter(list);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerMain.setAdapter(adapter);
        binding.recyclerMain.setLayoutManager(manager);
    }
}