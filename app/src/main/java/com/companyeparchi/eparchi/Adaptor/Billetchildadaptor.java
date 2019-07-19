package com.companyeparchi.eparchi.Adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.BilletModel;
import com.companyeparchi.eparchi.Models.MillStopModel;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class Billetchildadaptor extends RecyclerView.Adapter<Billetchildadaptor.ViewHolder>{

    public ArrayList<MillStopModel> millStopModels   = new ArrayList<MillStopModel>();

    // RecyclerView recyclerView;
    public Billetchildadaptor(ArrayList<MillStopModel> billetModels) {
        this.millStopModels = billetModels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.billetchilditem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MillStopModel millStopModel = millStopModels.get(position);
        holder.textView2.setText(millStopModel.getMstime().toString());
        holder.textView3.setText(millStopModel.getMendtime().toString());
        holder.textView4.setText(millStopModel.getMbcount().toString());
        holder.textView5.setText(millStopModel.getMblength().toString());
    }


    @Override
    public int getItemCount() {
        return millStopModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView2,textView3,textView4,textView5;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView2 = (TextView) itemView.findViewById(R.id.stime);
            this.textView3 = (TextView) itemView.findViewById(R.id.ttime);
            this.textView4 = (TextView) itemView.findViewById(R.id.count);
            this.textView5 = (TextView) itemView.findViewById(R.id.length);
        }
    }
}
