package com.example.kvitkanails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class TimeAppointment extends AppCompatActivity {

    private TextView tvSelectedDate;
    private GridView timeGrid;
    private Button btnConfirm;
    private String selectedDate;
    private String selectedTime = null;
    private TimeAdapter timeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time_appointment);

        // Get selected date from previous activity
        selectedDate = getIntent().getStringExtra("selected_date");

        // Back button
        Button backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Initialize views
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        timeGrid = findViewById(R.id.timeGrid);
        btnConfirm = findViewById(R.id.btnConfirm);

        // Display selected date
        if (selectedDate != null) {
            tvSelectedDate.setText("Selected date: " + selectedDate);
        } else {
            tvSelectedDate.setText("No date selected");
        }

        // Generate time slots (9:00 to 19:00 with 30 min intervals)
        List<String> timeSlots = generateTimeSlots();

        // Set up adapter
        timeAdapter = new TimeAdapter(this, timeSlots);
        timeGrid.setAdapter(timeAdapter);

        // Handle time selection
        timeGrid.setOnItemClickListener((parent, view, position, id) -> {
            selectedTime = timeSlots.get(position);
            timeAdapter.setSelectedPosition(position);
            Toast.makeText(this, "Selected: " + selectedTime, Toast.LENGTH_SHORT).show();
        });

        // Confirm button
        btnConfirm.setOnClickListener(v -> {
            if (selectedTime != null) {
                String message = "Appointment confirmed for " + selectedDate + " at " + selectedTime;
                Toast.makeText(TimeAppointment.this, message, Toast.LENGTH_LONG).show();

                // Return to main activity
                Intent intent = new Intent(TimeAppointment.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(TimeAppointment.this, "Please select a time", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> generateTimeSlots() {
        List<String> slots = new ArrayList<>();
        for (int hour = 9; hour <= 18; hour++) {
            // Add :00 slot
            slots.add(String.format("%02d:00", hour));
            // Add :30 slot (except for 19:00 which is closing)
            if (hour < 18) {
                slots.add(String.format("%02d:30", hour));
            }
        }
        // Add 19:00 as last slot
        slots.add("19:00");
        return slots;
    }
}