package com.example.ayahpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textViewAyah, textViewAyahArabic;
    EditText editTextAyah,editTextSurah;
    Button buttonSubmit;
    Button buttonSurahIndex;

    String urlAyah;
    String urlAyahAr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAyah = findViewById(R.id.textViewAyahBn);
        textViewAyahArabic = findViewById(R.id.textViewAyahArabic);

        buttonSurahIndex = findViewById(R.id.buttonSurahIndex);
        editTextAyah = findViewById(R.id.editTextAyah);
        editTextSurah =  findViewById(R.id.editTextSurah);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSurahIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SurahIndex.class);
                startActivity(intent);
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                get surah number and ayah number
                String surah = editTextSurah.getText().toString().trim();
                String ayah = editTextAyah.getText().toString().trim();
//                initialize method to receive specific ayah according to selected surah number.
                jsonReqBn(surah,ayah);
                jsonReqAr(surah,ayah);
//                set editText empty.
                editTextAyah.setText("");
                editTextSurah.setText("");
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
            registerForContextMenu(textViewAyahArabic);
            registerForContextMenu(textViewAyah);
//        textViewAyahArabic.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                ClipData clipData = ClipData.newPlainText("text copy",textViewAyahArabic.getText().toString());
//                clipboardManager.setPrimaryClip(clipData);
//                Toast.makeText(MainActivity.this,"copied",Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

    }
    void jsonReqAr(String surahAr, String ayahAr){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        urlAyahAr = "http://api.alquran.cloud/v1/ayah/"+surahAr+":"+ayahAr+"/ar";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAyahAr, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    String setText = jsonObject1.getString("text");
                    textViewAyahArabic.setText(setText);


                }catch (JSONException j){
                    j.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"No internet!",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }
    void jsonReqBn(String surahBn, String ayahBn){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        urlAyah = "http://api.alquran.cloud/v1/ayah/"+surahBn+":"+ayahBn+"/bn.bengali";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAyah, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    String setText = jsonObject1.getString("text");
                    textViewAyah.setText(setText);


                }catch (JSONException j){
                    j.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"No internet!",Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,"copy");
        menu.setHeaderTitle("copy text");
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData1 = ClipData.newPlainText("text",textViewAyah.getText().toString());
        ClipData clipData = ClipData.newPlainText("text",textViewAyahArabic.getText().toString());
        clipboardManager.setPrimaryClip(clipData);
        clipboardManager.setPrimaryClip(clipData1);
    }
}
