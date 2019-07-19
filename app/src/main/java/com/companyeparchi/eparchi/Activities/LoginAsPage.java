package com.companyeparchi.eparchi.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.companyeparchi.eparchi.R;

public class LoginAsPage extends AppCompatActivity {
    ListView listView;
    String[] Loginmembers = {"Induction Furnace supervisor","Induction Furnace (Electrician)","Billet reporting supervisor","Quality inspection supervisor","CCM Reading supervisor","End cutting supervisor","Maintenance checklist updates","FOUNDER"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_as_page);
        listView = findViewById(R.id.loginmembers);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1,Loginmembers);
        listView.setAdapter(arrayAdapter);
    }

    public void ShiftOpen(View view)
    {
        Intent intent = new Intent(LoginAsPage.this, LoginShift.class);
        startActivity(intent);
    }
}
