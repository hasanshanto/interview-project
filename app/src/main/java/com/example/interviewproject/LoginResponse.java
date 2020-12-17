package com.example.interviewproject;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("organization_name")
    private String orgName;

    @SerializedName("token")
    private String token;

    public String getOrgName() {
        return orgName;
    }

    public String getToken() {
        return token;
    }
}
