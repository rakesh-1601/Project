package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
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

import com.companyeparchi.eparchi.Adaptor.QualityAdaptor;
import com.companyeparchi.eparchi.Models.DqualityModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import org.w3c.dom.Text;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class QualityReport extends samplenav {

    public Spinner furnace,billet;
    TextView datetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_quality_report, null, false);
        drawer.addView(contentView, 0);
        datetext = findViewById(R.id.textView43);
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


    public void updatedata(View view)
    {
        furnace = findViewById(R.id.spinner6);
        billet = findViewById(R.id.spinner7);
        if(furnace.getSelectedItemPosition()==0)
        {
            Toast.makeText(this, "Please select furnace no.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String sampletype;
            if (billet.getSelectedItemPosition()==1) {
                sampletype = "MLTI";
            } else if(billet.getSelectedItemPosition()==2) {
                sampletype = "BLLT";
            }
            else {
                sampletype = "ALL";
            }

            myapiQuality(String.valueOf(furnace.getSelectedItemPosition()),sampletype);
        }
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
    public void myapiQuality(String furnace, String billet) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QualityReport.this);
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
        Call<DqualityModel> call = jsonPlaceHolder.getqualitydetail(text, furnace, billet,datenow);
        call.enqueue(new Callback<DqualityModel>() {

            @Override
            public void onResponse(Call<DqualityModel> call, Response<DqualityModel> response)
            {
                if (!response.isSuccessful()) {
                    Toast.makeText(QualityReport.this, "Unable to connect server at this moment", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.qualityrv);
                QualityAdaptor mAdapter = new QualityAdaptor(response.body().getData());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                Toast.makeText(QualityReport.this, "Reports Updated", Toast.LENGTH_SHORT).show();
                Log.v("reponsse is ", response.body().getMsg());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DqualityModel> call, Throwable t) {
                Toast.makeText(QualityReport.this, "Network error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
    }
}
