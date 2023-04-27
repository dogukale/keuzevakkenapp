package com.example.keuzevakkenapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.keuzevakkenapp.R;
import com.example.keuzevakkenapp.auth.SPM;
import com.example.keuzevakkenapp.auth.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    String user;
    String token;
    TextView loggedInUser;
    ImageView logOut, backButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get Logged In User from SPM
        user = SPM.getInstance(this).getUser().getName();
        user = user.substring(0, user.indexOf(" "));
        token = SPM.getInstance(this).getUser().getToken();

        // Assign variables
        loggedInUser = findViewById(R.id.loggedInUser);
        logOut = findViewById(R.id.logOutButton);
        backButton = findViewById(R.id.backButton);

        // Store Logged In User in TextField
        loggedInUser.setText("Welkom " + user);

        // Set OnClickListener for Back Button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                logOutRequest(token);
                SPM.getInstance(getApplicationContext()).logOut();
            }
        });
    }

    public void logOutRequest(String Token) {
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_LOGOUT, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String loggedOut = jsonObject.getString("message");
                Toast.makeText(this, loggedOut, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + Token);
                return params;
            }
        };

        requestQueue.add(sr);
    }
}