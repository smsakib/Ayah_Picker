package com.example.ayahpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ayahpicker.Adapter.SurahAdapter;
import com.example.ayahpicker.Models.SurahModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Surah extends AppCompatActivity {

    RecyclerView recyclerView;
    SurahAdapter surahAdapter;

    ArrayList<SurahModel> arrayListSurahs;
    TextView textViewSurahName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);
        recyclerView = findViewById(R.id.recyclerViewFetch);
        textViewSurahName = findViewById(R.id.textViewSurahName);
        arrayListSurahs = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(Surah.this,RecyclerView.VERTICAL,false));
        Bundle bundle = getIntent().getExtras();
        int pos = bundle.getInt("pos");
        int finalposition = pos+1;
        String position = String.valueOf(finalposition);
//        getSurah();
        getSurahByIndex(position);
    }

    private void getSurahByIndex(String position) {
        String urlSurah = "http://api.alquran.cloud/v1/surah/"+position+"/editions/quran-uthmani,bn.bengali";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlSurah, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String surahNameSet = jsonObject.getString("englishName");
                    textViewSurahName.setText(surahNameSet);
                    JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("ayahs");
                    JSONArray jsonArray2 = jsonObject2.getJSONArray("ayahs");
                    for (int i = 0; i<jsonArray1.length();i++){
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        JSONObject jsonObject3 = jsonArray2.getJSONObject(i);

                        String setSurahAr = jsonObject1.getString("text");
                        String setSurahBn = jsonObject3.getString("text");

                        arrayListSurahs.add(new SurahModel(setSurahAr,setSurahBn));


                    }

//                    JSONObject jsonObject2 = jsonArray.getJSONObject(1);
//                    JSONArray jsonArray2 = jsonObject2.getJSONArray("ayahs");
//                    for (int j = 0;j<jsonArray2.length();j++){
//                        JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
//                        String  setSurahbn = jsonObject3.getString("text");
//                        arrayListSurahs.add(new SurahModel(setSurahbn));
//                    }

                    surahAdapter = new SurahAdapter(Surah.this,arrayListSurahs);
                    recyclerView.setAdapter(surahAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    public void getSurah(){
        String urlSurah = "http://api.alquran.cloud/v1/surah/36/editions/quran-uthmani,en.asad";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlSurah, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("ayahs");
                    JSONArray jsonArray2 = jsonObject2.getJSONArray("ayahs");
                    for (int i = 0; i<jsonArray1.length();i++){
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                        JSONObject jsonObject3 = jsonArray2.getJSONObject(i);

                        String setSurahAr = jsonObject1.getString("text");
                        String setSurahBn = jsonObject3.getString("text");

                        arrayListSurahs.add(new SurahModel(setSurahAr,setSurahBn));
                    }

//                    JSONObject jsonObject2 = jsonArray.getJSONObject(1);
//                    JSONArray jsonArray2 = jsonObject2.getJSONArray("ayahs");
//                    for (int j = 0;j<jsonArray2.length();j++){
//                        JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
//                        String  setSurahbn = jsonObject3.getString("text");
//                        arrayListSurahs.add(new SurahModel(setSurahbn));
//                    }

                    surahAdapter = new SurahAdapter(Surah.this,arrayListSurahs);
                    recyclerView.setAdapter(surahAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

}
}

