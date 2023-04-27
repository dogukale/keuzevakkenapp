package com.example.keuzevakkenapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.keuzevakkenapp.R;
import com.example.keuzevakkenapp.auth.SPM;
import com.example.keuzevakkenapp.auth.URLs;
import com.example.keuzevakkenapp.auth.User;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    TextView detailCode, detailName, detailDesc, detailSpec;
    ImageView backButton;
    Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keuzevak_detail);

        // Assign variables
        detailCode = findViewById(R.id.detailCode);
        detailName = findViewById(R.id.detailNaam);
        detailDesc = findViewById(R.id.detailDesc);
        detailSpec = findViewById(R.id.detailSpec);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        // Bring Name in Scope
        String code = null;
        String name = null;
        String desc = null;
        String spec = null;

        // Check for any intents
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            code = extras.getString("code");
            name = extras.getString("name");
            desc = extras.getString("desc");
            spec = extras.getString("spec");
        }

        // Set Content in Detail Activity
        detailCode.setText(code);
        detailName.setText(name);
        detailDesc.setText(desc);
        detailSpec.setText(spec);

        // Change color for each Specialization
        if (spec.equals("Algemeen")) {
            detailSpec.setTextColor(Color.parseColor("#9C27B0"));
        }
        if (spec.equals("Software Engineering")) {
            detailSpec.setTextColor(Color.parseColor("#2196F3"));
        }
        if (spec.equals("Interactie-technologie")) {
            detailSpec.setTextColor(Color.parseColor("#4CAF50"));
        }
        if (spec.equals("Forensisch ICT")) {
            detailSpec.setTextColor(Color.parseColor("#FFC107"));
        }
        if (spec.equals("Business Data Management")) {
            detailSpec.setTextColor(Color.parseColor("#FF9800"));
        }

        // Set OnClickListener for Back Button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Post Request on Click
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Als je hierop klikt, moet hij de data knallen in database
                // Als hij is ingelogd, zo niet moet hij eerst inloggen

                // Assign variables
                User user = SPM.getInstance(getApplicationContext()).getUser();
                boolean isLoggedIn = SPM.getInstance(getApplicationContext()).isLoggedIn();
                int id = user.getId();
                String name = user.getName();
                String email = user.getEmail();
                String token = user.getToken();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                if (isLoggedIn) {
                    StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_POST_REVIEW, response -> {
                        try {
                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } catch (Throwable e) {
                            e.printStackTrace();
                            Log.d("Gefaald", "Niet gelukt");
                        }
                    }, error -> {

                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("user_id", String.valueOf(id));
                            params.put("student_name", name);
                            params.put("class_name", detailName.getText().toString());
                            params.put("class_code", detailCode.getText().toString());
                            params.put("class_desc", detailDesc.getText().toString());
                            params.put("class_spec", detailSpec.getText().toString());
                            return params;
                        }

                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Authorization", "Bearer " + token);
                            return params;
                        }

                    };

                    requestQueue.add(sr);

                } else {
                    Toast.makeText(getApplicationContext(), "Log eerst in om je in te schrijven", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}