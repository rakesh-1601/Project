package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.companyeparchi.eparchi.Adaptor.InductionAdaptor;
import com.companyeparchi.eparchi.Adaptor.InductionFragmentAdapter;
import com.companyeparchi.eparchi.Adaptor.newInductionAdapter;
import com.companyeparchi.eparchi.Models.DinductionModel;
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

public class InductionDashboard2 extends AppCompatActivity {

    TextView datetext;
    public static String datenow = "00-00-0000";
    public static DinductionModel dinductionModel;
    public static Integer number = 0;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewGroup layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_induction_dashboard2);
        datetext = findViewById(R.id.textView42);
        viewPager = findViewById(R.id.inductionpager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new InductionFragmentAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void showall(View view) {
        datenow = "00-00-0000";
        datetext.setText("Date");
        viewPager.setAdapter(new InductionFragmentAdapter(getSupportFragmentManager()));
    }

    public void showdate(View view) {
        Selectdate();

    }

    public void Selectdate() {
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
                        datenow = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        viewPager.setAdapter(new InductionFragmentAdapter(getSupportFragmentManager()));
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}