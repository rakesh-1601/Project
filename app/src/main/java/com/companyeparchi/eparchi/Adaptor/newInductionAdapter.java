package com.companyeparchi.eparchi.Adaptor;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Activities.InductionDashboard2;
import com.companyeparchi.eparchi.Models.DinductionModel;
import com.companyeparchi.eparchi.R;

public class newInductionAdapter extends RecyclerView.Adapter<newInductionAdapter.ViewHolder>{

    DinductionModel dinductionModel;
    public newInductionAdapter(DinductionModel dinductionModel) {
        this.dinductionModel = dinductionModel;
    }
    @Override
    public newInductionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.inductionitems, parent, false);
        newInductionAdapter.ViewHolder viewHolder = new newInductionAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(newInductionAdapter.ViewHolder holder, int position) {
        holder.textView1.setText((position+1)+")  Report Heat No - "+dinductionModel.getData().get(position).getHeat() );
        if(dinductionModel.getData().get(position).isFlag())
        {
            holder.constraintLayout.setBackgroundResource(R.drawable.customtablered);
        }else {
            holder.constraintLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return dinductionModel.getData().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        ConstraintLayout constraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.reportno);
            this.constraintLayout = itemView.findViewById(R.id.layoutbackground);
        }
    }
}
