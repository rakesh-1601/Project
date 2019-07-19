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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.EndCuttingModel;
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

public class EndCutting2 extends samplenav {
    EditText weight;
    TextView time;
    RadioGroup rg;
    ImageView imageView;
    String realtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_end_cutting2, null, false);
        drawer.addView(contentView, 0);
        weight = findViewById(R.id.weight);
        time = findViewById(R.id.time);
        rg = findViewById(R.id.customrg2);
        imageView = findViewById(R.id.imageView17);
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
    @Override
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

    public void SelectTime(View view) {
        int mHour1,mMinute1;
            final Calendar c = Calendar.getInstance();
            mHour1 = c.get(Calendar.HOUR_OF_DAY);
            mMinute1 = c.get(Calendar.MINUTE);
            final TextView editText = findViewById((view.getId()));
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
                    }, mHour1, mMinute1, false);
            timePickerDialog.show();
    }
    public void myapi2()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EndCutting2.this);

        alertDialogBuilder.setTitle("Submission");

        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
        EndCuttingModel endCuttingModel
                = new EndCuttingModel(
                        timeimage,
                LoginName.loginname,
                LoginName.shiftday,
                Integer.parseInt(weight.getText().toString().toString())
                , time.getText().toString()
                , valueitem
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN,"");
        Call<EndCuttingModel> call = jsonPlaceHolder.endcuttingtwodetail(text,endCuttingModel);
        call.enqueue(new Callback<EndCuttingModel>() {
            @Override
            public void onResponse(Call<EndCuttingModel> call, Response<EndCuttingModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Server Down ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                EndCuttingModel EndCuttingModel = response.body();
                if (!EndCuttingModel.isSuccess()) {
                    Toast.makeText(getApplicationContext(), EndCuttingModel.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EndCutting2.this, EndCutting2.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<EndCuttingModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    Integer valueitem;
    public void submit(View view)
    {

        int idb = rg.getCheckedRadioButtonId();
        if (TextUtils.isEmpty(weight.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Weight.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(time.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Time", Toast.LENGTH_SHORT).show();
        }
        else if (!timedone) {
            Toast.makeText(getApplicationContext(), "Select real Time", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton rb = findViewById(idb);
            if (rb.getText().toString().equals("20")) {
                valueitem = 20;
            } else if (rb.getText().toString().equals("12")) {
                valueitem = 12;
            } else {
                valueitem = 9;
            }

            myapi();
        }
    }

}
