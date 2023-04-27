package com.example.keuzevakkenapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.keuzevakkenapp.R;
import com.example.keuzevakkenapp.auth.SPM;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    String user;
    String token;
    TextView loggedInUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (SPM.getInstance(getActivity()).isLoggedIn()) {
            // Get Logged In User from SPM
            user = SPM.getInstance(getActivity().getApplicationContext()).getUser().getName();
            user = user.substring(0, user.indexOf(" "));
            token = SPM.getInstance(getActivity().getApplicationContext()).getUser().getToken();

            // Assign variables
            loggedInUser = view.findViewById(R.id.loggedInUser);

            // Store Logged In User in TextField
            loggedInUser.setText("Welkom " + user);
        }
    }
}