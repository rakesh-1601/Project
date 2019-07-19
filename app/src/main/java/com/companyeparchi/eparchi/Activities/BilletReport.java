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
import android.widget.TextView;
import android.widget.Toast;

import com.companyeparchi.eparchi.Adaptor.BilletAdaptor;
import com.companyeparchi.eparchi.Models.DbilletModel;
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

public class BilletReport extends samplenav {

    RecyclerView recyclerView;
    public static Integer number;
    TextView datetext;

    public  static DbilletModel billetModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_billet_report, null, false);
        drawer.addView(contentView, 0);
        recyclerView = (RecyclerView) findViewById(R.id.billetrv);
        datetext = findViewById(R.id.textView42);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(BilletReport.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        number = position;
                        Intent intent = new Intent(BilletReport.this,billetreportchild.class);
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

    public  void submit(View view)
    {
            myapiBillet();
    }



    public void myapiBillet() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BilletReport.this);

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
        String text = sharedPreferences.getString(TOKEN, "");
        Call<DbilletModel> call = jsonPlaceHolder.getbilletdetail(text,datenow);
        call.enqueue(new Callback<DbilletModel>() {

            @Override
            public void onResponse(Call<DbilletModel> call, Response<DbilletModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(BilletReport.this,"Unable to connect server at this moment", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                billetModels = response.body();
                BilletAdaptor mAdapter = new BilletAdaptor(response.body().getData());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                if(!billetModels.getCount()) {
                    Toast.makeText(BilletReport.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BilletReport.this, "Reports Updated", Toast.LENGTH_SHORT).show();
                }
                Log.v("reponsse is ", response.body().getMsg());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DbilletModel> call, Throwable t) {
                Toast.makeText(BilletReport.this, "Network error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
    }
}