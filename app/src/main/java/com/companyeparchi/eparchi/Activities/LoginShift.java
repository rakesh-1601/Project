package com.companyeparchi.eparchi.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.companyeparchi.eparchi.Retrofit.JsonPlaceHolder;
import com.companyeparchi.eparchi.Models.LoginModel;
import com.companyeparchi.eparchi.R;
import com.companyeparchi.eparchi.Retrofit.NullOnEmptyConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginShift extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "token";
    public static final String MYCASE = "mycase";
    EditText username, password;
    String text,text1,text2;

    public static String url = "http://18.221.41.13/";



    //public static String url = "https://dc73f23e.ngrok.io/";
    EditText editText;
    CheckBox showpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shift);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text1 = sharedPreferences.getString(MYCASE,"");
        Log.v("case is NOW",text1+text2);
        showpass = findViewById(R.id.checkBox);
        editText = findViewById(R.id.url);

        text = sharedPreferences.getString(TOKEN,"");
        if(text.equals("")) {
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
        }
        else {
            if (text1.equals("FNDR")) {
                Intent intent = new Intent(LoginShift.this, Dashboard2.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(LoginShift.this, LoginName.class);
                startActivity(intent);
            }
        }
    }

    public void optionpass(View v)
    {
        if(showpass.isChecked())
        {
            password.setTransformationMethod(null);
        }
        else
        {
            password.setTransformationMethod(new PasswordTransformationMethod());
        }
    }
    @Override
    public void onBackPressed() {
        return;
    }
    public void submit(View view) {
        //url = editText.getText().toString();
        if (TextUtils.isEmpty(username.getText().toString())) {
            Toast.makeText(getApplicationContext(),"Enter Username",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }  else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginShift.this);

            alertDialogBuilder.setTitle("Login");

            alertDialogBuilder.setMessage("Logging in wait ...!!").setCancelable(false);

            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final LoginModel log  =  new LoginModel(username.getText().toString(),password.getText().toString());
            JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
            Call<LoginModel> call = jsonPlaceHolder.loginuser(log);
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response)
                {

                    if (!response.isSuccessful()) {
                        Toast.makeText(LoginShift.this, response.message(), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        return;
                    }
                    LoginModel loginModel = response.body();
                    Log.v("response",loginModel.getMsg());
                    if(!loginModel.isSuccess())
                    {
                        Toast.makeText(getApplicationContext(),loginModel.getMsg(),Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(TOKEN, loginModel.getToken());
                        editor.putString(MYCASE,loginModel.getMycase());
                        editor.apply();
                        text1 = sharedPreferences.getString(MYCASE,"");
                        Log.v("case is NOW",text1+text2);
                        if(text1.equals("FNDR"))
                        {
                            Intent intent = new Intent(LoginShift.this, Dashboard2.class);
                            startActivity(intent);
                        }
                        else {
                            //setspinner();
                            Intent intent = new Intent(LoginShift.this, LoginName.class);
                            startActivity(intent);
                        }
                        alertDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    Log.v("error full", t.getMessage());
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });
        }
    }
}
