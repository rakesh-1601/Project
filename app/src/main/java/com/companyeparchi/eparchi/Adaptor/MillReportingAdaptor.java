package com.companyeparchi.eparchi.Adaptor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Activities.MillReports;
import com.companyeparchi.eparchi.Models.MillreportsModel1;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class MillReportingAdaptor extends RecyclerView.Adapter<MillReportingAdaptor.ViewHolder>{

    ArrayList<MillreportsModel1> millreportsModel1s   = MillReports.dmillReportsModel.getMdata().get(MillReports.number);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.millreportingitem1, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MillreportsModel1 millreportsModel1 = millreportsModel1s.get(position);
        holder.textView1.setText(millreportsModel1.getTime().toString());
        holder.textView2.setText(millreportsModel1.getSize().toString());
        holder.textView3.setText(millreportsModel1.getCount().toString());
        holder.textView4.setText(millreportsModel1.getLength().toString());
        holder.textView5.setText(millreportsModel1.getQcheck().toString());
    }


    @Override
    public int getItemCount() {
        return millreportsModel1s.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.time);
            this.textView2 = (TextView) itemView.findViewById(R.id.size);
            this.textView3 = (TextView) itemView.findViewById(R.id.quantity);
            this.textView4 = (TextView) itemView.findViewById(R.id.sectionwt);
            this.textView5 = (TextView) itemView.findViewById(R.id.qualitycheck);
        }
    }


}
