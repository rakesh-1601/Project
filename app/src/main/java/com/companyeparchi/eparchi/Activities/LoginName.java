package com.companyeparchi.eparchi.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.MillStopModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.MYCASE;
import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class LoginName extends samplenav {

    Spinner name = null;
    public static String loginname = "";
    RadioGroup rg;
    public static String shiftday = null;
    String text1, text2;
    public static Integer billetid;

    List<String> inductionemp = new ArrayList<>(Arrays.asList("Login as",
            "Krishna Srivastav",
            "Imtiaz Ahmed",
            "Rahul Singh",
            "Dinesh Yadav",
            "Other"

    ));
    List<String> ccmemp = new ArrayList<>(Arrays.asList("Login as",
            "Vikas Shukla",
            "Hitesh Dixsnesa",
            "Shivansh Upadhaya",
            "Other"
    ));

    List<String> qualityemp = new ArrayList<>(Arrays.asList("Login as",
            "Lalit Chaudhary",
            "Shyam Sahu",
            "Rakesh Sahu",
            "Saurabh Sahu",
            "Other"
    ));
    List<String> startendemp = new ArrayList<>(Arrays.asList("Login as"
    ));
    List<String> billetemp = new ArrayList<>(Arrays.asList("Login as",
            "Vikas Shukla",
            "Hitesh Dixsnesa",
            "Shivansh Upadhaya",
            "SBO",
            "Other"
    ));
    List<String> endcuttingemp = new ArrayList<>(Arrays.asList("Login as"
    ));
    List<String> millreportingemp = new ArrayList<>(Arrays.asList("Login as"
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_login_name, null, false);
        drawer.addView(contentView, 0);
        name = findViewById(R.id.name);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text1 = sharedPreferences.getString(MYCASE, "");
        text2 = sharedPreferences.getString(TOKEN, "");
        rg = findViewById(R.id.radioGroupDay);
        setspinner();
    }


    public void setspinner() {
        if (text1.equals("IFSU")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, inductionemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);
        } else if (text1.equals("IFSE")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, startendemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);
        } else if (text1.equals("QISU")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, qualityemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);
        } else if (text1.equals("CCMR")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, ccmemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);

        } else if (text1.equals("ECSU")) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, endcuttingemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);
        } else if (text1.equals("MCUP")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, millreportingemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);

        } else if (text1.equals("BRSU")) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, billetemp);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            name.setAdapter(adapter);

        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void submit(View view) {

        int rdb = rg.getCheckedRadioButtonId();
        if (rdb == -1) {
            Toast.makeText(getApplicationContext(), "Select Shift", Toast.LENGTH_SHORT).show();
        } else {
            if(name.getSelectedItemPosition()!=0)
            {
                loginname = name.getSelectedItem().toString();
            }
            RadioButton rb = findViewById(rdb);
            if (rb.getText().toString().equals("Day Shift")) {
                shiftday = "0";
            } else {
                shiftday = "1";
            }
            if (text1.equals("QISU")) {
                Intent intent = new Intent(LoginName.this, QualityInfo.class);
                startActivity(intent);
            } else if (text1.equals("CCMR")) {
                Intent intent = new Intent(LoginName.this, CCNReporting.class);
                startActivity(intent);

            } else if (text1.equals("ECSU")) {
                Intent intent = new Intent(LoginName.this, EndCutting2.class);
                startActivity(intent);

            } else if (text1.equals("MCUP")) {
                Intent intent = new Intent(LoginName.this, MillReporting1.class);
                startActivity(intent);
            } else if (text1.equals("BRSU")) {
                myapibilletid();
            } else {
                Intent intent = new Intent(LoginName.this, FurnaceInfo.class);
                startActivity(intent);
            }
        }

    }

    public void myapibilletid() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);
        Call<MillStopModel> call = jsonPlaceHolderApi.getbilletid(text2);

        call.enqueue(new Callback<MillStopModel>() {
            @Override
            public void onResponse(Call<MillStopModel> call, Response<MillStopModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(LoginName.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                MillStopModel millStopModel= response.body();
                billetid  = millStopModel.getId();
                Log.v("id is", String.valueOf(billetid));
                Intent intent = new Intent(LoginName.this, BilletInfo.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MillStopModel> call, Throwable t) {
                Toast.makeText(LoginName.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
