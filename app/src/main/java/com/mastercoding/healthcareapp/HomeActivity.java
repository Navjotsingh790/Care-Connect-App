package com.mastercoding.healthcareapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences.Editor editor =sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }

        });

        CardView FindDoctor=findViewById(R.id.cardFindDoctor);
        FindDoctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });

        CardView labTest=findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });

        CardView buymedicine=findViewById(R.id.cardBuyMedicine);
        buymedicine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://pharmeasy.in/")));
            }
        });

        CardView orderDetails=findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
            }
        });

        CardView readnews=findViewById(R.id.cardHealthDoctor);
        readnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(i);
                        finish();
                    }
                },2000);
            }
        });


    }
}