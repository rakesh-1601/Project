package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.InductionModel;
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

public class InductionForm extends samplenav {
    EditText heatno, mn, pig, sfc, cpc, scrap, taptemp, aluminum, silicon;
    TextView totaltime, ontime, offtime;
    RadioGroup rg;
    RadioButton rb;
    Button submitb;
    String ontimeimage, offtimeimage;
    Integer diffminute = 0, diffhour = 0;
    int starthour, startminute, endhour, endminute;
    public boolean ontimedone = false, offtimedone = false;
    Spinner reason;
    String indreason = "";
    Integer valueitem = -1;
    EditText reasontext;

    Date t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_form4, null, false);
        drawer.addView(contentView, 0);

        //Find ID of all Fields
        heatno = findViewById(R.id.heatno);
        ontime = findViewById(R.id.ontime);
        offtime = findViewById(R.id.offtime);
        mn = findViewById(R.id.mn);
        pig = findViewById(R.id.pig);
        sfc = findViewById(R.id.sfc);
        cpc = findViewById(R.id.cpc);
        rg = findViewById(R.id.radioGroup);
        scrap = findViewById(R.id.scrap);
        totaltime = findViewById(R.id.totaltime5);
        submitb = findViewById(R.id.submitb);
        taptemp = findViewById(R.id.taptemper);
        aluminum = findViewById(R.id.aluminum);
        silicon = findViewById(R.id.silicon);
        reason = findViewById(R.id.inductionreasonspinner);
        reasontext = findViewById(R.id.reasonwritten);
        reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Integer position = reason.getSelectedItemPosition();
                if (position == 8) {
                    reasontext.setVisibility(View.VISIBLE);
                } else {
                    reasontext.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void SelectTime(View view) {
        int mHour1, mMinute1;
        // Get Current Time
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

                        if (editText == findViewById(R.id.ontime)) {
                            starthour = hourOfDay;
                            startminute = minute;
                            Log.v("time is ontime ",""+starthour+startminute);
                        } else if(editText==findViewById(R.id.offtime)){
                            endhour = hourOfDay;
                            endminute = minute;
                            Log.v("time is offtime ",""+endhour+endminute);
                        }

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
                }, mHour1, mMinute1, false);
        timePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    //calculate difference of start and end time
    public void Timedifference(Integer e_h, Integer e_m, Integer s_h, Integer s_m) {

        Log.v("difference time is", String.valueOf(e_h)+" "+e_m+" "+s_h+" "+s_m);
        e_m += (e_h * 60);
        s_m += (s_h * 60);
        if(e_m<s_m)
        {
            e_m += 24 * 60;
        }
        int diff = e_m-s_m;
        diffhour = diff/60;
        diffminute = diff%60;

        Log.v("difference time is", String.valueOf(diffhour)+"  "+diffminute+"  "+diff);
    }

    //Cancel Reason Popup
    public void popupcancel(View view) {
        ConstraintLayout constraintLayout = findViewById(R.id.popupreason);
        constraintLayout.setVisibility(View.GONE);
    }

    //Cancel Remark Popup
    public void popupcancelremark(View view) {
        ConstraintLayout constraintLayout = findViewById(R.id.popupremark);
        constraintLayout.setVisibility(View.GONE);
    }

    //real time getter and change plus icon color
    public void changecolor(View view) {
        ImageView imageView = findViewById(view.getId());
        DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        if (imageView == findViewById(R.id.ontimeimage)) {
            ontimeimage = mHour + ":" + mMinute;
            ontimedone = true;
            t1 = c.getTime();
        } else {
            offtimeimage = mHour + ":" + mMinute;
            offtimedone = true;
            t2 = c.getTime();
        }
    }

    public void finalretrofitapi(String remark) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InductionForm.this);
        alertDialogBuilder.setTitle("Submission");
        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        final InductionModel inductionModel
                = new InductionModel(
                indreason,
                ontimeimage, offtimeimage,
                LoginName.loginname,
                valueitem
                , heatno.getText().toString()
                , ontime.getText().toString()
                , offtime.getText().toString()
                , totaltime.getText().toString()
                , Integer.parseInt(pig.getText().toString())
                , Integer.parseInt(scrap.getText().toString())
                , Integer.parseInt(mn.getText().toString())
                , Integer.parseInt(sfc.getText().toString())
                , Integer.parseInt(cpc.getText().toString())
                , LoginName.shiftday
                , Integer.parseInt(taptemp.getText().toString()),
                aluminum.getText().toString(),
                silicon.getText().toString(),
                remark
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<InductionModel> call = jsonPlaceHolder.inductiondetail(text, inductionModel);
        call.enqueue(new Callback<InductionModel>() {

            @Override
            public void onResponse(Call<InductionModel> call, Response<InductionModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(InductionForm.this, response.message(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                InductionModel InductionModel = response.body();
                if (!InductionModel.isSuccess()) {
                    Toast.makeText(InductionForm.this, inductionModel.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(InductionForm.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InductionForm.this, FurnaceInfo.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InductionModel> call, Throwable t) {
                Toast.makeText(InductionForm.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void myapiconfirmation(final String remark) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Confirm Submit ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, int id) {
                        dialog.cancel();
                        finalretrofitapi(remark);
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


    //submit of reason clicked
    public void Submittwo(View view) {

        if (reason.getSelectedItemPosition() == 8) {
            if (TextUtils.isEmpty(reasontext.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Reason ", Toast.LENGTH_SHORT).show();
            } else {
                indreason = reasontext.getText().toString();
                ConstraintLayout constraintLayout = findViewById(R.id.popupremark);
                constraintLayout.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            indreason = reason.getSelectedItem().toString();
            ConstraintLayout constraintLayout = findViewById(R.id.popupremark);
            constraintLayout.setVisibility(View.VISIBLE);
        }
    }

    //Submit of remark clicked
    public void Submitremark(View view) {
        EditText editText = findViewById(R.id.remark);
        if (editText.equals("")) {
            Toast.makeText(this, "Please enter remark", Toast.LENGTH_SHORT).show();
        } else {
            myapiconfirmation(editText.getText().toString());
        }
    }


    public void submit(View view) {

        Timedifference(endhour, endminute, starthour, startminute);
        TextView textView = findViewById(R.id.totaltime5);
        textView.setText(diffhour + ":" + diffminute);

        //Validate all Fields..
        int idb = rg.getCheckedRadioButtonId();
        if (TextUtils.isEmpty(heatno.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Heat no.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ontime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter On time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(offtime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Off time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mn.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter M N", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(scrap.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Scrap", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pig.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Pig", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cpc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter C P C", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(sfc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter S F C", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(taptemp.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Tap Temperature ", Toast.LENGTH_SHORT).show();
        } else if (idb == -1) {
            Toast.makeText(getApplicationContext(), "Select Furnace", Toast.LENGTH_SHORT).show();
        } else if (!ontimedone) {
            Toast.makeText(getApplicationContext(), "Select on time", Toast.LENGTH_SHORT).show();
        } else if (!offtimedone) {
            Toast.makeText(getApplicationContext(), "Select off time", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(aluminum.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Aluminum value", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(silicon.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Silicon value", Toast.LENGTH_SHORT).show();
        } else {
            rb = findViewById(idb);
            if (rb.getText().toString().equals("Furnace 1")) {
                valueitem = 1;
            } else if (rb.getText().toString().equals("Furnace 2")) {
                valueitem = 2;
            } else if (rb.getText().toString().equals("Furnace 3")) {
                valueitem = 3;
            } else {
                valueitem = 4;
            }

            if (diffhour == 1 && diffminute >= 50) {
                ConstraintLayout constraintLayout = findViewById(R.id.popupremark);
                constraintLayout.setVisibility(View.VISIBLE);
            } else if (diffhour > 1 && diffhour <= 2 && diffminute <= 45) {
                ConstraintLayout constraintLayout = findViewById(R.id.popupremark);
                constraintLayout.setVisibility(View.VISIBLE);
            } else {
                ConstraintLayout constraintLayout = findViewById(R.id.popupreason);
                constraintLayout.setVisibility(View.VISIBLE);

            }
        }
    }
}
