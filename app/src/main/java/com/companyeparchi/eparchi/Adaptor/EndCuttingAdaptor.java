package com.companyeparchi.eparchi.Adaptor;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.EndCuttingModel;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class EndCuttingAdaptor extends RecyclerView.Adapter<EndCuttingAdaptor.ViewHolder>{

    ArrayList<EndCuttingModel> endCuttingModels   = new ArrayList<EndCuttingModel>();

    // RecyclerView recyclerView;
    public EndCuttingAdaptor(ArrayList<EndCuttingModel> endCuttingModelsModels) {
        this.endCuttingModels = endCuttingModelsModels;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.endcuttingreportitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EndCuttingModel endCuttingModel = endCuttingModels.get(position);
        holder.textView1.setText(String.valueOf(position+1));
        holder.textView2.setText(endCuttingModel.getStart().toString());
        holder.textView3.setText(endCuttingModel.getEnd().toString());
        holder.textView4.setText(endCuttingModel.getWeight().toString());
    }


    @Override
    public int getItemCount() {
        return endCuttingModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
        ConstraintLayout constraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.sno_value);
            this.textView2 = (TextView) itemView.findViewById(R.id.stime_value);
            this.textView3 = (TextView) itemView.findViewById(R.id.etime_value);
            this.textView4 = (TextView) itemView.findViewById(R.id.weight_value);

        }
    }


}
