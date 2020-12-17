package com.example.interviewproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocActivity extends AppCompatActivity {
    private Button btnSelect, btnUpload;
    private TextView textView;
    public ApiClient apiClient;
    public static Uri path;

    private int REQ_PDF = 21;


    private String token = "32562fc766feb95dfb70d6aef143f5e43f3c4706";
    private String auth_token = "Token " + token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        //Builds HTTP Client for API Calls
        apiClient = RetrofitClient.buildHTTPClient();

        textView = findViewById(R.id.textView);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpload = findViewById(R.id.btnUpload);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/pdf");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, REQ_PDF);

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDocument();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_PDF && resultCode == RESULT_OK && data != null) {

            path = data.getData();
            Log.e("Tag", path.toString());
            textView.setText("Document Selected");
            btnSelect.setText("Change Document");
        }
    }


    private void uploadDocument() {

        File file = new File(path.getPath());

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file.getPath());
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Call<CvResponse> call = apiClient.updateCv(auth_token, fileToUpload, filename);
        call.enqueue(new Callback<CvResponse>() {
            @Override
            public void onResponse(Call<CvResponse> call, Response<CvResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DocActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(DocActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CvResponse> call, Throwable t) {
                Toast.makeText(DocActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}