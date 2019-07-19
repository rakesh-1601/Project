package com.companyeparchi.eparchi.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.companyeparchi.eparchi.Adaptor.MillReasonAdaptor;
import com.companyeparchi.eparchi.Adaptor.MillReportingAdaptor;
import com.companyeparchi.eparchi.R;

public class millreportchild extends samplenav {

    TextView bst,bet,date,mst;
    RecyclerView recyclerView1,recyclerView2;
    TextView no1,no2,no3,no4,no5,no6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.millreportingitem3, null, false);
        drawer.addView(contentView, 0);
        bst = findViewById(R.id.blowerstarttime);
        bet = findViewById(R.id.blowerendtime);
        date = findViewById(R.id.date);
        mst = findViewById(R.id.millstarttime);
        recyclerView1 = findViewById(R.id.millreportsrv);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView2 = findViewById(R.id.breakdownrv);
        recyclerView2.setNestedScrollingEnabled(false);
        no1 = findViewById(R.id.no1);
        no2 = findViewById(R.id.no2);
        no3 = findViewById(R.id.no3);
        no4 = findViewById(R.id.no4);
        no5 = findViewById(R.id.no5);
        no6 = findViewById(R.id.no6);
        setupdata();
    }

    public void setupdata()
    {
        bst.setText(MillReports.dmillReportsModel.getHdata().get(MillReports.number).getBstime());
        bet.setText(MillReports.dmillReportsModel.getHdata().get(MillReports.number).getBendtime());
        date.setText(MillReports.dmillReportsModel.getHdata().get(MillReports.number).getDate());
        mst.setText(MillReports.dmillReportsModel.getHdata().get(MillReports.number).getMstime());
        MillReportingAdaptor  mAdapter = new MillReportingAdaptor();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView1.setLayoutManager(mLayoutManager);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(mAdapter);
        MillReasonAdaptor mAdapter1 = new MillReasonAdaptor();
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(mLayoutManager1);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter1);
        no1.setText(MillReports.dmillReportsModel.getNodetail().get(MillReports.number).getNo1().toString());
        no2.setText(MillReports.dmillReportsModel.getNodetail().get(MillReports.number).getNo2().toString());
        no3.setText(MillReports.dmillReportsModel.getNodetail().get(MillReports.number).getNo3().toString());
        no4.setText(MillReports.dmillReportsModel.getNodetail().get(MillReports.number).getNo4().toString());
        no5.setText(MillReports.dmillReportsModel.getNodetail().get(MillReports.number).getNo5().toString());
        no6.setText(MillReports.dmillReportsModel.getNodetail().get(MillReports.number).getNo6().toString());
    }
}
