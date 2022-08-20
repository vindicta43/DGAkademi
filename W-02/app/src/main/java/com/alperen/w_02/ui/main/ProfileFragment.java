package com.alperen.w_02.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentProfileBinding;
import com.alperen.w_02.utils.FirebaseRepository;
import com.alperen.w_02.utils.INetworkStatus;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.tvUserDetail.setText(FirebaseRepository.getUserProfile());

        binding.btnPastPayments.setOnClickListener(view -> {
            binding.progress.setVisibility(View.VISIBLE);
            FirebaseRepository.getPastPayments().observe(getViewLifecycleOwner(), s -> {
                if (s != null || !s.isEmpty()) {
                    binding.progress.setVisibility(View.GONE);
                    new AlertDialog.Builder(getContext())
                            .setMessage(s)
                            .setPositiveButton("Okay", (dialogInterface, i) -> {})
                            .show();
                } else {
                    binding.progress.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No purchases found", Toast.LENGTH_SHORT).show();
                }
            });
        });

        binding.btnLogout.setOnClickListener(view -> {
            FirebaseRepository.signOut();
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_authActivity);
        });
    }
}