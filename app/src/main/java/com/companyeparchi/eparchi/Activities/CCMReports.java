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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.companyeparchi.eparchi.Adaptor.CCMAdaptor;
import com.companyeparchi.eparchi.Models.DccmModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class CCMReports extends samplenav {

    Spinner sequenceno;
    TextView reason, tdt, sizecc,datetext;
    ConstraintLayout reasonlayout, layouttemp;
    RadioGroup daterg;
    List<String> sequence = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_ccmreports, null, false);
        drawer.addView(contentView, 0);
        sequenceno = findViewById(R.id.sequenceno);
        reason = findViewById(R.id.reasonccm);
        tdt = findViewById(R.id.turndishtemp);
        sizecc = findViewById(R.id.sizeofcc);
        reasonlayout = findViewById(R.id.layoutreason);
        reasonlayout.setVisibility(View.GONE);
        layouttemp = findViewById(R.id.layouttemp);
        layouttemp.setVisibility(View.GONE);
        daterg = findViewById(R.id.dateradiogroup);
        datetext = findViewById(R.id.textView42);
        getsequenceapi();
    }


    public void showall(View view)
    {
        datenow = "00-00-0000";
        datetext.setText("Date");
        getsequenceapi();
    }

    public void showdate(View view)
    {
        Selectdate();
    }
    public void getsequenceapi() {
        sequence.clear();
        sequence.add("Select Sequence No.");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CCMReports.this);

        alertDialogBuilder.setTitle("Setting up");

        alertDialogBuilder.setMessage("Please wait ...!!").setCancelable(false);

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
        Call<DccmModel> call = jsonPlaceHolder.getccmsequence(text,datenow);
        call.enqueue(new Callback<DccmModel>() {

            @Override
            public void onResponse(Call<DccmModel> call, Response<DccmModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(CCMReports.this, "Not able to reach server", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                if (response.body().getCount()) {
                    int n = response.body().getNo();
                    for (int i = 1; i <= n; i++) {
                        sequence.add(String.valueOf(i));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CCMReports.this, android.R.layout.simple_spinner_item, sequence);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sequenceno.setAdapter(adapter);

                }
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DccmModel> call, Throwable t) {
                Toast.makeText(CCMReports.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
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
                        getsequenceapi();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    public void updatedata(View view) {

        if (sequenceno.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please Select Sequence No.", Toast.LENGTH_SHORT).show();
        } else {
            myapiCCMReports(sequenceno.getSelectedItem().toString());
        }
    }

    public void myapiCCMReports(String seq) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CCMReports.this);

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
        Call<DccmModel> call = jsonPlaceHolder.getccmreports(text, seq,datenow);
        call.enqueue(new Callback<DccmModel>() {

            @Override
            public void onResponse(Call<DccmModel> call, Response<DccmModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(CCMReports.this, "Not able to reach server", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }

                    reason.setText(response.body().getReason().toString());
                    tdt.setText(response.body().getTdt().toString());
                    sizecc.setText(response.body().getCsize().toString());
                    layouttemp.setVisibility(View.VISIBLE);
                    reasonlayout.setVisibility(View.VISIBLE);

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ccmreportsrv);
                //recyclerView.setNestedScrollingEnabled(false);
                CCMAdaptor mAdapter = new CCMAdaptor(response.body().getData());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                Toast.makeText(CCMReports.this, "Reports Updated", Toast.LENGTH_SHORT).show();
                Log.v("reponsse is ", response.body().getMsg());
                alertDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DccmModel> call, Throwable t) {
                Toast.makeText(CCMReports.this, "Network Error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
    }
}
