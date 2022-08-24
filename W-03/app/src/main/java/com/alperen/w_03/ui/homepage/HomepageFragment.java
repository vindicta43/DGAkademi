package com.alperen.w_03.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentHomepageBinding;
import com.alperen.w_03.model.CardModel;
import com.alperen.w_03.ui.homepage.adapters.HomepageViewPagerAdapter;
import com.github.techisfun.android.topsheet.TopSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class HomepageFragment extends Fragment {
    FragmentHomepageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        List<CardModel> list = new ArrayList<>();
        list.add(new CardModel("123", "a"));
        list.add(new CardModel("456", "s"));
        list.add(new CardModel("789", "d"));
        HomepageViewPagerAdapter adapter = new HomepageViewPagerAdapter(list);

        binding.vpCard.setAdapter(adapter);

        binding.ibMenu.setOnClickListener(view -> {
            TopSheetDialog dialog = new TopSheetDialog(getContext());
            dialog.setContentView(R.layout.layout_menu);
            dialog.show();
        });

    }
}