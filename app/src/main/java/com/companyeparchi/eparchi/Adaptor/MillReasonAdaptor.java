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

public class MillReasonAdaptor extends RecyclerView.Adapter<MillReasonAdaptor.ViewHolder>{

    ArrayList<MillreportsModel1> millreportsModel1s   = MillReports.dmillReportsModel.getBdata().get(MillReports.number);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.millreportsitem2, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MillreportsModel1 millreportsModel1 = millreportsModel1s.get(position);

        if(millreportsModel1.getFlag())
        {
            holder.textView1.setText(millreportsModel1.getTimebig());
        }
        String a = millreportsModel1.getReason();
        String b = millreportsModel1.getTreason();
        holder.textView2.setText(b+"   "+a);
    }


    @Override
    public int getItemCount() {
        return millreportsModel1s.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.bigtime);
            this.textView2 = (TextView) itemView.findViewById(R.id.reason);
        }
    }


}
