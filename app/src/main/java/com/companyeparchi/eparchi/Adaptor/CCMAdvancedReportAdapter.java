package com.companyeparchi.eparchi.Adaptor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.DccmnewModel;
import com.companyeparchi.eparchi.R;

public class CCMAdvancedReportAdapter extends RecyclerView.Adapter<CCMAdvancedReportAdapter.ViewHolder>{
   DccmnewModel model;
    public CCMAdvancedReportAdapter(DccmnewModel model) {
        this.model = model;
    }
    @Override
    public CCMAdvancedReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.ccmadvanceditem, parent, false);
        CCMAdvancedReportAdapter.ViewHolder viewHolder = new CCMAdvancedReportAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(CCMAdvancedReportAdapter.ViewHolder holder, int position) {
        Log.v("position is ", String.valueOf(position));
        holder.textView1.setText(model.getData().get(position).getSno().toString());
        holder.textView2.setText(model.getData().get(position).getTheat().toString());
        holder.textView3.setText(model.getData().get(position).getTtime().toString());
        holder.textView4.setText(model.getData().get(position).getReason().toString());
        holder.textView5.setText(model.getData().get(position).getAtime().toString());
    }


    @Override
    public int getItemCount() {
        return model.getData().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.sequenceno);
            this.textView2 = (TextView) itemView.findViewById(R.id.totalheat);
            this.textView3 = (TextView) itemView.findViewById(R.id.totaltime);
            this.textView4 = (TextView) itemView.findViewById(R.id.reason);
            this.textView5 = itemView.findViewById(R.id.extra_time);
        }
    }
}
