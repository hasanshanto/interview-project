package com.example.interviewproject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiClient {


    @POST(Constants.LOGIN_URL)
    Call<LoginResponse> userLogin(@Body Post post);


    //@FormUrlEncoded
    @POST(Constants.FINAL_SUBMIT_URL)
    Call<DataResponse> sendData(@Header("Authorization") String token, @Body Data data);


    @Multipart
    @PUT(Constants.CV_URL)
    Call<CvResponse> updateCv(
            @Header("Authorization") String token,
            @Part MultipartBody.Part file,
            @Part("file") RequestBody name);


}
