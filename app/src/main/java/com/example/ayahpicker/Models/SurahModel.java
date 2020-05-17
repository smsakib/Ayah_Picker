package com.example.ayahpicker.Models;

public class SurahModel {
 public String arabicText;
 public String banglaText;

    public SurahModel(String arabicText,String banglaText) {
        this.arabicText = arabicText;
        this.banglaText = banglaText;

    }


    public String getBanglaText() {
        return banglaText;
    }

    public String getArabicText() {
        return arabicText;
    }





    public SurahModel(){

 }
}
