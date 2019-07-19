package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.CNNFinalModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class CNNFinal extends samplenav {
    EditText editText;
    Spinner reason;
    String ccmreason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_cnnfinal, null, false);
        drawer.addView(contentView, 0);
        editText = findViewById(R.id.reason);
        reason = findViewById(R.id.ccmreasonspinner);
        reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Integer position = reason.getSelectedItemPosition();
                if (position == 3) {
                    editText.setVisibility(View.VISIBLE);
                } else {
                    editText.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    @Override
    public void onBackPressed() {
        return;
    }

    public void submit(View view) {
        if (reason.getSelectedItemPosition() == 3) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Reason ", Toast.LENGTH_SHORT).show();
            } else {
                ccmreason = editText.getText().toString();
                myapi();
            }
        }
        else
        {
            ccmreason = reason.getSelectedItem().toString();
            myapi();
        }
    }

    public void myapi(){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CNNFinal.this);

            alertDialogBuilder.setTitle("Submit");

            alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

            CNNFinalModel cnnFinalModel = new CNNFinalModel(
                    CCNReporting.ccmid,
                    cnnreportingform.realtime2, CCNReporting.realtime,
                    LoginName.loginname,
                    LoginName.shiftday,
                    ccmreason
            );
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String text = sharedPreferences.getString(TOKEN, "");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginShift.url)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<CNNFinalModel> call = jsonPlaceHolder.cnnsubmitdetail(text, cnnFinalModel);
            Log.v("my rsponse", cnnreportingform.realtime2);
            call.enqueue(new Callback<CNNFinalModel>() {
                @Override
                public void onResponse(Call<CNNFinalModel> call, Response<CNNFinalModel> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        return;
                    }
                    CNNFinalModel CNNFinalModel = response.body();
                    if (!CNNFinalModel.isSuccess()) {
                        Toast.makeText(getApplicationContext(), CNNFinalModel.getMsg(), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else {
                        alertDialog.dismiss();
                        Intent intent = new Intent(CNNFinal.this, CCNReporting.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<CNNFinalModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });
        }
    }
