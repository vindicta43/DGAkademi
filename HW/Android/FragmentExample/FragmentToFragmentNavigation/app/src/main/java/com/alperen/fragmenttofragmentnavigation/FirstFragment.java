package com.alperen.fragmenttofragmentnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FirstFragment extends Fragment {
    EditText etData;
    Button btnSendDataNavigation;

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
        btnSendDataNavigation = getView().findViewById(R.id.btnSendDataNavigation);

        btnSendDataNavigation.setOnClickListener(v -> {
            String data = etData.getText().toString().isEmpty() ? "null" : etData.getText().toString();

            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(data);
            action.setResult(data);
            Navigation.findNavController(v).navigate(action);
        });
    }
}