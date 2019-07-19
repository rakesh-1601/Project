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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.BilletModel;
import com.companyeparchi.eparchi.Models.MillStopModel;
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

public class BilletInfo extends samplenav {
    EditText blength, bcount, remarks, mbcount, mblength;
    Spinner mcuttype;
    TextView etime, stime, mstime, mendtime;
    ConstraintLayout layout;
    ImageView imageView1, imageView2;
    public static Integer billetid;
    String text;

    Date t1;
    Date t2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_billet_info, null, false);
        drawer.addView(contentView, 0);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TOKEN,"");
        stime = findViewById(R.id.starttime);
        bcount = findViewById(R.id.billetcount);
        blength = findViewById(R.id.billetlength);
        etime = findViewById(R.id.endtimebreakdown);
        remarks = findViewById(R.id.remark);
        mbcount = findViewById(R.id.mbilletcount);
        mblength = findViewById(R.id.mbilletlength);
        mcuttype = findViewById(R.id.mcuttype);
        mstime = findViewById(R.id.mstarttime);
        mendtime = findViewById(R.id.mendtime);
        layout = findViewById(R.id.millstop);
        imageView1 = findViewById(R.id.millstopimageadd);
        imageView2 = findViewById(R.id.crossmillstop);
        billetid = LoginName.billetid;
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

    //show mill stop
    public void expand(View view) {
        layout.setVisibility(View.VISIBLE);
        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.VISIBLE);
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

    Date mt1,mt2;
    String montimeimage, mofftimeimage;
    public boolean montimedone = false, mofftimedone = false;
    public void changecolor2(View view) {
        int idimage = view.getId();
        ImageView imageView = findViewById(idimage);
        if (imageView == findViewById(R.id.montimeimage) && !montimedone) {
            DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
            int mHour, mMinute;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            montimeimage = mHour + ":" + mMinute;
            montimedone = true;
            mt1 = c.getTime();
        } else if (imageView == findViewById(R.id.mofftimeimage) && !mofftimedone) {
            DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.blue));
            int mHour, mMinute;
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            mofftimeimage = mHour + ":" + mMinute;
            mofftimedone = true;
            mt2 = c.getTime();
        }
    }

    public void collapse(View view) {
        layout.setVisibility(View.GONE);
        imageView1.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.GONE);
        mendtime.setText("");
        montimedone = false;
        mofftimedone = false;
        mstime.setText("");
        mbcount.setText("");
        mblength.setText("");
        ImageView imageView1,imageView2;
        imageView1 = findViewById(R.id.montimeimage);
        imageView2 = findViewById(R.id.mofftimeimage);
        DrawableCompat.setTint(imageView1.getDrawable(), ContextCompat.getColor(this, R.color.green));
        DrawableCompat.setTint(imageView2.getDrawable(), ContextCompat.getColor(this, R.color.green));
    }

    public void addmilldetailnow(View view) {

        Log.v("message dekh",mt1.toString()+mt2.toString());
        if (TextUtils.isEmpty(mstime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Mill Stop Start Time feilds.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mblength.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All Mill Stop field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mbcount.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All Mill  field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mendtime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Mill field", Toast.LENGTH_SHORT).show();
        } else if (mcuttype.getSelectedItemPosition()==0) {
            Toast.makeText(getApplicationContext(), "Enter Mill field", Toast.LENGTH_SHORT).show();
        } else if (!montimedone) {
            Toast.makeText(getApplicationContext(), "Select Mill Plus button start time", Toast.LENGTH_SHORT).show();
        } else if (!mofftimedone) {
            Toast.makeText(getApplicationContext(), "Select Mill plus button end time", Toast.LENGTH_SHORT).show();
        } else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BilletInfo.this);

            alertDialogBuilder.setTitle("Adding");

            alertDialogBuilder.setMessage("Adding please wait ...!!").setCancelable(false);

            final AlertDialog alertDialog = alertDialogBuilder.create();

            String cuttype;
            if(mcuttype.getSelectedItemPosition()==1)
            {
                cuttype="MANL";
            }
            else
            {
                cuttype = "AUTO";
            }

            alertDialog.show();
            MillStopModel millStopModel
                    = new MillStopModel(
                    billetid,
                    montimeimage, mofftimeimage,
                    mstime.getText().toString()
                    , Integer.parseInt(mblength.getText().toString())
                    , Integer.parseInt(mbcount.getText().toString())
                    , mendtime.getText().toString()
                    , cuttype
            );
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginShift.url)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<MillStopModel> call = jsonPlaceHolder.milldetailaddnow(text, millStopModel);
            call.enqueue(new Callback<MillStopModel>() {
                @Override
                public void onResponse(Call<MillStopModel> call, Response<MillStopModel> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Server Down ", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        return;
                    }
                    MillStopModel BilletModel = response.body();

                    Toast.makeText(getApplicationContext(), BilletModel.getMsg(), Toast.LENGTH_SHORT).show();
                    mendtime.setText("");
                    mstime.setText("");
                    mbcount.setText("");
                    montimedone = false;
                    mofftimedone = false;
                    mblength.setText("");
                    ImageView imageView11, imageView22;
                    imageView11 = findViewById(R.id.montimeimage);
                    imageView22 = findViewById(R.id.mofftimeimage);
                    DrawableCompat.setTint(imageView11.getDrawable(), ContextCompat.getColor(getBaseContext(), R.color.green));
                    DrawableCompat.setTint(imageView22.getDrawable(), ContextCompat.getColor(getBaseContext(), R.color.green));
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.GONE);
                    alertDialog.dismiss();
                    layout.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<MillStopModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });
        }

    }

    public void submit(View view) {
        if (TextUtils.isEmpty(stime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Start Time for Induction.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(blength.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(bcount.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etime.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter End Time for Induction", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(remarks.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (!offtimedone) {
            Toast.makeText(getApplicationContext(), "Select Plus button of start time for induction", Toast.LENGTH_SHORT).show();
        } else if (!ontimedone) {
            Toast.makeText(getApplicationContext(), "Select Plus button of end time for induction", Toast.LENGTH_SHORT).show();
        }
        else {
            myapi();
        }
    }
    public void myapi() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BilletInfo.this);
        alertDialogBuilder.setTitle("Submission");
        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        BilletModel billetModel
                = new BilletModel(
                        billetid,
                ontimeimage,offtimeimage,
                LoginName.loginname,
                LoginName.shiftday,
                FurnaceInfo.ActFurnaceNo,
                stime.getText().toString()
                , Integer.parseInt(blength.getText().toString())
                , Integer.parseInt(bcount.getText().toString())
                , etime.getText().toString()
                , remarks.getText().toString()
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<BilletModel> call = jsonPlaceHolder.billetdetail(text,billetModel);
        call.enqueue(new Callback<BilletModel>() {
            @Override
            public void onResponse(Call<BilletModel> call, Response<BilletModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Server Down ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                BilletModel BilletModel = response.body();
                if (!BilletModel.isSuccess()) {
                    Toast.makeText(getApplicationContext(), BilletModel.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Intent intent = new Intent(BilletInfo.this, BilletInfo.class);
                    startActivity(intent);
                    alertDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<BilletModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
}
