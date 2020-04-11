package com.cirrastec.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context mContext) {
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno",Context.MODE_PRIVATE);


    }

    public void storeString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String readString(String key) {
        return this.mSharedPreferences.getString(key,"");
    }

    public void storeBoolean(String key, Boolean value) {
        this.mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean readBoolean(String key) {
        return this.mSharedPreferences.getBoolean(key, false);
    }


}
