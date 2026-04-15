package com.example.kvitkanails;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    private TextView totalPriceValue;
    private CheckBox cbManicure;
    private CheckBox cbRemoval;
    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_services);

        // Back button
        Button backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Initialization of elements
        totalPriceValue = findViewById(R.id.totalPriceValue);
        cbManicure = findViewById(R.id.cbManicureClassic);
        cbRemoval = findViewById(R.id.cbRemoval);

        // Setting listeners for checkboxes
        if (cbManicure != null) {
            cbManicure.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        if (cbRemoval != null) {
            cbRemoval.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        //Initial calculation
        calculateTotal();
    }

    private void calculateTotal() {
        total = 0;

        // Classic Manicure - 30 €
        if (cbManicure != null && cbManicure.isChecked()) {
            total += 30;
        }

        // Gel Removal - 10 €
        if (cbRemoval != null && cbRemoval.isChecked()) {
            total += 10;
        }

        // Update display
        if (totalPriceValue != null) {
            totalPriceValue.setText(String.valueOf(total));
        }
    }
}