package com.nullstdio.fieldbuzz.client;

import com.nullstdio.fieldbuzz.models.CvFile;
import com.nullstdio.fieldbuzz.models.CvFileDataModel;
import com.nullstdio.fieldbuzz.models.LoginModel;
import com.nullstdio.fieldbuzz.models.UserModelClass;
import com.nullstdio.fieldbuzz.models.response.LoginResponse;
import com.nullstdio.fieldbuzz.models.response.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    //For Auth Token
    @Headers({"Accept: application/json"})
    @POST("login")
    Call<LoginResponse> getToken(@Body LoginModel body);

    // upload Data  for testing endpoint
    @POST("v0/recruiting-entities/")
    Call<UserResponse> postTestData(@Header("Authorization") String Token, @Body UserModelClass modelData);

    //Real Api endPoint for upload Data
    @POST("v1/recruiting-entities/")
    Call<UserResponse> postData(@Header("Authorization") String Token, @Body UserModelClass modelData);

    @Multipart
    @PUT("file-object/{id}/")
    Call<CvFileDataModel> updateFileWithPdf(@Header("Authorization") String Token, @Path("id") int id, @Part MultipartBody.Part file);

}

