package com.example.kvitkanails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class TimeAdapter extends ArrayAdapter<String> {

    private List<String> timeSlots;
    private int selectedPosition = -1;
    private Context context;

    public TimeAdapter(Context context, List<String> timeSlots) {
        super(context, android.R.layout.simple_list_item_1, timeSlots);
        this.context = context;
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setPadding(24, 16, 24, 16);
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setTextSize(18);
        } else {
            textView = (TextView) convertView;
        }

        String time = timeSlots.get(position);
        textView.setText(time);

        if (position == selectedPosition) {
            textView.setBackgroundColor(0xFF9C27B0);
            textView.setTextColor(0xFFFFFFFF);
        } else {
            textView.setBackgroundColor(0xFFFFFFFF);
            textView.setTextColor(0xFF230454);
        }

        return textView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }
}