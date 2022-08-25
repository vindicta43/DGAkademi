package com.alperen.w_03.ui.homepage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alperen.w_03.R;
import com.alperen.w_03.databinding.FragmentHomepageBinding;
import com.alperen.w_03.repository.locale.W03Dao;
import com.alperen.w_03.repository.locale.W03Database;
import com.alperen.w_03.repository.network.FirebaseRepository;
import com.alperen.w_03.ui.homepage.adapters.DashboardViewPagerAdapter;
import com.alperen.w_03.ui.homepage.adapters.RecentTransactionsAdapter;
import com.alperen.w_03.ui.homepage.adapters.UpcomingPaymentsAdapter;
import com.alperen.w_03.utils.W03Util;
import com.github.techisfun.android.topsheet.TopSheetDialog;

public class HomepageFragment extends Fragment {
    FragmentHomepageBinding binding;
    W03Dao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(getLayoutInflater());
        dao = W03Database.getInstance(getContext()).w03Dao();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            W03Util.fillDb(getContext());
            initRecyclerViews();
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.ibMenu.setOnClickListener(this::createMenu);
        initRecyclerViews();
    }

    private void createMenu(View v) {
        TopSheetDialog dialog = new TopSheetDialog(v.getContext());
        dialog.setContentView(R.layout.layout_menu);

        ImageButton ibCancel = dialog.findViewById(R.id.ibCancel);
        LinearLayout menuItemOverview = dialog.findViewById(R.id.menuItemOverview);
        LinearLayout menuItemMessages = dialog.findViewById(R.id.menuItemMessages);
        LinearLayout menuItemCommunity = dialog.findViewById(R.id.menuItemCommunity);
        LinearLayout menuItemPayments = dialog.findViewById(R.id.menuItemPayments);
        LinearLayout menuItemStatistics = dialog.findViewById(R.id.menuItemStatistics);
        LinearLayout menuItemReferrals = dialog.findViewById(R.id.menuItemReferrals);
        LinearLayout menuItemFillDb = dialog.findViewById(R.id.menuItemFillDb);
        LinearLayout menuItemCleanDb = dialog.findViewById(R.id.menuItemCleanDb);
        LinearLayout menuItemLogout = dialog.findViewById(R.id.menuItemLogout);

        ibCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        menuItemOverview.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "Overview clicked", Toast.LENGTH_SHORT).show();
        });

        menuItemMessages.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "Messages clicked", Toast.LENGTH_SHORT).show();
        });

        menuItemCommunity.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "Community clicked", Toast.LENGTH_SHORT).show();
        });

        menuItemPayments.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "Payments clicked", Toast.LENGTH_SHORT).show();
        });

        menuItemStatistics.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "Statistics clicked", Toast.LENGTH_SHORT).show();
        });

        menuItemReferrals.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "Referrals clicked", Toast.LENGTH_SHORT).show();
        });

        menuItemFillDb.setOnClickListener(view -> {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                W03Util.fillDb(v.getContext());
                Toast.makeText(v.getContext(), "Database filled", Toast.LENGTH_SHORT).show();
                initRecyclerViews();
            });
        });

        menuItemCleanDb.setOnClickListener(view -> {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                W03Util.cleanDb(v.getContext());
                Toast.makeText(v.getContext(), "Database cleaned", Toast.LENGTH_SHORT).show();
                initRecyclerViews();
            });
        });

        menuItemLogout.setOnClickListener(view -> {
            dialog.dismiss();
            FirebaseRepository.signOut();
            Navigation.findNavController(v).navigate(R.id.action_homepageFragment_to_signInFragment);
        });

        dialog.show();
    }

    private void initRecyclerViews() {
        DashboardViewPagerAdapter adapter = new DashboardViewPagerAdapter(dao.getCards());
        binding.vpCard.setAdapter(adapter);

        UpcomingPaymentsAdapter adapter2 = new UpcomingPaymentsAdapter(dao.getUpcomingPayments());
        binding.rvUpcomingPayments.setAdapter(adapter2);
        binding.rvUpcomingPayments.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RecentTransactionsAdapter adapter3 = new RecentTransactionsAdapter(dao.getRecentTransactions());
        binding.rvRecentTransactions.setAdapter(adapter3);
        binding.rvRecentTransactions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}