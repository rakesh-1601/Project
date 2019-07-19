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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.SequenceModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class StartEndSequence extends samplenav {
    EditText sno, skwah, skvah, ekwah, ekvah;
    TextView etime, stime;
    Date t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_start_end_sequence, null, false);
        drawer.addView(contentView, 0);
        sno = findViewById(R.id.sno);
        stime = findViewById(R.id.stime);
        skwah = findViewById(R.id.skwah);
        skvah = findViewById(R.id.skvah);
        etime = findViewById(R.id.etime);
        ekvah = findViewById(R.id.ekvah);
        ekwah = findViewById(R.id.ekwah);
    }

    @Override
    public void onBackPressed() {
        return;
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

                        editText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    String ontimeimage, offtimeimage;
    public boolean ontimedone = false, offtimedone = false;

    public void changecolor(View view) {
        int idimage = view.getId();
        ImageView imageView = findViewById(idimage);
        if (imageView == findViewById(R.id.ontimeimage) && !ontimedone) {
            DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
            int mHour, mMinute;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            ontimeimage = mHour + ":" + mMinute;
            ontimedone = true;
            t1 = c.getTime();
        } else if (imageView == findViewById(R.id.offtimeimage) && !offtimedone) {
            DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
            int mHour, mMinute;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            offtimeimage = mHour + ":" + mMinute;
            offtimedone = true;
            t2 = c.getTime();
        }
    }

    public void startpressed(View view) {
        ConstraintLayout constraintLayout1, constraintLayout2, constraintLayout3;
        constraintLayout1 = findViewById(R.id.constraintLayout2);
        constraintLayout2 = findViewById(R.id.constraintLayout3);
        constraintLayout3 = findViewById(R.id.constraintLayout4);
        constraintLayout1.setVisibility(View.VISIBLE);
        constraintLayout2.setVisibility(View.VISIBLE);
        constraintLayout3.setVisibility(View.VISIBLE);
    }

    public void endpressed(View view) {
        ConstraintLayout constraintLayout1, constraintLayout2, constraintLayout3;
        constraintLayout1 = findViewById(R.id.constraintLayout5);
        constraintLayout2 = findViewById(R.id.constraintLayout6);
        constraintLayout3 = findViewById(R.id.constraintLayout7);
        constraintLayout1.setVisibility(View.VISIBLE);
        constraintLayout2.setVisibility(View.VISIBLE);
        constraintLayout3.setVisibility(View.VISIBLE);
    }

    public void submit(View view) {
        Log.v("value", String.valueOf(ontimedone));

        if (TextUtils.isEmpty(sno.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Sequence no.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(stime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Start time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(skvah.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter KVAH ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(skwah.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter KWAH ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ekvah.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter KVAH", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ekwah.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter KWAH", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter End time", Toast.LENGTH_SHORT).show();
        } else if (!ontimedone) {
            Toast.makeText(getApplicationContext(), "Select on time", Toast.LENGTH_SHORT).show();
        } else if (!offtimedone) {
            Toast.makeText(getApplicationContext(), "Select off time", Toast.LENGTH_SHORT).show();
        }else {
            myapi();
        }
    }

    public void myapi() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StartEndSequence.this);

        alertDialogBuilder.setTitle("Submission");

        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();


        SequenceModel sequenceModel
                = new SequenceModel(
                ontimeimage, offtimeimage,
                LoginName.loginname,
                LoginName.shiftday,
                FurnaceInfo.ActFurnaceNo,
                sno.getText().toString()
                , Integer.parseInt(skwah.getText().toString())
                , Integer.parseInt(skvah.getText().toString())
                , stime.getText().toString()
                , Integer.parseInt(ekwah.getText().toString())
                , Integer.parseInt(ekvah.getText().toString())
                , etime.getText().toString()
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN,"");
        Call<SequenceModel> call = jsonPlaceHolder.sequencedetail(text, sequenceModel);
        call.enqueue(new Callback<SequenceModel>() {
            @Override
            public void onResponse(Call<SequenceModel> call, Response<SequenceModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Server Down ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                SequenceModel SequenceModel = response.body();
                if (!SequenceModel.isSuccess()) {
                    Toast.makeText(getApplicationContext(), SequenceModel.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StartEndSequence.this, StartEndSequence.class);
                    alertDialog.dismiss();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<SequenceModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
