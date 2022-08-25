package com.alperen.w_03.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentHomepageBinding;
import com.alperen.w_03.model.CardModel;
import com.alperen.w_03.model.RecentTransactionsModel;
import com.alperen.w_03.model.UpcomingPaymentsModel;
import com.alperen.w_03.ui.homepage.adapters.DashboardViewPagerAdapter;
import com.alperen.w_03.ui.homepage.adapters.RecentTransactionsAdapter;
import com.alperen.w_03.ui.homepage.adapters.UpcomingPaymentsAdapter;
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

        binding.ibMenu.setOnClickListener(view -> {
            TopSheetDialog dialog = new TopSheetDialog(getContext());
            dialog.setContentView(R.layout.layout_menu);
            dialog.show();
        });

        List<CardModel> list = new ArrayList<>();
        list.add(new CardModel("123", "a"));
        list.add(new CardModel("456", "s"));
        list.add(new CardModel("789", "d"));

        DashboardViewPagerAdapter adapter = new DashboardViewPagerAdapter(list);
        binding.vpCard.setAdapter(adapter);


        List<UpcomingPaymentsModel> list2 = new ArrayList<>();
        list2.add(new UpcomingPaymentsModel("tax", "goverment", "201"));
        list2.add(new UpcomingPaymentsModel("tax2", "goverment2", "202"));
        list2.add(new UpcomingPaymentsModel("tax3", "goverment3", "203"));
        list2.add(new UpcomingPaymentsModel("tax4", "goverment4", "204"));

        UpcomingPaymentsAdapter adapter2 = new UpcomingPaymentsAdapter(list2);
        binding.rvUpcomingPayments.setAdapter(adapter2);
        binding.rvUpcomingPayments.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        List<RecentTransactionsModel> list3 = new ArrayList<>();
        list3.add(new RecentTransactionsModel("taxi1", "03", "$50"));
        list3.add(new RecentTransactionsModel("taxi2", "04", "$51"));
        list3.add(new RecentTransactionsModel("taxi3", "05", "$52"));
        list3.add(new RecentTransactionsModel("taxi4", "06", "$53"));
        list3.add(new RecentTransactionsModel("taxi5", "07", "$54"));
        list3.add(new RecentTransactionsModel("taxi6", "08", "$55"));

        list3.add(new RecentTransactionsModel("taxi6", "08", "$55"));
        list3.add(new RecentTransactionsModel("taxi6", "08", "$55"));

        RecentTransactionsAdapter adapter3 = new RecentTransactionsAdapter(list3);
        binding.rvRecentTransactions.setAdapter(adapter3);
        binding.rvRecentTransactions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}