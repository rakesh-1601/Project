package com.companyeparchi.eparchi.Adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.QualityModel;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class QualityAdaptor extends RecyclerView.Adapter<QualityAdaptor.ViewHolder>{

    public ArrayList<QualityModel> qualityModels   = new ArrayList<QualityModel>();

    // RecyclerView recyclerView;
    public QualityAdaptor(ArrayList<QualityModel> qualityModels) {
        this.qualityModels = qualityModels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.qualityreportitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final QualityModel qualityModel = qualityModels.get(position);
        holder.textView.setText(qualityModel.getTmaterial().toString());
        holder.textView1.setText(qualityModel.getHeat());
        holder.textView2.setText(qualityModel.getPerc().toString());
        holder.textView3.setText(qualityModel.getPerp().toString());
        holder.textView4.setText(qualityModel.getPermn().toString());
        holder.textView5.setText(qualityModel.getPers().toString());
        holder.textView6.setText(qualityModel.getPersi().toString());
    }


    @Override
    public int getItemCount() {
        return qualityModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5,textView6,textView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.typematerial);
            this.textView1 = (TextView) itemView.findViewById(R.id.heat);
            this.textView2 = (TextView) itemView.findViewById(R.id.c);
            this.textView3 = (TextView) itemView.findViewById(R.id.p);
            this.textView4 = (TextView) itemView.findViewById(R.id.mn);
            this.textView5 = (TextView) itemView.findViewById(R.id.s);
            this.textView6 = (TextView) itemView.findViewById(R.id.si);
        }
    }
}
