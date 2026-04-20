package com.example.kvitkanails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);

        // Back button
        Button backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Instagram click listener
        TextView tvInstagram = findViewById(R.id.tvInstagram);
        tvInstagram.setOnClickListener(v -> {
            String instagramUrl = "https://www.instagram.com/kvitka_nails_studio";
            Uri uri = Uri.parse(instagramUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        // Open Map button
        Button openMapButton = findViewById(R.id.btnOpenMap);
        openMapButton.setOnClickListener(v -> {
            // Coordinates for Bré, Co. Wicklow (примерные координаты)
            String latitude = "53.1014";
            String longitude = "-6.0675";
            Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + Uri.encode("Novara Ave, Bré, Ireland"));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }
}