package com.example.kvitkanails;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    private TextView totalPriceValue;

    // Manicure section
    private CheckBox cbManicureClassic;
    private CheckBox cbRemovalClassic;

    // Shellac section
    private CheckBox cbShellac;
    private CheckBox cbManicureShellac;
    private CheckBox cbRemovalShellac;

    // Standalone removal
    private CheckBox cbRemovalOnly;

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

        // Initialize views
        totalPriceValue = findViewById(R.id.totalPriceValue);

        // Manicure section
        cbManicureClassic = findViewById(R.id.cbManicureClassic);
        cbRemovalClassic = findViewById(R.id.cbRemoval_Classic);

        // Shellac section
        cbShellac = findViewById(R.id.cbShellac);
        cbManicureShellac = findViewById(R.id.cbManicureClassic_Shellac);
        cbRemovalShellac = findViewById(R.id.cbRemoval);

        // Standalone removal
        cbRemovalOnly = findViewById(R.id.cbRemovalOnly);

        // === LOGIC FOR MANICURE SECTION ===
        // When manicure is selected, removal is auto-checked
        if (cbManicureClassic != null) {
            cbManicureClassic.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && cbRemovalClassic != null && !cbRemovalClassic.isChecked()) {
                    cbRemovalClassic.setChecked(true);
                }
                calculateTotal();
            });
        }

        // Removal can be unchecked independently
        if (cbRemovalClassic != null) {
            cbRemovalClassic.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        // === LOGIC FOR SHELLAC SECTION ===
        // When Shellac is selected, auto-check Manicure + Removal
        if (cbShellac != null) {
            cbShellac.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (cbManicureShellac != null && !cbManicureShellac.isChecked()) {
                        cbManicureShellac.setChecked(true);
                    }
                    if (cbRemovalShellac != null && !cbRemovalShellac.isChecked()) {
                        cbRemovalShellac.setChecked(true);
                    }
                } else {
                    // If Shellac is unchecked, uncheck Manicure and Removal too
                    if (cbManicureShellac != null && cbManicureShellac.isChecked()) {
                        cbManicureShellac.setChecked(false);
                    }
                    if (cbRemovalShellac != null && cbRemovalShellac.isChecked()) {
                        cbRemovalShellac.setChecked(false);
                    }
                }
                calculateTotal();
            });
        }

        // If Manicure in Shellac section is unchecked, uncheck Shellac
        if (cbManicureShellac != null) {
            cbManicureShellac.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (!isChecked && cbShellac != null && cbShellac.isChecked()) {
                    cbShellac.setChecked(false);
                }
                calculateTotal();
            });
        }

        // If Removal in Shellac section is unchecked, Shellac remains (no auto-uncheck)
        if (cbRemovalShellac != null) {
            cbRemovalShellac.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        // Standalone removal listener
        if (cbRemovalOnly != null) {
            cbRemovalOnly.setOnCheckedChangeListener((buttonView, isChecked) -> calculateTotal());
        }

        calculateTotal();
    }

    private void calculateTotal() {
        total = 0;

        // Manicure section: Classic Manicure 30€ + Removal 5€
        if (cbManicureClassic != null && cbManicureClassic.isChecked()) {
            total += 30;
            if (cbRemovalClassic != null && cbRemovalClassic.isChecked()) {
                total += 5;
            }
        }

        // Shellac section: Shellac 40€ + Manicure 30€ + Removal 5€
        if (cbShellac != null && cbShellac.isChecked()) {
            total += 5;
            if (cbManicureShellac != null && cbManicureShellac.isChecked()) {
                total += 30;
            }
            if (cbRemovalShellac != null && cbRemovalShellac.isChecked()) {
                total += 5;
            }
        }

        // Standalone removal: 10€
        if (cbRemovalOnly != null && cbRemovalOnly.isChecked()) {
            total += 10;
        }

        // Update display
        if (totalPriceValue != null) {
            totalPriceValue.setText(String.valueOf(total));
        }
    }
}