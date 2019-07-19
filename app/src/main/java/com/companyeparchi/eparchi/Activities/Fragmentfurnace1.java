package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.companyeparchi.eparchi.Adaptor.InductionAdaptor;
import com.companyeparchi.eparchi.Adaptor.newInductionAdapter;
import com.companyeparchi.eparchi.Models.DinductionModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.companyeparchi.eparchi.Activities.LoginShift.SHARED_PREFS;
import static com.companyeparchi.eparchi.Activities.LoginShift.TOKEN;

public class Fragmentfurnace1 extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.newinductiondashboard_layoout, container, false);
        recyclerView = rootView.getRootView().findViewById(R.id.recyclernewind);
        myapiInduction("1",rootView);
        return rootView;
    }

    public void myapiInduction(String furnace, final View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());

        alertDialogBuilder.setTitle("Getting Reports");

        alertDialogBuilder.setMessage("Getting Reports please wait ...!!").setCancelable(false);

        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginShift.url)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        final String text = sharedPreferences.getString(TOKEN, "");
        Call<DinductionModel> call = jsonPlaceHolder.getinductiondetail(text, furnace,InductionDashboard2.datenow);
        call.enqueue(new Callback<DinductionModel>() {

            @Override
            public void onResponse(Call<DinductionModel> call, final Response<DinductionModel> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(view.getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    return;
                }
                newInductionAdapter mAdapter = new newInductionAdapter(response.body());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(view.getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent intent = new Intent(view.getContext(),FinalInduction.class);
                                InductionDashboard2.dinductionModel = response.body();
                                InductionDashboard2.number = position;
                                startActivity(intent);
                                // do whatever
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

                Toast.makeText(view.getContext(), "Reports Updated", Toast.LENGTH_SHORT).show();
                Log.v("reponsse is ", response.body().getMsg());
                alertDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DinductionModel> call, Throwable t) {
                Toast.makeText(view.getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                Log.v("now", t.getMessage().toString());
                alertDialog.dismiss();
            }
        });
    }
}

