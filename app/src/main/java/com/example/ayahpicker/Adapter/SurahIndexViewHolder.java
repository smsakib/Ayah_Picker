package com.example.ayahpicker.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayahpicker.R;

public class SurahIndexViewHolder extends RecyclerView.ViewHolder {
    TextView textViewSurahIndex;
    public SurahIndexViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewSurahIndex = itemView.findViewById(R.id.textViewSurah);
    }
}
