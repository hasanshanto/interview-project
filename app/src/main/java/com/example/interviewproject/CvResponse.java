package com.example.interviewproject;

public class CvResponse {
    private String tsync_id;
    private String name;

    public CvResponse(String tsync_id, String name) {
        this.tsync_id = tsync_id;
        this.name = name;
    }

    public String getTsync_id() {
        return tsync_id;
    }

    public String getName() {
        return name;
    }
}
