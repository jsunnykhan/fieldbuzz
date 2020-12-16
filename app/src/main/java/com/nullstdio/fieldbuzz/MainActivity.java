package com.nullstdio.fieldbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nullstdio.fieldbuzz.client.Api;
import com.nullstdio.fieldbuzz.helper.SessionManager;
import com.nullstdio.fieldbuzz.models.LoginModel;
import com.nullstdio.fieldbuzz.models.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MainActivity extends AppCompatActivity {

    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginBt;
    private LoginModel loginModel;
    private Retrofit retrofit;
    private SessionManager sessionManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(MainActivity.this);
        isLoggedIn();

        usernameEt = findViewById(R.id.userNameEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBt = findViewById(R.id.loginBt);

         retrofit = new  Retrofit.Builder().baseUrl("https://recruitment.fisdev.com/api/")
                                 .addConverterFactory(GsonConverterFactory.create())
                                 .build();



        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameEt.getText().toString();
                String passWord = passwordEt.getText().toString();

                loginModel = new LoginModel(userName , passWord);

                Api api = retrofit.create(Api.class);
                Call<LoginResponse> responseCall = api.getToken(loginModel);

                responseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        String token = response.body().getToken();
                        sessionManager.setToken(token);
                        Intent intent = new Intent(MainActivity.this , FormActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("response" , "Fail");
                    }
                });
            }
        });


    }

    private void isLoggedIn() {
        if (sessionManager.isLoggedIn()){
            Intent intent = new Intent(MainActivity.this , FormActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
