package com.alperen.w_01.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alperen.w_01.R;
import com.alperen.w_01.models.base.CarEnum;
import com.alperen.w_01.models.base.Vehicle;
import com.alperen.w_01.models.cars.Car;
import com.alperen.w_01.network.FirebaseInstance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvInfo, tvDatabase;
    Button btnAddBmw, btnAddMercedes, btnAddAudi, btnClearDb;
    EditText etCarName, etHorsePower, etTirePressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnAddBmw.setOnClickListener(this);
        btnAddMercedes.setOnClickListener(this);
        btnAddAudi.setOnClickListener(this);
        btnClearDb.setOnClickListener(this);
    }

    private void setError(String errorText) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorText)
                .setPositiveButton("Okay", (dialogInterface, i) -> {
                })
                .create()
                .show();
    }

    private void initViews() {
        tvInfo = findViewById(R.id.tvInfo);
        tvDatabase = findViewById(R.id.tvDatabase);
        btnAddBmw = findViewById(R.id.btnAddBmw);
        btnAddMercedes = findViewById(R.id.btnAddMercedes);
        btnAddAudi = findViewById(R.id.btnAddAudi);
        btnClearDb = findViewById(R.id.btnClearDb);
        etCarName = findViewById(R.id.etCarName);
        etHorsePower = findViewById(R.id.etHorsePower);
        etTirePressure = findViewById(R.id.etTirePressure);

        // Firebase user id
        tvInfo.setText(FirebaseAuth.getInstance().getUid());

        // Get data from database and change textView
        getDb();
    }

    // A basic observer for database
    private void getDb() {
        FirebaseDatabase.getInstance().getReference("cars").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Vehicle> cars = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Car singleCar = data.getValue(Car.class);
                            cars.add(singleCar);
                        }

                        tvDatabase.setText(cars.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        setError(error.getMessage());
                    }
                });
    }

    @Override
    public void onClick(View view) {
        String carName = etCarName.getText().toString();
        String hp = etHorsePower.getText().toString();
        String pressure = etTirePressure.getText().toString();

        // Clear db button click listener
        if (view.getId() == R.id.btnClearDb) {
            FirebaseInstance.clearDb(this);
            return;
        }

        // If all fields are filled write data on database
        if (!carName.isEmpty() && !hp.isEmpty() && !pressure.isEmpty()) {
            int intHp = Integer.parseInt(hp);
            int intPressure = Integer.parseInt(pressure);
            switch (view.getId()) {
                case R.id.btnAddBmw:
                    FirebaseInstance.produceCar(new Car(intHp, 2, intPressure, 6, 2, carName, CarEnum.BMW), this);
                    break;

                case R.id.btnAddMercedes:
                    FirebaseInstance.produceCar(new Car(intHp, 2, intPressure, 8, 2, carName, CarEnum.MERCEDES), this);
                    break;

                case R.id.btnAddAudi:
                    FirebaseInstance.produceCar(new Car(intHp, 1, intPressure, 4, 5, carName, CarEnum.AUDI), this);
                    break;
            }
        } else {
            String errorText = "";
            if (carName.isEmpty())
                errorText += "Car name field is empty\n";

            if (hp.isEmpty())
                errorText += "Horse power field is empty\n";

            if (pressure.isEmpty())
                errorText += "Tire pressure field is empty\n";

            setError(errorText);
        }
    }
}