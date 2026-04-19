package com.example.kvitkanails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Services & Prices button
        Button servicesButton = findViewById(R.id.button);
        servicesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
            startActivity(intent);
        });

        // Designs Gallery button
        Button designsButton = findViewById(R.id.button4);
        designsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DesignsGallery.class);
            startActivity(intent);
        });

        // My Account button
        Button accountButton = findViewById(R.id.button3);
        accountButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyAccount.class);
            startActivity(intent);
        });

        // Location button
        Button locationButton = findViewById(R.id.button2);
        locationButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Location.class);
            startActivity(intent);
        });
    }
}