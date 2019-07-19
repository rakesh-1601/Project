package com.companyeparchi.eparchi.Adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.BilletModel;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class BilletAdaptor extends RecyclerView.Adapter<BilletAdaptor.ViewHolder>{

    public ArrayList<BilletModel> billetModels   = new ArrayList<BilletModel>();

    // RecyclerView recyclerView;
    public BilletAdaptor(ArrayList<BilletModel> billetModels) {
        this.billetModels = billetModels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.billetreportitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BilletModel billetModel = billetModels.get(position);
        holder.textView1.setText(billetModel.getStime());
        holder.textView2.setText(billetModel.getEndtime().toString());
        holder.textView3.setText(billetModel.getTtime().toString());
        holder.textView4.setText(billetModel.getBcount().toString());
        holder.textView5.setText(billetModel.getBlength().toString());
    }


    @Override
    public int getItemCount() {
        return billetModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.heat);
            this.textView2 = (TextView) itemView.findViewById(R.id.ont);
            this.textView3 = (TextView) itemView.findViewById(R.id.ot);
            this.textView4 = (TextView) itemView.findViewById(R.id.count);
            this.textView5 = (TextView) itemView.findViewById(R.id.length);
        }
    }
}
