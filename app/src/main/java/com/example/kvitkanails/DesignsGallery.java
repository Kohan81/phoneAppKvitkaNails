package com.example.kvitkanails;

import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class DesignsGallery extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button prevButton;
    private Button nextButton;

    // Add your design photos here
    private int[] designPhotos = {
            R.drawable.design1,
            R.drawable.design2,
            R.drawable.design3,
            R.drawable.design4,
            R.drawable.design5,
            R.drawable.design6,
            R.drawable.design7,
            R.drawable.design8,
            R.drawable.design9,
            R.drawable.design10,
            R.drawable.design11,
            R.drawable.design12,
            R.drawable.design13
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_designs_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back button
        Button backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // ViewPager setup
        viewPager = findViewById(R.id.viewPager);
        PhotoAdapter adapter = new PhotoAdapter(designPhotos);
        viewPager.setAdapter(adapter);

        // Navigation buttons
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);

        prevButton.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem > 0) {
                viewPager.setCurrentItem(currentItem - 1);
            }
        });

        nextButton.setOnClickListener(v -> {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem < designPhotos.length - 1) {
                viewPager.setCurrentItem(currentItem + 1);
            }
        });

        // Update button visibility when page changes
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateButtonsVisibility(position);
            }
        });

        updateButtonsVisibility(0);
    }

    private void updateButtonsVisibility(int currentPosition) {
        if (currentPosition == 0) {
            prevButton.setAlpha(0.3f);
        } else {
            prevButton.setAlpha(0.6f);
        }

        if (currentPosition == designPhotos.length - 1) {
            nextButton.setAlpha(0.3f);
        } else {
            nextButton.setAlpha(0.6f);
        }
    }
}