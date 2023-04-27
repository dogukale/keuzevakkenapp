package com.example.keuzevakkenapp.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.keuzevakkenapp.R;
import com.example.keuzevakkenapp.activity.DetailActivity;
import com.example.keuzevakkenapp.activity.MainActivity;
import com.example.keuzevakkenapp.activity.ProfileActivity;
import com.example.keuzevakkenapp.auth.SPM;
import com.example.keuzevakkenapp.auth.URLs;
import com.example.keuzevakkenapp.auth.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountFragment extends Fragment {

    // API Request & JSON
    String deviceName = android.os.Build.MODEL + android.os.Build.MANUFACTURER + android.os.Build.PRODUCT;
    JSONObject ApiResponse;
    View view_onViewCreated;
    String Token;

    // Assign values
    EditText nameValue, passwordValue;
    Button signIn;

    // TAG for Internet Connection
    private static final String TAG = "internetConnection";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // If User is Logged in
        if (SPM.getInstance(getActivity()).isLoggedIn()) {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
        } else {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_account, container, false);
            return v;
        }

        return null;
    }

    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(@NonNull @NotNull View v, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        // Save View v Globally
        view_onViewCreated = v;

        // Assign variables
        nameValue = v.findViewById(R.id.nameValue);
        passwordValue = v.findViewById(R.id.passwordValue);
        signIn = v.findViewById(R.id.signIn);

        // If Sign In Button is Clicked
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetAvailable(getActivity())) {
                    logIn();
                } else {
                    Toast.makeText(getActivity(),"No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Log in
    public void logIn() {
        // E-mailadres
        nameValue = (EditText) view_onViewCreated.findViewById(R.id.nameValue);
        String gebruikersEmail = nameValue.getText().toString();
        // Wachtwoord
        passwordValue = (EditText) view_onViewCreated.findViewById(R.id.passwordValue);
        String gebruikerswachtwoord = passwordValue.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JSONObject loginAttempt = new JSONObject();
        try {
            loginAttempt.put("email", gebruikersEmail);
            loginAttempt.put("password", gebruikerswachtwoord);
            loginAttempt.put("device_name", deviceName);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, URLs.URL_LOGIN, loginAttempt, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ApiResponse = response;
                    if (ApiResponse.getString("code").equals("log_success")) {
                        // Show toast for login success
                        Toast.makeText(getActivity(),"Succesvol ingelogd", Toast.LENGTH_SHORT).show();

                        // Get User information
                        int id = Integer.parseInt(ApiResponse.get("id").toString());
                        String email = ApiResponse.get("email").toString();
                        String name = ApiResponse.get("name").toString();
                        Token = ApiResponse.get("token").toString();

                        // Save User in SPM
                        User user = new User(id, name, email, Token);
                        SPM.getInstance(getActivity().getApplicationContext()).userLogin(user);

                        // Go to Profile Page
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "E-mailadres of wachtwoord onjuist", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(objectRequest);
    }

    public static boolean isInternetAvailable(Context context) {
        NetworkInfo connection = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (connection == null) {
            Log.d(TAG,"No internet connection");
            return false;
        } else {
            if(connection.isConnected()) {
                Log.d(TAG,"Internet connection available...");
                return true;
            } else {
                Log.d(TAG," internet connection");
                return true;
            }
        }
    }

    // hier gaat hij naar de sactum-geauthenticeerde routes
//    public void getAuthRoute() {
//        String URL_part = "sanctumusers";
//
//        RequestQueue requestQueue2 = Volley.newRequestQueue(getActivity().getApplicationContext());
//
//        StringRequest sr = new StringRequest(Request.Method.POST, BASE_URL+URL_part, response -> {
//                try {
////                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("gelukt", response.toString());
////                    String name = jsonObject.getString("message");
////                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
//                } catch (Throwable e) {
//                    e.printStackTrace();
//                    Log.d("gefaald", "niet gelukt");
//                }
//            }, error -> {
//
//            }) {
//
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("tag", "test");
//                return params;
//            }
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("Authorization", "Bearer " + Token);
//                    return params;
//                }
//
//            };
////            MySingleton.getInstance(MainActivity.this).addToRequestQueue(sr);
//        requestQueue2.add(sr);
//    }
}





