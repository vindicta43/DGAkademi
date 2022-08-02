package com.alperen.w_01;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alperen.w_01.models.base.Car;
import com.alperen.w_01.models.cars.BMW;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BMW araba = new BMW("yeni kasa", 150, 50);

        Log.d("car", String.valueOf(araba.getEngine().getEngineCount()));
        Log.d("car", String.valueOf(araba.getEngine().getHorsePower()));

    }
}