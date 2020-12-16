package com.nullstdio.fieldbuzz.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String Name = "logged";

    private static  final String KEY_IS_TOKEN ="token";

    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Name , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setToken (String token){
        editor.putString(KEY_IS_TOKEN , token);
        editor.commit();
    }

    public  String getToken () {
        return sharedPreferences.getString(KEY_IS_TOKEN , null);
    }

    public boolean isLoggedIn(){
        if (getToken() != null){
            return true;
        }else {
            return false;
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }


}
