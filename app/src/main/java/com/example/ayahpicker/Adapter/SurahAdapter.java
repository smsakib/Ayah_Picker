package com.example.ayahpicker.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayahpicker.R;
import com.example.ayahpicker.Models.SurahModel;

import java.util.ArrayList;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    Context context;
    public static ArrayList<SurahModel> arrayListSurah;
//    public static ArrayList<SurahBanglaModel> surahBanglaModels;

    public SurahAdapter(Context context, ArrayList<SurahModel> arrayListSurah){
        this.context = context;
        this.arrayListSurah = arrayListSurah;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.surah_child_item,parent,false);
        SurahViewHolder surahViewHolder = new SurahViewHolder(view);
        return surahViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SurahViewHolder holder, int position) {
        SurahModel currentSurahItem = arrayListSurah.get(position);

        String arabicRcvText = currentSurahItem.getArabicText();
        String bangalRcvText = currentSurahItem.getBanglaText();
        holder.textViewArabic.setText(arabicRcvText);
        holder.textViewBangla.setText(bangalRcvText);
        holder.textViewArabic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", holder.textViewArabic.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context,"copied",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        holder.textViewBangla.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", holder.textViewBangla.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context,"copied",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayListSurah.size();
    }


    public class SurahViewHolder extends RecyclerView.ViewHolder{

        TextView textViewArabic;
        TextView textViewBangla;
        CardView cardViewSurahHolder;

        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewArabic = itemView.findViewById(R.id.textViewArabic);
            textViewBangla = itemView.findViewById(R.id.textViewBangla);
            cardViewSurahHolder = itemView.findViewById(R.id.surah_card_id);
        }
    }
}
