package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
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

import com.companyeparchi.eparchi.Adaptor.InductionAdaptor;
import com.companyeparchi.eparchi.Models.DinductionModel;
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

public class InductionReport extends samplenav {

    public Spinner furnace;
    ConstraintLayout reasonlayout;
    TextView datetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_induction_report, null, false);
        drawer.addView(contentView, 0);
        reasonlayout = findViewById(R.id.pop2);
        datetext = findViewById(R.id.textView42);

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

    public void hidereason(View view)
    {
        reasonlayout.setVisibility(View.GONE);
    }
    public void updatedata(View view)
    {
        furnace = findViewById(R.id.spinner6);
        if(furnace.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "Please select furnace no.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            myapiInduction(String.valueOf(furnace.getSelectedItemPosition()));
        }
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

    public void myapiInduction(String furnace) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InductionReport.this);

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
        Call<DinductionModel> call = jsonPlaceHolder.getinductiondetail(text, furnace,datenow);
        call.enqueue(new Callback<DinductionModel>() {

            @Override
            public void onResponse(Call<DinductionModel> call, Response<DinductionModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(InductionReport.this, response.message(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.inductionrv);

                InductionAdaptor mAdapter = new InductionAdaptor(response.body().getData());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(InductionReport.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {


                                TextView textview = view.findViewById(R.id.reason);
                                TextView textview2 = view.findViewById(R.id.redflag);
                                if(textview2.getText().toString().equals("red")) {
                                    reasonlayout.setVisibility(View.VISIBLE);
                                    TextView myreason = reasonlayout.findViewById(R.id.reasonwritten);
                                    myreason.setText(textview.getText().toString());
                                }
                                // do whatever
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

                Toast.makeText(InductionReport.this, "Reports Updated", Toast.LENGTH_SHORT).show();
                Log.v("reponsse is ", response.body().getMsg());
                alertDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DinductionModel> call, Throwable t) {
                Toast.makeText(InductionReport.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
    }
}
