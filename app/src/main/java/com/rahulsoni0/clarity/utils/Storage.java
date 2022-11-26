package com.rahulsoni0.clarity.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {
    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public Storage(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public String getName() {
        return prefs.getString("name", " user ");
    }

    public void setName(String name) {
        editor.putString("name", name);
        editor.apply();
    }

    public String getAge() {
        return prefs.getString("age", "");
    }

    public void setAge(String age) {
        editor.putString("age", age);
        editor.apply();
    }

    public String getGender() {
        return prefs.getString("gender", "");
    }

    public void setGender(String gender) {
        editor.putString("gender", gender);
        editor.apply();
    }


    public String getEmail() {
        return prefs.getString("email", "");
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.apply();
    }


    public boolean isNewUser() {
        return prefs.getBoolean("isNewUser", true);
    }

    public int getfocusCount(){
        return prefs.getInt("focusCount", 0);
    }
    public void setFocusCount(int count){
        editor.putInt("focusCount", count);
        editor.apply();
    }
    public void setNewUser(boolean isNew) {

        editor.putBoolean("isNewUser", isNew);
        editor.apply();
    }

    public boolean isLogin() {
        return prefs.getBoolean("isLogin", false);
    }

    public void setLogin(boolean isLogin) {
        editor.putBoolean("isLogin", isLogin);
        editor.apply();
    }


}
