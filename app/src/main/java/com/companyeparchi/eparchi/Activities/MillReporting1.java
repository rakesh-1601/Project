package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.MillReportingModel1;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class MillReporting1 extends samplenav {


    public static Integer millid;
    public static Integer Length = 20;
    public static Integer flag = 0;

    RadioGroup rg;
    ImageView imageView,imageView2;
    EditText dopenmeter;
    TextView date,time;
    Spinner material;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_mill_reporting1, null, false);
        drawer.addView(contentView, 0);
        imageView = findViewById(R.id.imageView12);
        rg = findViewById(R.id.customrg1);
        imageView2 = findViewById(R.id.imageView4);
        date = findViewById(R.id.date);
        time = findViewById(R.id.starttime);
        material = findViewById(R.id.spinner2);

        dopenmeter  =findViewById(R.id.dateopenmeter);
    }

    public void onBackPressed() {
        return;
    }

    public boolean timedone = false;
    String timeimage;
    public void changecolor(View view) {
        DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        timeimage = mHour + ":" + mMinute;
        timedone = true;
    }

    String dateimage;
    public boolean datedone = false;
    public void changecolor2(View view) {
        DrawableCompat.setTint(imageView2.getDrawable(), ContextCompat.getColor(this, R.color.blue));
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        dateimage = mHour + ":" + mMinute;
        datedone = true;
    }



    public void SelectTime(View view)
    {
        int mHour,mMinute;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        final TextView editText = findViewById((view.getId()));
        Log.v("id is",editText.toString());
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if(hourOfDay<10 && minute<10)
                        {
                            editText.setText("0"+hourOfDay + ":0" + minute);
                        }
                        else if(hourOfDay<10){
                            editText.setText("0"+hourOfDay + ":" + minute);
                        }
                        else if(minute<10) {
                            editText.setText(hourOfDay + ":0" + minute);
                        }
                        else {
                            editText.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void Selectdate(View view)
    {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        final TextView editText = findViewById((view.getId()));

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    Integer valueitem;
    String materialtype;
    public void submitmillone(View view)
    {
        int idb = rg.getCheckedRadioButtonId();
        if (TextUtils.isEmpty(dopenmeter.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Day Opening Meter.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(time.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Start Time", Toast.LENGTH_SHORT).show();
        }
        else if (!timedone) {
            Toast.makeText(getApplicationContext(), "Press Plus Button for start time ", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(date.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Date", Toast.LENGTH_SHORT).show();
        }
        else if (!datedone) {
            Toast.makeText(getApplicationContext(), "Press Plus Button for date", Toast.LENGTH_SHORT).show();
        } else if (material.getSelectedItemPosition()==0) {
            Toast.makeText(getApplicationContext(), "Enter Material Type", Toast.LENGTH_SHORT).show();
        }
        else if (!timedone) {
            Toast.makeText(getApplicationContext(), "Press Plus Button for start time ", Toast.LENGTH_SHORT).show();
        }else {
            RadioButton rb = findViewById(idb);
            if (rb.getText().toString().equals("20")) {
                valueitem = 20;
            } else if (rb.getText().toString().equals("12")) {
                valueitem = 12;
            } else {
                valueitem = 9;
            }

            if(material.getSelectedItemPosition()==1)
            {
                materialtype = "ANG";
                flag = 1;
            }
            else
            {
                flag = 2;
                materialtype = "CHA";
            }
            Length = valueitem;

            myapi();
        }
    }


    public void myapi() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Confirm Submit ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id) {
                        dialog.cancel();
                        myapi2();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void myapi2()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting1.this);

        alertDialogBuilder.setTitle("Submission");

        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
        MillReportingModel1 millReportingModel1
                = new MillReportingModel1(
                time.getText().toString(),Integer.parseInt(dopenmeter.getText().toString()),date.getText().toString(),materialtype,valueitem,timeimage,dateimage,LoginName.loginname,LoginName.shiftday
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN,"");
        Call<MillReportingModel1> call = jsonPlaceHolder.millreporting1detail(text,millReportingModel1);
        call.enqueue(new Callback<MillReportingModel1>() {
            @Override
            public void onResponse(Call<MillReportingModel1> call, Response<MillReportingModel1> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel1 millReportingModel1 = response.body();
                millid = millReportingModel1.getId();
//                Log.v("id finally",millid.toString());
                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MillReporting1.this, MillReporting2.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel1> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

}
