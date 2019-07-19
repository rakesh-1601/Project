package com.companyeparchi.eparchi.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.companyeparchi.eparchi.Adaptor.BilletAdaptor;
import com.companyeparchi.eparchi.Adaptor.Billetchildadaptor;
import com.companyeparchi.eparchi.Models.MillStopModel;
import com.companyeparchi.eparchi.R;

public class billetreportchild extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billetreportchild);
        recyclerView = findViewById(R.id.recyclerms);
        Billetchildadaptor mAdapter = new Billetchildadaptor(BilletReport.billetModels.getData().get(BilletReport.number).getMillstopdata());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
