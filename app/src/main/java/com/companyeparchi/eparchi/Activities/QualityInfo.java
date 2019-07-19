package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.companyeparchi.eparchi.Models.QualityModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class QualityInfo extends samplenav {

    EditText hno, bpc, pc, psi, permn,pers,perp;
    RadioGroup rgf,rgs;
    RadioButton rb1,rb2;

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_quality, null, false);
        drawer.addView(contentView, 0);


        hno = findViewById(R.id.heatno);
        bpc = findViewById(R.id.batchperc);
        pc = findViewById(R.id.perc);
        psi = findViewById(R.id.persi);
        permn = findViewById(R.id.permn);
        pers = findViewById(R.id.pers);
        rgf = findViewById(R.id.radioGroupfurnace);
        rgs = findViewById(R.id.radioGrouptype);
        perp = findViewById(R.id.perp);
    }
    public void submit(View view)
    {
        Integer valueitem;
        String sampletype;
        int idb1 = rgf.getCheckedRadioButtonId();
        int idb2 = rgs.getCheckedRadioButtonId();
        if (TextUtils.isEmpty(hno.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter Heat no.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(permn.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(bpc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(psi.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pers.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Enter All field", Toast.LENGTH_SHORT).show();
        }else if (idb1 == -1) {
            Toast.makeText(getApplicationContext(), "Select Furnace", Toast.LENGTH_SHORT).show();
        } else if (idb2 == -1) {
            Toast.makeText(getApplicationContext(), "Select Sample type", Toast.LENGTH_SHORT).show();
        }else {
            rb1 = findViewById(idb1);
            if (rb1.getText().toString().equals("Furnace 1")) {
                valueitem = 1;
            } else if (rb1.getText().toString().equals("Furnace 2")) {
                valueitem = 2;
            }else if (rb1.getText().toString().equals("Furnace 3")) {
                valueitem = 3;
            }
            else
            {
                valueitem = 4;
            }
            rb2 = findViewById(idb2);
            if (rb2.getText().toString().equals("Lollypop")) {
                sampletype = "MLTI";
            } else{
                sampletype = "BLLT";
            }


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QualityInfo.this);

            alertDialogBuilder.setTitle("Submission");

            alertDialogBuilder.setMessage("Submitting please wait ...!!").setCancelable(false);

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();



            QualityModel qualityModel
                    = new QualityModel(
                    LoginName.loginname,
                    LoginName.shiftday
                            ,perp.getText().toString()
                    ,hno.getText().toString()
                    ,bpc.getText().toString()
                    , pc.getText().toString()
                    , psi.getText().toString()
                    , permn.getText().toString()
                    , pers.getText().toString()
                    , sampletype
                    ,valueitem.toString()
            );
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginShift.url)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            String text = sharedPreferences.getString(TOKEN,"");
            Call<QualityModel> call = jsonPlaceHolder.qualitydetail(text,qualityModel);
            call.enqueue(new Callback<QualityModel>() {
                @Override
                public void onResponse(Call<QualityModel> call, Response<QualityModel> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Server Down ",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        return;
                    }
                    QualityModel QualityModel = response.body();
                    if(!QualityModel.isSuccess())
                    {
                        Toast.makeText(getApplicationContext(),QualityModel.getMsg(),Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(QualityInfo.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(QualityInfo.this, QualityInfo.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<QualityModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
