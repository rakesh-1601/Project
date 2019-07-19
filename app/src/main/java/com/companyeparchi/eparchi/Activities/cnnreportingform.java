package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.CNNFormModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class cnnreportingform extends samplenav {
    EditText heatno, ltemp, lweight, tdt;
    Spinner lno;
    RadioButton one100, two25;
    TextView casttime, endtime;
    public static String realtime2;
    public static Integer cou = 0;

    String r1 = "", r2 = "";


    String t1,t2;


    public void endsequence(View view) {
        SelectTime2();
        myapi(1);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_cnnreportingform, null, false);
        drawer.addView(contentView, 0);
        heatno = findViewById(R.id.heatno);
        endtime = findViewById(R.id.endtimebreakdown);
        ltemp = findViewById(R.id.ltemp);
        lweight = findViewById(R.id.lweight);
        one100 = findViewById(R.id.one00);
        two25 = findViewById(R.id.one25);
        casttime = findViewById(R.id.castontime);
        tdt = findViewById(R.id.turndishtemp);
        lno = findViewById(R.id.spinner);
        if (cou >= 1) {
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout6);
            constraintLayout.setVisibility(View.GONE);
            tdt.setText("-100");
            r1="-100";
            r2="-100";
            ConstraintLayout constraintLayout1 = findViewById(R.id.constraintLayout5);
            constraintLayout1.setVisibility(View.GONE);
        }
    }

    public void SelectTime2() {
        int mHour, mMinute;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        realtime2 = mHour + " : " + mMinute;
    }

    public void SelectTime(View view) {
        int mHour, mMinute;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        final TextView editText = findViewById((view.getId()));

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        if (hourOfDay < 10 && minute < 10) {
                            editText.setText("0" + hourOfDay + ":0" + minute);
                        } else if (hourOfDay < 10) {
                            editText.setText("0" + hourOfDay + ":" + minute);
                        } else if (minute < 10) {
                            editText.setText(hourOfDay + ":0" + minute);
                        } else {
                            editText.setText(hourOfDay + ":" + minute);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


    String ontimeimage, offtimeimage;
    public boolean ontimedone = false, offtimedone = false;

    public void changecolor(View view) {
        int idimage = view.getId();
        ImageView imageView = findViewById(idimage);
        if (imageView == findViewById(R.id.imageView15) && !ontimedone) {
            DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
            int mHour, mMinute;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            ontimeimage = mHour + ":" + mMinute;
            ontimedone = true;
        } else if (imageView == findViewById(R.id.imageView16) && !offtimedone) {
            DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
            int mHour, mMinute;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            offtimeimage = mHour + ":" + mMinute;
            offtimedone = true;
        }
    }

    public void submit(View view) {
        myapi(0);
    }

    public void myapi(final Integer flag) {
        if (TextUtils.isEmpty(heatno.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Heat no.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(lweight.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ltemp.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(casttime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(endtime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (lno.getSelectedItem().toString().equals("Size in CC")) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (!ontimedone) {
            Toast.makeText(getApplicationContext(), "Select on time", Toast.LENGTH_SHORT).show();
        } else if (!offtimedone) {
            Toast.makeText(getApplicationContext(), "Select off time", Toast.LENGTH_SHORT).show();
        }else if(cou==0 && !one100.isChecked() && !two25.isChecked())
        {
            Toast.makeText(getApplicationContext(), "Select Size in CC", Toast.LENGTH_SHORT).show();
        }
        else {
            if (one100.isChecked()) {
                r1 = "100 x 100";
            }if (two25.isChecked()) {
                r2 = "125 x 125";
            }

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cnnreportingform.this);

            alertDialogBuilder.setTitle("Submit");

            alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

            CNNFormModel cnnFormModel
                    = new CNNFormModel(
                            r1,r2,
                    CCNReporting.ccmid,
                    Integer.parseInt(heatno.getText().toString()),
                    casttime.getText().toString(),
                    Integer.parseInt(ltemp.getText().toString()),
                    Integer.parseInt(lweight.getText().toString()),
                    endtime.getText().toString(),
                    Integer.parseInt(tdt.getText().toString()),
                    ontimeimage,
                    offtimeimage,
                    lno.getSelectedItem().toString(),
                    FurnaceInfo.ActFurnaceNo,
                    LoginName.shiftday,
                    LoginName.loginname


            );

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String text = sharedPreferences.getString(TOKEN, "");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginShift.url)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<CNNFormModel> call = jsonPlaceHolder.cnnformdetail(text, cnnFormModel);
            call.enqueue(new Callback<CNNFormModel>() {
                @Override
                public void onResponse(Call<CNNFormModel> call, Response<CNNFormModel> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        return;
                    }
                    CNNFormModel CNNFormModel = response.body();
                    if (!CNNFormModel.isSuccess()) {
                        Toast.makeText(getApplicationContext(), CNNFormModel.getMsg(), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else {
                        alertDialog.dismiss();
                        cou = 1;
                        if (flag == 0) {
                            Intent intent = new Intent(cnnreportingform.this, cnnreportingform.class);
                            startActivity(intent);
                        } else {
                            cou = 0;
                            Intent intent = new Intent(cnnreportingform.this, CNNFinal.class);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<CNNFormModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });
        }
    }
}
