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
    private CheckBox cbManicureClassic;
    private CheckBox cbManicureClassicShellac;
    private CheckBox cbShellac;
    private CheckBox cbRemoval;
    private CheckBox cbRemoval_Classic;
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
        //cbManicure = findViewById(R.id.cbManicureClassic);
        cbRemoval = findViewById(R.id.cbRemoval);

        cbManicureClassic = findViewById(R.id.cbManicureClassic);
        cbRemoval_Classic = findViewById(R.id.cbRemoval_Classic);

        cbManicureClassicShellac = findViewById(R.id.cbManicureClassic_Shellac);
        cbShellac = findViewById(R.id.cbShellac);


        // Setting listeners for checkboxes
        if (cbManicure != null) {
            cbManicure.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        if (cbRemoval != null) {
            cbRemoval.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        if (cbManicureClassic != null) {
            cbManicureClassic.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        if (cbRemoval_Classic != null) {
            cbRemoval_Classic.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        if (cbManicureClassicShellac != null) {
            cbManicureClassicShellac.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        if (cbShellac != null) {
            cbShellac.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        // Calculate total

        //Initial calculation
        calculateTotal();
    }

    private void calculateTotal() {
        total = 0;

        // Classic Manicure - 30 €
        if (cbManicure != null && cbManicure.isChecked()) {
            total += 30;
        }

        // Gel Removal - 5 €
        if (cbRemoval != null && cbRemoval.isChecked()) {
            total += 5;
        }

        // Classic Manicure + Gel Removal = 30 € + Shellac - 5 €
        if (cbManicureClassic != null && cbManicureClassic.isChecked()) {
            total += 30;
        }

        if (cbManicureClassicShellac != null && cbManicureClassicShellac.isChecked()) {
            total += 30;
        }

        if (cbRemoval_Classic != null && cbRemoval_Classic.isChecked()) {
            total += 5;
        }

        if (cbShellac != null && cbShellac.isChecked()) {
            total += 5;
        }

        // Update display
        if (totalPriceValue != null) {
            totalPriceValue.setText(String.valueOf(total));
        }
    }
}