package com.example.interviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {
    private EditText UUIDET, nameET, emailET, phoneET, addressET, universityET, yearET,
            cgpaET, experienceET, officeET, roleET, salaryET, referenceET, gitUrlET, cvET;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Spinner roleSpinner;
    private Button submitButton;
    public ApiClient apiClient;
    public String token;
    public String auth_token;
    private String role;
    private static final String tID = "f8f202d2f85b4ed394576f88f6beba0f";
    public String tsynchID = "f8f202d2f85b4ed394576f88f6beba0f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Builds HTTP Client for API Calls
        apiClient = RetrofitClient.buildHTTPClient();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            token = bundle.getString("auth_token");
            auth_token = "Token " + token;
        }

        nameET = findViewById(R.id.nameEditText);
        emailET = findViewById(R.id.emailEditText);
        phoneET = findViewById(R.id.phoneEditText);
        addressET = findViewById(R.id.addressEditText);
        universityET = findViewById(R.id.varsityEditText);
        yearET = findViewById(R.id.gradYearEditText);
        cgpaET = findViewById(R.id.cgpaEditText);
        experienceET = findViewById(R.id.experienceEditText);
        officeET = findViewById(R.id.officeEditText);

        radioGroup = findViewById(R.id.roleRadioGroup);
       // mobileButton = findViewById(R.id.mobileButton);
        //backendButton = findViewById(R.id.backendButton);


       // roleET = findViewById(R.id.roleEditText);
        salaryET = findViewById(R.id.salaryEditText);
        referenceET = findViewById(R.id.referenceEditText);
        gitUrlET = findViewById(R.id.githubEditText);
        submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
    }
    public void checkRadioButton(View view){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioButtonId);
        role = (String) radioButton.getText();
    }

    private void doSubmit() {

        // String UUID = UUIDET.getText().toString().trim();
        String name = nameET.getText().toString().trim();
        if (name.isEmpty()) {
            nameET.setError("Name is required.");
            nameET.requestFocus();
            return;
        }
        if (name.length() > 256) {
            nameET.setError("Name too long.");
            nameET.requestFocus();
            return;
        }

        String email = emailET.getText().toString().trim();

        if (email.isEmpty()) {
            emailET.setError("Email is required.");
            emailET.requestFocus();
            return;
        }
        if (!isValidEmail(emailET.getText().toString())) {
            emailET.setError("Email is invalid!");
            return;
        }

        String phone = phoneET.getText().toString().trim();
        if (phone.isEmpty()) {
            phoneET.setError("Phone Number is required.");
            phoneET.requestFocus();
            return;
        }
        if (phone.length() > 14) {
            phoneET.setError("Phone Number too long.");
            phoneET.requestFocus();
            return;
        }

        String address = addressET.getText().toString().trim();
        if (address.length() > 512) {
            addressET.setError("Address too long.");
            addressET.requestFocus();
            return;
        }
        String university = universityET.getText().toString().trim();
        if (university.isEmpty()) {
            universityET.setError("University Name is required.");
            universityET.requestFocus();
            return;
        }

        if (university.length() > 256) {
            universityET.setError("University name too long.");
            universityET.requestFocus();
            return;
        }
        int year = Integer.parseInt(yearET.getText().toString().trim());

        if (TextUtils.isEmpty(yearET.getText().toString().trim())) {
            yearET.setError("Graduation Year is required.");
            yearET.requestFocus();
            return;
        }

        if (year < 2015 || year > 2020) {

            yearET.setError("Graduation Year must be in between 2015 to 2020.");
            yearET.requestFocus();
            return;
        }

        double cgpa = Double.parseDouble(cgpaET.getText().toString().trim());

        if (cgpa < 2.0 || cgpa > 4.00) {
            cgpaET.setError("Mention your CGPA correctly.");
            cgpaET.requestFocus();
            return;
        }


        int experience = Integer.parseInt(experienceET.getText().toString().trim());

        if (experience < 0 || experience > 100) {
            experienceET.setError("Mention Experience Correctly.");
            experienceET.requestFocus();
            return;
        }
        String office = officeET.getText().toString().trim();
        if (office.length() > 256) {
            officeET.setError("Office name too long.");
            officeET.requestFocus();
            return;
        }

      //  String role = roleET.getText().toString().trim();


        int salary = Integer.parseInt(salaryET.getText().toString().trim());

        if (salary < 15000 || salary > 60000) {
            salaryET.setError("Salary Range is between 15000 to 60000.");
            salaryET.requestFocus();
            return;
        }
        String reference = referenceET.getText().toString().trim();
        if (reference.length() > 256) {
            referenceET.setError("Reference Name too long.");
            referenceET.requestFocus();
            return;
        }

        String giturl = gitUrlET.getText().toString().trim();

        if (giturl.isEmpty()) {
            gitUrlET.setError("Github URL is required.");
            gitUrlET.requestFocus();
            return;
        }

        if (giturl.length() > 512) {
            gitUrlET.setError("Github URL too long.");
            gitUrlET.requestFocus();
            return;
        }

        CvFile myFile = new CvFile(tID);


        Data data = new Data(tsynchID, name, email, phone, address, university, year, cgpa, experience, office, role, salary, reference, giturl, myFile);
        //Root root = new Root(tsynchID, "Md. Hasibul Hasan", "hasanshantu@gmail.com", "01315628889",
        //      "Dhaka", "DIU", 2020, 3.76, 2,
        //    "Fresher", "Mobile", 25000, "No",
        //  "https://github.com", myFile);
        Call<DataResponse> call = apiClient.sendData(auth_token, data);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(InfoActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InfoActivity.this, "successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InfoActivity.this, DocActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}