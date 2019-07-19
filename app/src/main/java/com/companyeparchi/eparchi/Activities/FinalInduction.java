package com.companyeparchi.eparchi.Activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.companyeparchi.eparchi.R;

public class FinalInduction extends AppCompatActivity {

    public TextView textView1, textView2, textView3, textView4, textView5, textView6, textView14, textView8, textView9, textView10, textView11, textView12, textView13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inductionreportitem);
        textView1 = (TextView) findViewById(R.id.heat);
        textView2 = (TextView) findViewById(R.id.ont);
        textView3 = (TextView) findViewById(R.id.ot);
        textView4 = (TextView) findViewById(R.id.tt);
        textView6 = (TextView) findViewById(R.id.reason);
        textView8 = findViewById(R.id.length);
        textView9 = findViewById(R.id.manganese);
        textView10 = findViewById(R.id.aluminium);
        textView11 = findViewById(R.id.silicon);
        textView12 = findViewById(R.id.scrap);
        textView13 = findViewById(R.id.extra_time);
        textView14 = findViewById(R.id.remark);
        setup();
    }

    public void setup() {
        textView1.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getHeat().toString());
        textView2.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getOntime().toString());
        textView3.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getOfftime().toString());
        textView4.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getTotaltime().toString());
        textView8.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getPf().toString());
        textView9.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getMn().toString());
        textView10.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getAluminum().toString());
        textView11.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getSilicon().toString());
        textView12.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getScrap().toString());
        if (!InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getReason().toString().equals("")) {
            textView6.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getReason().toString());
        }
        textView13.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getEtime().toString());
        if (!InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getRemark().toString().equals("")) {
            textView14.setText(InductionDashboard2.dinductionModel.getData().get(InductionDashboard2.number).getRemark().toString());
        }
    }
}
