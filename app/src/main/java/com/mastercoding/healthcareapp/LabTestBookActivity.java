package com.mastercoding.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname=findViewById(R.id.editTextLTBFullName);
        edaddress=findViewById(R.id.editTextLTBAddresss);
        edcontact=findViewById(R.id.editTextLTBContactNumber);
        edpincode=findViewById(R.id.editTextLTBPincode);
        btnBooking=findViewById(R.id.buttonLTBBookAppointment);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedpreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"Healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer .parseInt(edpincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");

                Toast.makeText(getApplicationContext(),"Your Booking is  done successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });

    }
}