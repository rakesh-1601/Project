package com.companyeparchi.eparchi.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.companyeparchi.eparchi.R;

import static com.companyeparchi.eparchi.Activities.LoginShift.MYCASE;
import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class FurnaceInfo extends samplenav {
    RadioGroup rg;
    public static int ActFurnaceNo = 0;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_furnace_info, null, false);
        drawer.addView(contentView, 0);
        rg = findViewById(R.id.radioGroupFurnaceAct);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void okclicked(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(MYCASE,"");
        String text2 = sharedPreferences.getString(TOKEN,"");
        Log.v("case is NOW",text+text2);

        int idb = rg.getCheckedRadioButtonId();

        if (idb == -1) {
            Toast.makeText(getApplicationContext(), "Select Furnace", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton rb1 = findViewById(idb);
            if (rb1.getText().toString().equals("Furnace 1")) {
                ActFurnaceNo = 1;
            } else if (rb1.getText().toString().equals("Furnace 2")) {
                ActFurnaceNo = 2;
            } else if (rb1.getText().toString().equals("Furnace 3")) {
                ActFurnaceNo = 3;
            } else {
                ActFurnaceNo = 4;
            }


            if(text.equals("IFSU"))
            {
                Intent intent = new Intent(FurnaceInfo.this, InductionForm.class);
                startActivity(intent);
            }
            else if(text.equals("IFSE"))
            {
                Intent intent = new Intent(FurnaceInfo.this,StartEndSequence.class);
                startActivity(intent);
            }
        }
    }
}
