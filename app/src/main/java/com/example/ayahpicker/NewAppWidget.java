package com.example.ayahpicker;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider{


    private static CharSequence ayahs;
    ArrayList<String> arrayList;



    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

//        String[] strings = {"Hello","World","Computer","file","edit","code","Sakib","Shad","Man","Raj","Ban"};
//        String[] name = context.getResources().getStringArray(R.array.name);
//        Random random = new Random();
//        int randomIndex = random.nextInt(name.length);
//        String randomString = name[randomIndex];



//        CharSequence widgetText = context.getString(R.string.appwidget_text);

//        //Retrieve the time//

//        String timeString =
//               DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());



//         Construct the RemoteViews object

//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
//        views.setTextViewText(R.id.appwidget_text,randomString);
//
//
//        views.setTextViewText(R.id.appwidget_text,context.getResources().getString(R.string.time,timeString));
//
//        // Instruct the widget manager to update the widget
//        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        super.onUpdate(context,appWidgetManager,appWidgetIds);

        String url = "http://api.alquran.cloud/v1/quran/quran-uthmani";
        final String [] strings = new String[50];
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                    JSONArray jsonArraySurahs = jsonObjectData.getJSONArray("surahs");
                    for (int i = 0; i<jsonArraySurahs.length();i++){
                        JSONObject jsonObjectSurahsIndex = jsonArraySurahs.getJSONObject(i);
                        JSONArray jsonArrayAyahs = jsonObjectSurahsIndex.getJSONArray("ayahs");
                        for (int j = 0;j<jsonArrayAyahs.length();j++){
                            JSONObject jsonObjectAyahs = jsonArrayAyahs.getJSONObject(j);
                            String arrayData = jsonObjectAyahs.getString("text");
                            strings[j] = arrayData;
                            Random random = new Random();
                            int randomIndex = random.nextInt(strings.length);
                            String randomAyahs = strings[randomIndex];
                            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
                            remoteViews.setTextViewText(R.id.appwidget_text,randomAyahs);
                            appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
//        Random random = new Random();
//        int randomInt = random.nextInt(100000);
//        String rand = String.valueOf(randomInt);
//
//        final int N = appWidgetIds.length;
//
//        for (int i = 0; i<N;i++){
//            int awId = appWidgetIds[i];
//            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
//            remoteViews.setTextViewText(R.id.appwidget_text,rand);
//            appWidgetManager.updateAppWidget(awId,remoteViews);
//        }
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

