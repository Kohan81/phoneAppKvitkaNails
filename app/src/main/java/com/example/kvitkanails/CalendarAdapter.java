package com.example.kvitkanails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;
import java.util.List;

public class CalendarAdapter extends ArrayAdapter<String> {

    private List<String> days;
    private int selectedPosition = -1;
    private Context context;

    public CalendarAdapter(Context context, List<String> days) {
        super(context, android.R.layout.simple_list_item_1, days);
        this.context = context;
        this.days = days;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setPadding(16, 12, 16, 12);
            textView.setGravity(android.view.Gravity.CENTER);
        } else {
            textView = (TextView) convertView;
        }

        String day = days.get(position);
        textView.setText(day);

        if (position == selectedPosition) {
            textView.setBackgroundColor(0xFF9C27B0);
            textView.setTextColor(0xFFFFFFFF);
            textView.setPadding(16, 12, 16, 12);
        } else {
            textView.setBackgroundColor(0x00000000);
            textView.setTextColor(0xFF230454);
        }

        return textView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }
}