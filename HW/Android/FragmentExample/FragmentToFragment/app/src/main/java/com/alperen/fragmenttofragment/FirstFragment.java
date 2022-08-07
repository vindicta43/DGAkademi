package com.alperen.fragmenttofragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    EditText etData;
    Button btnSendDataFragmentResult, btnSendDataBundle;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etData = getView().findViewById(R.id.etData);
        btnSendDataFragmentResult = getView().findViewById(R.id.btnSendDataFragmentResult);
        btnSendDataBundle = getView().findViewById(R.id.btnSendDataBundle);

        // Data transfer with setFragmentResult
        btnSendDataFragmentResult.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            String data = etData.getText().toString().isEmpty() ? "null fragment result" : etData.getText().toString();
            bundle.putString("data", data);
            getParentFragmentManager().setFragmentResult("requestKey", bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, SecondFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        });

        // Data transver with bundle
        btnSendDataBundle.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            String data = etData.getText().toString().isEmpty() ? "null bundle" : etData.getText().toString();
            bundle.putString("bundleData", data);

            SecondFragment secondFragment = new SecondFragment();
            secondFragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, secondFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}