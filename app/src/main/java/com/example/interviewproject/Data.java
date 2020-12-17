package com.example.interviewproject;

public class Data {
    public String tsync_id;
    public String name;
    public String email;
    public String phone;
    public String full_address;
    public String name_of_university;
    public int graduation_year;
    public double cgpa;
    public int experience_in_months;
    public String current_work_place_name;
    public String applying_in;
    public int expected_salary;
    public String field_buzz_reference;
    public String github_project_url;
    public CvFile cv_file;
    public long on_spot_update_time;
    public long on_spot_creation_time;

    public Data(String tsync_id, String name, String email, String phone, String full_address, String name_of_university, int graduation_year, double cgpa, int experience_in_months, String current_work_place_name, String applying_in, int expected_salary, String field_buzz_reference, String github_project_url, CvFile cv_file) {
        this.tsync_id = tsync_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.full_address = full_address;
        this.name_of_university = name_of_university;
        this.graduation_year = graduation_year;
        this.cgpa = cgpa;
        this.experience_in_months = experience_in_months;
        this.current_work_place_name = current_work_place_name;
        this.applying_in = applying_in;
        this.expected_salary = expected_salary;
        this.field_buzz_reference = field_buzz_reference;
        this.github_project_url = github_project_url;
        this.cv_file = cv_file;
        //this.on_spot_update_time = on_spot_update_time;
        //this.on_spot_creation_time = on_spot_creation_time;
    }

    public String getTsync_id() {
        return tsync_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFull_address() {
        return full_address;
    }

    public String getName_of_university() {
        return name_of_university;
    }

    public int getGraduation_year() {
        return graduation_year;
    }

    public double getCgpa() {
        return cgpa;
    }

    public int getExperience_in_months() {
        return experience_in_months;
    }

    public String getCurrent_work_place_name() {
        return current_work_place_name;
    }

    public String getApplying_in() {
        return applying_in;
    }

    public int getExpected_salary() {
        return expected_salary;
    }

    public String getField_buzz_reference() {
        return field_buzz_reference;
    }

    public String getGithub_project_url() {
        return github_project_url;
    }

    public CvFile getCv_file() {
        return cv_file;
    }

    public long getOn_spot_update_time() {
        return on_spot_update_time;
    }

    public long getOn_spot_creation_time() {
        return on_spot_creation_time;
    }
}


