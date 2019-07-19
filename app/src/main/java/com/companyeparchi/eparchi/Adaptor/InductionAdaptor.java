package com.companyeparchi.eparchi.Adaptor;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.companyeparchi.eparchi.Models.InductionModel;
import com.companyeparchi.eparchi.R;

import java.util.ArrayList;

public class InductionAdaptor extends RecyclerView.Adapter<InductionAdaptor.ViewHolder>{

    ArrayList<InductionModel> inductionModels   = new ArrayList<InductionModel>();

        // RecyclerView recyclerView;
        public InductionAdaptor(ArrayList<InductionModel> inductionModels) {
            this.inductionModels = inductionModels;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.inductionreportitembasic, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final InductionModel inductionModel = inductionModels.get(position);
            holder.textView1.setText(inductionModel.getHeat());
            holder.textView2.setText(inductionModel.getOntime());
            Log.v("flag value", String.valueOf(inductionModel.isFlag()));
            if(inductionModel.isFlag())
            {
                holder.constraintLayout.setBackgroundResource(R.drawable.customtablered);
                holder.textView7.setText("red");
            }
            else {
                holder.constraintLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            holder.textView3.setText(inductionModel.getOfftime());
            holder.textView4.setText(inductionModel.getTotaltime());
            holder.textView6.setText(inductionModel.getReason());
            holder.textView8.setText(inductionModel.getPf().toString());
            holder.textView9.setText(inductionModel.getMn().toString());
            holder.textView10.setText(inductionModel.getAluminum().toString());
            holder.textView11.setText(inductionModel.getSilicon().toString());
            holder.textView12.setText(inductionModel.getScrap().toString());
            holder.textView13.setText(inductionModel.getEtime().toString());
            Log.v("yeh message",inductionModel.getEtime().toString());
        }

        @Override
        public int getItemCount() {
            return inductionModels.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13;
            ConstraintLayout constraintLayout;
            public ViewHolder(View itemView) {
                super(itemView);
                this.textView1 = (TextView) itemView.findViewById(R.id.heat);
                this.textView2 = (TextView) itemView.findViewById(R.id.ont);
                this.textView3 = (TextView) itemView.findViewById(R.id.ot);
                this.textView4 = (TextView) itemView.findViewById(R.id.tt);
                this.textView5 = (TextView) itemView.findViewById(R.id.totaltime);
                this.textView6 = (TextView) itemView.findViewById(R.id.reason);
                this.textView7 = itemView.findViewById(R.id.redflag);
                this.constraintLayout = itemView.findViewById(R.id.inductionitemconstraint);
                this.textView8 = itemView.findViewById(R.id.length);
                this.textView9 = itemView.findViewById(R.id.manganese);
                this.textView10 = itemView.findViewById(R.id.aluminium);
                this.textView11 = itemView.findViewById(R.id.silicon);
                this.textView12 = itemView.findViewById(R.id.scrap);
                this.textView13 = itemView.findViewById(R.id.extra_time);
            }
        }


    }
