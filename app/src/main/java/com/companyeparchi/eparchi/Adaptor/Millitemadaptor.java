package com.companyeparchi.eparchi.Adaptor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.R;

public class Millitemadaptor extends RecyclerView.Adapter<Millitemadaptor.ViewHolder>{

    Integer numberofobjects;
    public Millitemadaptor(Integer numberofobjects) {
        this.numberofobjects = numberofobjects;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.millitems, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView1.setText("Report - "+(position+1));
    }

    @Override
    public int getItemCount() {
        return numberofobjects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.reportno);
        }
    }


}
