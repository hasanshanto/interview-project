package com.example.interviewproject;

public class DataResponse {
    private String name;
    private String email;

    public DataResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
