package com.example.interviewproject;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;

    public Post(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
