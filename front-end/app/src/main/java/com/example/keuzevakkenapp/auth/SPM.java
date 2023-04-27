package com.example.keuzevakkenapp.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.keuzevakkenapp.activity.DetailActivity;
import com.example.keuzevakkenapp.activity.MainActivity;
import com.example.keuzevakkenapp.fragments.AccountFragment;

public class SPM {
    // Constants
    private static final String SHARED_PREF_NAME = "SPM";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TOKEN = "token";

    private static SPM mInstance;
    private static Context mCtx;

    private SPM(Context context) {
        mCtx = context;
    }

    public static synchronized SPM getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SPM(context);
        }
        return mInstance;
    }

    // Method to let the user login
    // This method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_TOKEN, user.getToken());
        editor.apply();
    }

    // This method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    // This method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_TOKEN,null)
        );
    }

    // This method will logout the user
    public void logOut() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }
}
