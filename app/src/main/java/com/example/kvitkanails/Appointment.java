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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Appointment extends AppCompatActivity {

    private TextView tvMonthYear;
    private GridView calendarGrid;
    private Button btnPrevMonth, btnNextMonth, btnNext;
    private Calendar currentCalendar;
    private CalendarAdapter calendarAdapter;
    private List<String> dayList;
    private String selectedDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointment);

        // Back button
        Button backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Initialize views
        tvMonthYear = findViewById(R.id.tvMonthYear);
        calendarGrid = findViewById(R.id.calendarGrid);
        btnPrevMonth = findViewById(R.id.btnPrevMonth);
        btnNextMonth = findViewById(R.id.btnNextMonth);
        btnNext = findViewById(R.id.btnNext);

        // Initialize calendar
        currentCalendar = Calendar.getInstance();
        updateCalendar();

        // Month navigation
        btnPrevMonth.setOnClickListener(v -> {
            currentCalendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });

        btnNextMonth.setOnClickListener(v -> {
            currentCalendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });

        // Next button
        btnNext.setOnClickListener(v -> {
            if (selectedDate != null) {
                Intent intent = new Intent(Appointment.this, TimeAppointment.class);
                intent.putExtra("selected_date", selectedDate);
                startActivity(intent);
            } else {
                Toast.makeText(Appointment.this, "Please select a date", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCalendar() {
        String monthYear = currentCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)
                + " " + currentCalendar.get(Calendar.YEAR);
        tvMonthYear.setText(monthYear);

        dayList = getDaysInMonth(currentCalendar);

        // Используем упрощенный адаптер
        calendarAdapter = new CalendarAdapter(this, dayList);
        calendarGrid.setAdapter(calendarAdapter);

        calendarGrid.setOnItemClickListener((parent, view, position, id) -> {
            String day = dayList.get(position);
            if (!day.isEmpty()) {
                selectedDate = day + " " + monthYear;
                ((CalendarAdapter)calendarAdapter).setSelectedPosition(position);
                Toast.makeText(this, "Selected: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> getDaysInMonth(Calendar calendar) {
        List<String> days = new ArrayList<>();

        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);

        // Get first day of month (0 = Sunday, 1 = Monday, etc.)
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Add empty days for previous month
        for (int i = 0; i < firstDayOfWeek; i++) {
            days.add("");
        }

        // Add days of current month
        int maxDays = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDays; i++) {
            days.add(String.valueOf(i));
        }

        return days;
    }
}