package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.companyeparchi.eparchi.Adaptor.Millitemadaptor;
import com.companyeparchi.eparchi.Models.DmillReportsModel;
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

public class MillReports extends samplenav {

    Spinner millno;
    RecyclerView recyclerView;
    public static Integer number = 0;
    public static DmillReportsModel dmillReportsModel;
    TextView datetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_mill_reports, null, false);
        drawer.addView(contentView, 0);
        millno = findViewById(R.id.spinnermill);
        recyclerView = (RecyclerView) findViewById(R.id.millrv);
        datetext = findViewById(R.id.textView42);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MillReports.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        number = position;
                        Intent intent = new Intent(MillReports.this,millreportchild.class);
                        startActivity(intent);
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void showall(View view)
    {
        datenow = "00-00-0000";
        datetext.setText("Date");
    }

    public void showdate(View view)
    {
        Selectdate();

    }
    String datenow = "00-00-0000";
    public void Selectdate()
    {
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        datetext.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        datenow = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void updatedata(View view)
    {

        if(millno.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "Please Select Mill number", Toast.LENGTH_SHORT).show();
        }
        else
        {

            myapiMillReports(millno.getSelectedItem().toString());
        }
    }
    public void myapiMillReports(String seq) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReports.this);

        alertDialogBuilder.setTitle("Getting Reports");

        alertDialogBuilder.setMessage("Getting Reports please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        final String text = sharedPreferences.getString(TOKEN, "");
        Call<DmillReportsModel> call = jsonPlaceHolder.getmillreportsdashboard(text, seq,datenow);
        call.enqueue(new Callback<DmillReportsModel>() {

            @Override
            public void onResponse(Call<DmillReportsModel> call, Response<DmillReportsModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(MillReports.this, "Not able to reach server", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                if(response.body().getCount()) {
                    dmillReportsModel = response.body();
                    Millitemadaptor mAdapter = new Millitemadaptor(Integer.parseInt(dmillReportsModel.getOcount()));
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
                Toast.makeText(MillReports.this, "Reports Updated", Toast.LENGTH_SHORT).show();
                Log.v("reponsse is ", response.body().getMsg());
                alertDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DmillReportsModel> call, Throwable t) {
                Toast.makeText(MillReports.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
    }
}
