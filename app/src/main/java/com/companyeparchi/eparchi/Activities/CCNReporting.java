package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.MillStopModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class CCNReporting extends samplenav {

    public static Integer ccmid;
    public static String realtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_ccnreporting, null, false);
        drawer.addView(contentView, 0);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void SelectTime() {
        int mHour, mMinute;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        realtime = mHour+" : "+mMinute;
    }
    public void startsequence(View view)
    {
        getccmid();
    }

    private void getccmid() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CCNReporting.this);

        alertDialogBuilder.setTitle("Wait");

        alertDialogBuilder.setMessage("Starting your sequence ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN,"");
        JsonPlaceHolder jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);
        Call<MillStopModel> call = jsonPlaceHolderApi.getccmid(text);

        call.enqueue(new Callback<MillStopModel>() {
            @Override
            public void onResponse(Call<MillStopModel> call, Response<MillStopModel> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(CCNReporting.this, "Server Down", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillStopModel millStopModel= response.body();
                ccmid  = millStopModel.getId();
                Log.v("id is", String.valueOf(ccmid));
                SelectTime();
                alertDialog.dismiss();
                Intent intent = new Intent(CCNReporting.this, cnnreportingform.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<MillStopModel> call, Throwable t) {
                Toast.makeText(CCNReporting.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
}
