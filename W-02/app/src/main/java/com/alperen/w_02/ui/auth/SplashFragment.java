package com.alperen.w_02.ui.auth;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alperen.w_02.R;
import com.alperen.w_02.databinding.FragmentSplashBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SplashFragment extends Fragment {
    private FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.splashAnim.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null)
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_mainActivity);
                else
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_signInFragment);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}