package com.example.ayahpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ayahpicker.Adapter.SurahIndexAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SurahIndex extends AppCompatActivity {

    ArrayList<String> surahIndexArrayList = new ArrayList<>(Arrays.asList("Al Fatihah","Al Baqara","Al imran","An-Nisa","Al-Ma'idah","Al-An'am",
    "Al-A'raf","Al-Anfal","At-Taubah","Yunus","Hud","Yusuf","Ar-Ra'd","Ibrahim","Al-Hijr","An-Nahl","Bani Isra'il","Al-Kahf",
    "Maryam","Ta Ha","Al-Anbiya","Al-Hajj","Al-Mu'minun","An-Nur","Al-Furqan","Ash-Shu'ara","An-Naml","Al-Qasas","Al-'Ankabut","Ar-Rum",
            "Luqman","As-Sajdah","Al-Ahzab","Al-Saba","Al-Fatir","Ya Sin","As-Saffat","Sad","Az-Zumar","Al-Mu'min","Ha Mim","Ash-Shura",
            "Az-Zukhruf","Ad-Dukhan","Al-Jathiyah","Al-Ahqaf","Muhammad","Al-Fath","Al-Hujurat","Qaf","Ad-Dhariyat","At-Tur","An-Najm",
            "Al-Qamar","Ar-Rahman","Al-Waqi'ah","Al-Hadid","Al-Mujadilah","Al-Hashr","Al-Mumtahanah","As-Saff","Al-Jumu'ah","Al-Munafiqun",
            "At-Taghabun","At-Talaq","At-Tahrim","Al-Mulk","Al-Qalam","Al-Haqqah","Al-Ma'arij","Nuh","Al-Jinn","Al-Muzzammil","Al-Muddaththir",
            "Al-Qiyamah","Al-Insan","Al-Mursalat","An-Naba","An-Nazi'at","'Abasa","At-Takwir","Al-Infitar","At-Tatfif","Al-Inshiqaq","Al-Buruj",
            "At-Tariq","Al-A'la","Al-Ghashiyah","Al-Fajr","Al-Balad","Ash-Shams","Al-Lail","Ad-Duha","Al-Inshirah","At-Tin","Al-'Alaq",
            "Al-Qadr","Al-Bayyinah","Al-Zilzal","Al-'Adiyat","Al-Qari'ah","At-Takathur","Al-Asr","Al-Humazah","Al-Fil","Al-Quraish","Al-Ma'un",
            "Al-Kauthar","Al-Kafirun","An-Nasr","Al-Lahab","Al-Ikhlas","Al-Falaq","An-Nas"));
    SurahIndexAdapter.onClickInterface onClickInterface;
    private SurahIndexAdapter surahIndexAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_index);

        onClickInterface = new SurahIndexAdapter.onClickInterface() {
            @Override
            public void setClick(int abc) {
                int surahNumber = abc+1;
                Toast.makeText(SurahIndex.this,"Surah Number Is: " +surahNumber,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SurahIndex.this,Surah.class);
                intent.putExtra("pos",abc);
                startActivity(intent);

                surahIndexAdapter.notifyDataSetChanged();
            }
        };


        RecyclerView recyclerViewSurahIndex = findViewById(R.id.recyclerViewSurahIndex);
        recyclerViewSurahIndex.setLayoutManager( new GridLayoutManager(this,3));

        surahIndexAdapter = new SurahIndexAdapter(this,surahIndexArrayList,onClickInterface);
        recyclerViewSurahIndex.setAdapter(surahIndexAdapter);

    }
}
