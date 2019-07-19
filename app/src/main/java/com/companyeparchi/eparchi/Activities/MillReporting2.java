package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.MillReportingModel2;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class MillReporting2 extends samplenav {
    ConstraintLayout layout1, layout30, page2;
    TextView textView, billetcount;
    Spinner length;
    Spinner material;
    TextView nocounter;
    Spinner number;
    Date t1,t2;

    List<String> mill12 = new ArrayList<>(Arrays.asList("Select Material type",
            "A - 40*40*5",
            "A - 40*40*6",
            "A - 50*50*4",
            "A - 50*50*5",
            "A - 50*50*6"
    ));
    List<String> mill9 = new ArrayList<>(Arrays.asList("Select Material type",
            "HA - 25*25*3",
            "HA - 25*25*5",
            "HA - 32*32*3",
            "HA - 35*35*5",
            "MA - 40*40*3",
            "MA - 40*40*4",
            "MA - 40*40*5",
            "MA - 40*40*6"

    ));
    List<String> mill20angle = new ArrayList<>(Arrays.asList("Select Material type",
            "A - 65*65*5",
            "A - 65*65*6",
            "A - 65*65*8",
            "A - 65*65*10",
            "A - 75*75*5",
            "A - 75*75*6",
            "A - 75*75*8",
            "A - 75*75*10",
            "A - 75*75*12"
    ));
    List<String> mill20channel = new ArrayList<>(Arrays.asList("Select Material type",
            "MC -75*40-5.5 KG M 4*6.5",
            "MC -75*40-6.5 KG H 4.8*7.5",
            "MC -100*50-8.5 KG M 5*7",
            "MC -100*50-9.5 KG H 5*7.7",
            "MC -125*65-5.5 KG M 5.3*8.2",
            "MC -150*75-6.5 KG M 5.7*9"
    ));

    List<String> length20 = new ArrayList<>(Arrays.asList(
            "12",
            "24",
            "25"
    ));
    List<String> length12 = new ArrayList<>(Arrays.asList(
            "12",
            "36",
            "42",
            "30"
    ));
    List<String> length9 = new ArrayList<>(Arrays.asList("9"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_mill_reporting2, null, false);
        drawer.addView(contentView, 0);
        layout30 = findViewById(R.id.popup);
        layout1 = findViewById(R.id.popup2);
        page2 = findViewById(R.id.page2);
        textView = findViewById(R.id.timeinterval);
        billetcount = findViewById(R.id.currentcount);
        length = findViewById(R.id.billet_length);
        material = findViewById(R.id.spinner3);
        nocounter = findViewById(R.id.textView19);
        number = findViewById(R.id.spinner5);
        int a = MillReporting1.Length;
        int b = MillReporting1.flag;
        length.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                    billetcount.setText("-");
                    material.setSelection(0);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        if (a == 12) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, mill12);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            material.setAdapter(adapter);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, length12);

            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            length.setAdapter(adapter1);

        } else if (a == 9) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, mill9);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            material.setAdapter(adapter);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, length9);

            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            length.setAdapter(adapter1);
        }
        else
        {

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, length20);

            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            length.setAdapter(adapter1);


            if(b==1)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_spinner_item, mill20angle);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material.setAdapter(adapter);
            }
            else
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_spinner_item, mill20channel);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                material.setAdapter(adapter);
            }
        }

        material.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Integer position = material.getSelectedItemPosition();
                if (position == 0) {
                    billetcount.setText("--");
                } else {
                    getbilletcount(Integer.parseInt(length.getSelectedItem().toString()), material.getSelectedItem().toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                Integer position = number.getSelectedItemPosition();
                if (position == 0) {
                    nocounter.setText("--");
                } else {
                    getnocounterapi(Integer.parseInt(number.getSelectedItem().toString()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        delay(layout30,1800000);
        delay(layout1,3600000);
    }


    @Override
    public void onBackPressed() {
        page2.setVisibility(View.GONE);
        return;
    }

    public void addtwoclicked(View view) {
        if (number.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Enter number ", Toast.LENGTH_SHORT).show();
        } else {
            setnocounterapi(Integer.parseInt(number.getSelectedItem().toString()));
        }
    }

    public void getnocounterapi(Integer position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Getting data");
        alertDialogBuilder.setMessage("Please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        MillReportingModel2 millReportingModel2 = new MillReportingModel2(-1, position,
                LoginName.loginname,
                LoginName.shiftday, MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call;
        call = jsonPlaceHolder.getnocountermill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();

                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    nocounter.setText(millReportingModel1.getCount().toString());
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void setnocounterapi(Integer position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Adding data");
        alertDialogBuilder.setMessage("Please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        MillReportingModel2 millReportingModel2 = new MillReportingModel2(
                Integer.parseInt(nocounter.getText().toString()),
                position,
                LoginName.loginname,
                LoginName.shiftday, MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call;
        call = jsonPlaceHolder.setnocountermill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();

                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                    nocounter.setText(millReportingModel1.getCount().toString());
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void SelectTime(View view) {
        int mHour, mMinute;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        final TextView editText = findViewById((view.getId()));
        Log.v("id is", editText.toString());
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        editText.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


    String realstarttime, realendtime;
    Boolean realstartdone, realenddone;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changecolorstart(View view) {
        int idb = view.getId();
        Button button = findViewById(idb);
        button.setBackground(ContextCompat.getDrawable(this,R.drawable.roundbuttongreen));
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        realstarttime = mHour + ":" + mMinute;
        realstartdone = true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changecolorend(View view) {
        int idb = view.getId();
        Button button = findViewById(idb);
        button.setBackground(ContextCompat.getDrawable(this,R.drawable.roundbuttongreen));
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        realendtime = mHour + ":" + mMinute;
        realenddone = true;
    }

    public void submit30(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Submission");
        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        EditText reason30 = findViewById(R.id.reason30);
        MillReportingModel2 millReportingModel2
                = new MillReportingModel2(
                reason30.getText().toString(), LoginName.loginname, LoginName.shiftday, MillReporting1.millid, 1
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call = jsonPlaceHolder.reason30mill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();
                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    delay(layout30, 1800000);
                    layout30.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

    }

    public void submit1hr(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Submission");
        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        EditText reason1 = findViewById(R.id.reason1);
        MillReportingModel2 millReportingModel2
                = new MillReportingModel2(
                reason1.getText().toString(), LoginName.loginname, LoginName.shiftday, MillReporting1.millid, 2
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call = jsonPlaceHolder.reason1mill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();
                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    delay(layout1, 3600000);
                    layout1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

    }

    public void delay(final ConstraintLayout mylayout, int time) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mylayout.setVisibility(View.VISIBLE);
            }
        }, time);
    }

    TextView startbreak, endbreak, reasonbreak;

    public void pg2submitclick(View view) {
        reasonbreak = findViewById(R.id.breakdownreason);
        startbreak = findViewById(R.id.starttimebreakdown);
        endbreak = findViewById(R.id.endtimebreakdown);
        if (TextUtils.isEmpty(reasonbreak.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter BreakDown Reason.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(startbreak.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Start Time", Toast.LENGTH_SHORT).show();
        } else if (!realstartdone) {
            Toast.makeText(getApplicationContext(), " Press Start Time Button ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(endbreak.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Start Time", Toast.LENGTH_SHORT).show();
        } else if (!realenddone) {
            Toast.makeText(getApplicationContext(), " Press Start Time Button ", Toast.LENGTH_SHORT).show();
        } else {
            submitbreakdownapi();
        }
    }

    public void submitbreakdownapi() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Submission");
        alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        MillReportingModel2 millReportingModel2
                = new MillReportingModel2(
                startbreak.getText().toString()
                , endbreak.getText().toString()
                , realstarttime
                , realendtime
                , reasonbreak.getText().toString()
                , LoginName.loginname
                , LoginName.shiftday, MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call = jsonPlaceHolder.millbreakdownreport2(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();
                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    page2.setVisibility(View.GONE);
                    startbreak.setText("");
                    endbreak.setText("");
                    reasonbreak.setText("");
                    realenddone = false;
                    realstartdone = false;
                    Button button = findViewById(R.id.starttimereal);
                    Button button1 = findViewById(R.id.endtimereal);
                    button.setBackground(ContextCompat.getDrawable(MillReporting2.this,R.drawable.roundbuttonyellow));
                    button.setBackground(ContextCompat.getDrawable(MillReporting2.this,R.drawable.roundbuttonyellow));
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void pg1submitclick(View view) {
        Intent intent = new Intent(MillReporting2.this, MillReporting4.class);
        startActivity(intent);
    }


    public void breakdownpg1(View view) {
        page2.setVisibility(View.VISIBLE);
        Calendar rightNow = Calendar.getInstance();
        String indicator;
        String startt, endt;
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);
        if (currentHourIn24Format < 11) {
            startt = String.valueOf(currentHourIn24Format);
            endt = String.valueOf((currentHourIn24Format + 1));
            indicator = " AM ";
        } else if (currentHourIn24Format < 12) {
            startt = "11";
            endt = "12";
            indicator = " AM ";
        } else if (currentHourIn24Format < 13) {
            startt = "12";
            endt = "1";
            indicator = " PM ";
        } else {
            startt = String.valueOf((currentHourIn24Format - 12));
            endt = String.valueOf((currentHourIn24Format - 11));
            indicator = " PM ";
        }
        textView.setText(startt + indicator + "     to     " + endt + indicator);
    }


    public void addoneclicked(View view) {
        if (material.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Enter Material Type", Toast.LENGTH_SHORT).show();
        } else {
            addbilletcount(Integer.parseInt(length.getSelectedItem().toString()), material.getSelectedItem().toString());

        }
    }

    public void addbilletcount(Integer millno, String material) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("adding data");
        alertDialogBuilder.setMessage("Please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        MillReportingModel2 millReportingModel2 = new MillReportingModel2(
                Integer.parseInt(billetcount.getText().toString()), millno, material, LoginName.loginname,
                LoginName.shiftday, MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call;
        call = jsonPlaceHolder.setbilletcountermill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();

                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    Log.v("rushi", millReportingModel1.getCount().toString());
                    billetcount.setText(millReportingModel1.getCount().toString());
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void decrementbillet(View view) {
        if (material.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Select Material Tyep", Toast.LENGTH_SHORT).show();
        } else {
            reducebilletcount(Integer.parseInt(length.getSelectedItem().toString()), material.getSelectedItem().toString());

        }
    }

    public void reducebilletcount(Integer millno, String material) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Updating data");
        alertDialogBuilder.setMessage("Please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        MillReportingModel2 millReportingModel2 = new MillReportingModel2(
                Integer.parseInt(billetcount.getText().toString()), millno, material, LoginName.loginname,
                LoginName.shiftday, MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call;
        call = jsonPlaceHolder.reducebilletcountermill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();

                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    billetcount.setText(millReportingModel1.getCount().toString());
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
    public void getbilletcount(Integer millno, String material) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MillReporting2.this);
        alertDialogBuilder.setTitle("Getting data");
        alertDialogBuilder.setMessage("Please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        MillReportingModel2 millReportingModel2 = new MillReportingModel2(
                -1, millno, material, LoginName.loginname,
                LoginName.shiftday, MillReporting1.millid
        );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TOKEN, "");
        Call<MillReportingModel2> call;
        call = jsonPlaceHolder.getbilletcountermill(text, millReportingModel2);
        call.enqueue(new Callback<MillReportingModel2>() {
            @Override
            public void onResponse(Call<MillReportingModel2> call, Response<MillReportingModel2> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), " Inavlid Request ", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                MillReportingModel2 millReportingModel1 = response.body();

                if (!millReportingModel1.isSuccess()) {
                    Toast.makeText(getApplicationContext(), millReportingModel1.getMsg(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                } else {
                    billetcount.setText(millReportingModel1.getCount().toString());
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MillReportingModel2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
}
