package com.example.keuzevakkenapp.auth;

public class User {
    private final int id;
    private final String email;
    private final String name;
    private final String token;

    public User(int id, String name, String email, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
