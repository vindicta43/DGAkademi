package com.alperen.fragmenttofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    TextView tvData;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvData = getView().findViewById(R.id.tvData);

        // Data transfer with setFragmentResult
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
            String resultText = result.getString("data");
            tvData.setText(resultText);
        });

        // Data transver with bundle
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String resultText = bundle.getString("bundleData");
            tvData.setText(resultText);
        }
    }
}