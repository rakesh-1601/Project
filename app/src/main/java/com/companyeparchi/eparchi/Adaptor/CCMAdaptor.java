package com.companyeparchi.eparchi.Adaptor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.CNNFormModel;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class CCMAdaptor extends RecyclerView.Adapter<CCMAdaptor.ViewHolder>{

    ArrayList<CNNFormModel> cnnFormModels   = new ArrayList<CNNFormModel>();

    public CCMAdaptor(ArrayList<CNNFormModel> cnnFormModels) {

        Log.v("size is", String.valueOf(cnnFormModels.size()));
        this.cnnFormModels = cnnFormModels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.ccmreportsitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CNNFormModel cnnFormModel = cnnFormModels.get(position);
        holder.textView1.setText(cnnFormModel.getHeat().toString());
        holder.textView2.setText(cnnFormModel.getStarttime().toString());
        holder.textView3.setText(cnnFormModel.getEndtime().toString());
        holder.textView4.setText(cnnFormModel.getLtemp().toString());
        holder.textView5.setText(cnnFormModel.getLweight().toString());
    }


    @Override
    public int getItemCount() {
        return cnnFormModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.het_no);
            this.textView2 = (TextView) itemView.findViewById(R.id.stime_value);
            this.textView3 = (TextView) itemView.findViewById(R.id.etime_value);
            this.textView4 = (TextView) itemView.findViewById(R.id.laddle_temp);
            this.textView5 = (TextView) itemView.findViewById(R.id.laddle_weight);
        }
    }


}
