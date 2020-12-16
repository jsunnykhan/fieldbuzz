package com.nullstdio.fieldbuzz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.nullstdio.fieldbuzz.client.Api;
import com.nullstdio.fieldbuzz.helper.FilePath;
import com.nullstdio.fieldbuzz.helper.SessionManager;
import com.nullstdio.fieldbuzz.models.CvFile;
import com.nullstdio.fieldbuzz.models.CvFileDataModel;
import com.nullstdio.fieldbuzz.models.UserModelClass;
import com.nullstdio.fieldbuzz.models.response.UserResponse;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;


public class FormActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 120;
    private SessionManager sessionManager;
    private Retrofit retrofit;


    private Spinner spinner;
    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;
    private TextInputLayout phoneLayout;
    private TextInputLayout addressLayout;
    private TextInputLayout universityNameLayout;
    private TextInputLayout graduationYearLayout;
    private TextInputLayout cgpaLayout;
    private TextInputLayout experienceLayout;
    private TextInputLayout currentWorkLayout;
    private TextInputLayout expectedSalaryLayout;
    private TextInputLayout githubUrlLayout;
    private TextInputLayout referenceLayout;

    private Api api;
    private String backendOrMobile;


    private Button fileChooserBt, submitBt;
    private TextView fileNameTv;


    String[] applyingIn = {"Mobile", "Backend"};
    private Uri uri;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        sessionManager = new SessionManager(this);

        nameLayout = (TextInputLayout) findViewById(R.id.nameLayout);
        emailLayout = (TextInputLayout) findViewById(R.id.emailLayout);
        phoneLayout = (TextInputLayout) findViewById(R.id.phoneLayout);
        addressLayout = (TextInputLayout) findViewById(R.id.fullAddressLayout);
        universityNameLayout = (TextInputLayout) findViewById(R.id.nameOfUniversityLayout);
        graduationYearLayout = (TextInputLayout) findViewById(R.id.graduationYearLayout);
        cgpaLayout = (TextInputLayout) findViewById(R.id.cgpaLayout);
        experienceLayout = (TextInputLayout) findViewById(R.id.experienceLayout);
        currentWorkLayout = (TextInputLayout) findViewById(R.id.currentWorkPlaceLayout);
        expectedSalaryLayout = (TextInputLayout) findViewById(R.id.expectedSalaryLayout);
        githubUrlLayout = (TextInputLayout) findViewById(R.id.githubUrlLayout);
        referenceLayout = (TextInputLayout) findViewById(R.id.reference);
        fileChooserBt = (Button) findViewById(R.id.fileBt);
        submitBt = (Button) findViewById(R.id.submitBt);
        fileNameTv = (TextView) findViewById(R.id.fileTv);


        retrofit = new Retrofit.Builder().baseUrl("https://recruitment.fisdev.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);


        spinner = findViewById(R.id.applyInSp);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, applyingIn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                backendOrMobile = applyingIn[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        fileChooserBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkResult()) {
                        choosePdfFile();
                    } else {
                        requestPersmission();
                    }
                } else {
                    choosePdfFile();
                }


            }
        });


        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameLayout.getEditText().getText().toString();
                String email = emailLayout.getEditText().getText().toString();
                String phone = phoneLayout.getEditText().getText().toString();
                String address = addressLayout.getEditText().getText().toString();
                String universityName = universityNameLayout.getEditText().getText().toString();
                String graduationYear = graduationYearLayout.getEditText().getText().toString();
                String cgpa = cgpaLayout.getEditText().getText().toString();
                String experienceYear = experienceLayout.getEditText().getText().toString();
                String workingPlace = currentWorkLayout.getEditText().getText().toString();
                String expectedSalary = expectedSalaryLayout.getEditText().getText().toString();
                String githubUrl = githubUrlLayout.getEditText().getText().toString();
                String reference = referenceLayout.getEditText().getText().toString();


                if (!isValidate(name, nameLayout, "Enter your Name") | !emailValidate(email) |
                        !isValidate(phone, phoneLayout, "Enter Phone number") |
                        !isValidate(universityName, universityNameLayout, "Enter University name") |
                        !isValidate(graduationYear, graduationYearLayout, "Enter Graduation year") |
                        backendOrMobile.isEmpty() |
                        !isValidate(expectedSalary, expectedSalaryLayout, "Enter Expected Salary") |
                        !isValidate(githubUrl, githubUrlLayout, "Enter Github link")) {
                    return;
                }


                final CvFile cvFile = new CvFile();
                UserModelClass userModel = new UserModelClass(name, email,
                        phone, address, universityName, Integer.parseInt(graduationYear), Double.parseDouble(cgpa),
                        backendOrMobile, Integer.parseInt(expectedSalary), reference, Integer.parseInt(experienceYear), workingPlace,
                        githubUrl, cvFile);


                final String token = "Token " + sessionManager.getToken();


                Call<UserResponse> responseCall = api.postData(token, userModel);

                responseCall.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(FormActivity.this, response.body().getCvFile().getPath() + "", Toast.LENGTH_SHORT).show();
                            int id = response.body().getCvFile().getId();
                            Toast.makeText(FormActivity.this, "id : " + id, Toast.LENGTH_SHORT).show();
                            Log.d("First", "onResponse: " + response.body().getCvFile().getFile());

                            if (uri != null) {
                                File file = null;
                                try {
                                    file = new File(FilePath.getPath(uri, FormActivity.this));
                                } catch (Exception e) {

                                }
                                Toast.makeText(FormActivity.this, "" + file, Toast.LENGTH_SHORT).show();
                                RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
                                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                                Call<CvFileDataModel> pdfCall = api.updateFileWithPdf(token, id, body);

                                pdfCall.enqueue(new Callback<CvFileDataModel>() {
                                    @Override
                                    public void onResponse(Call<CvFileDataModel> call, Response<CvFileDataModel> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(FormActivity.this, "onResponse Succsses" + response.body().toString(), Toast.LENGTH_SHORT).show();
                                            Log.d("Second", "onResponse: " + response.body().getFile());
                                        } else {
                                            Toast.makeText(FormActivity.this, "onResponse Failure in pdf call", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CvFileDataModel> call, Throwable t) {
                                        Log.d("onFailure", "onFailure: onFailure Failure in pdf call " + t);
                                    }
                                });

                            }


                        } else {
                            Log.d("Failure", "onResponse: Failure Post Method ");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.d("Fail1", "onFailure Failure in post Data call "+t  );

                    }
                });


            }
        });

    }

    //Validate for all
    private boolean isValidate(String name, TextInputLayout layer, String message) {

        if (name.isEmpty()) {
            layer.setError(message);
            return false;
        } else {
            layer.setError(null);
            return true;
        }
    }

    //Validate for email
    private boolean emailValidate(String email) {
        if (email.isEmpty()) {
            emailLayout.setError("Enter your Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Should be valid Email");
            return false;
        } else {
            emailLayout.setError(null);
            return true;
        }
    }


    private void choosePdfFile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent, "Choose Pdf File"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == 1 && resultCode == RESULT_OK && data.getData() != null) {
            uri = data.getData();

            String namepath = uri.getPath().toString();
            fileNameTv.setText(namepath);

        }
    }


    private void requestPersmission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(FormActivity.this
                , Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Please Give permission to upload file", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                    , PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkResult() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission UnSuccess", Toast.LENGTH_SHORT).show();
        }

    }
}
