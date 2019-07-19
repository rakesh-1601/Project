package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.MillReportingModel4;
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

public class MillReporting4 extends samplenav {

    EditText srawmat, sendcut, susemiss, ssalemiss, scoal, dayclosmetread;
    TextView starttime;
    ImageView realtime;
    public boolean timedone = false;
    String timeimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_mill_reporting4, null, false);
        drawer.addView(contentView, 0);
        srawmat = findViewById(R.id.stock_raw_material);
        sendcut = findViewById(R.id.stock_end_cutting);
        susemiss = findViewById(R.id.stock_use_missrolled);
        ssalemiss = findViewById(R.id.stock_sale_missrolled);
        scoal = findViewById(R.id.stock_of_coal);
        dayclosmetread = findViewById(R.id.day_closing_meter);
        starttime = findViewById(R.id.start_time);
        realtime = findViewById(R.id.realtime);
    }

    public void submit(View view) {
        if (srawmat.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (sendcut.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (susemiss.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (ssalemiss.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (scoal.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (dayclosmetread.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (starttime.getText().toString().equals("")) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
        } else if (!timedone) {
            Toast.makeText(this, "Click plus button for start time", Toast.LENGTH_SHORT).show();
        } else {
            myapi();
        }
    }
    @Override
    public void onBackPressed() {
        return;
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

    public void myapi2() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting4.this);

        alertDialogBuilder.setTitle("Submission");

        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

        MillReportingModel4 millReportingModel1
                = new MillReportingModel4(
                Integer.parseInt(srawmat.getText().toString()),
                Integer.parseInt(sendcut.getText().toString()),
                Integer.parseInt(susemiss.getText().toString()),
                Integer.parseInt(ssalemiss.getText().toString()),
                Integer.parseInt(scoal.getText().toString()),
                Integer.parseInt(dayclosmetread.getText().toString()),
                starttime.getText().toString(),
                timeimage,
                LoginName.loginname, LoginName.shiftday
                ,MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel4> call = jsonPlaceHolder.millreporting4(text, millReportingModel1);
        call.enqueue(new Callback<MillReportingModel4>() {
            @Override
            public void onResponse(Call<MillReportingModel4> call, Response<MillReportingModel4> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel4 millReportingModel1 = response.body();
                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MillReporting4.this, LoginName.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel4> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void changecolor(View view) {
        DrawableCompat.setTint(realtime.getDrawable(), ContextCompat.getColor(this, R.color.blue));
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        timeimage = mHour + ":" + mMinute;
        timedone = true;
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

}
