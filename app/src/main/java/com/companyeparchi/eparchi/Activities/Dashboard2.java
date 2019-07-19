package com.companyeparchi.eparchi.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.companyeparchi.eparchi.R;

public class Dashboard2 extends samplenav {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_dashboard2, null, false);
        drawer.addView(contentView, 0);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void openInductionDetailAdvanced(View view)
    {
        Intent intent = new Intent(Dashboard2.this,InductionDashboard2.class);
        startActivity(intent);
    }

    public void openInductionDetail(View view)
    {
        Intent intent = new Intent(Dashboard2.this,InductionReport.class);
        startActivity(intent);
    }

    public void OpenQualityDetail(View view) {
        Intent intent = new Intent(Dashboard2.this, QualityReport.class);
        startActivity(intent);
    }

    public void OpenBilletDetail(View view) {
        Intent intent = new Intent(Dashboard2.this, BilletReport.class);
        startActivity(intent);
    }

    public void OpenEndCuttingDetail(View view) {
        Intent intent = new Intent(Dashboard2.this, EndCuttingReport.class);
        startActivity(intent);
    }

    public void openccmreports(View view)
    {
        Intent intent = new Intent(Dashboard2.this, CCMReports.class);
        startActivity(intent);
    }

    public void openccmadvanced(View view)
    {
        Intent intent = new Intent(Dashboard2.this, CCMReportsadvanced.class);
        startActivity(intent);
    }

    public void openmillreports(View view)
    {
        Intent intent = new Intent(Dashboard2.this, MillReports.class);
        startActivity(intent);
    }

}
