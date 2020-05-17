package com.example.ayahpicker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayahpicker.R;

import java.util.ArrayList;

public class SurahIndexAdapter extends RecyclerView.Adapter<SurahIndexViewHolder> {
    Context context;
    ArrayList<String> listViewSurahModels;
    onClickInterface onClickInterface;

    public SurahIndexAdapter(Context context, ArrayList<String> listViewSurahModels, onClickInterface onClickInterface) {
        this.context = context;
        this.listViewSurahModels = listViewSurahModels;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public SurahIndexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.surah_item,parent,false);
        SurahIndexViewHolder surahIndexViewHolder = new SurahIndexViewHolder(view);
        return surahIndexViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SurahIndexViewHolder holder, final int position) {
            holder.textViewSurahIndex.setText(listViewSurahModels.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickInterface.setClick(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listViewSurahModels.size();
    }

    public interface onClickInterface {
        void setClick(int abc);
    }
}
