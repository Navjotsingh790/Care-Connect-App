package com.mastercoding.healthcareapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BookingAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton,btnBook,btnBack;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);

        tv=findViewById(R.id.textViewAppTitle);
        ed1=findViewById(R.id.editTextAppFullName);
        ed2=findViewById(R.id.editTextAppAddress);
        ed3=findViewById(R.id.editTextAppContactNumber);
        ed4=findViewById(R.id.editTextAppFees);
        dateButton=findViewById(R.id.buttonAppDatePicker);
        timeButton=findViewById(R.id.buttonAppTimePicker);
        btnBook=findViewById(R.id.buttonAppBookAppointment);
        btnBack=findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("cons Fees:"+fees+"/-");

        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingAppointmentActivity.this,FindDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "Healthcare", null, 1);
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "").toString();

                if(db.CheckAppontmentExists(username, title + "=>" + fullname, address, contact, dateButton.getText().toString(), timeButton.getText().toString()) == 1)
                {
                    Toast.makeText(getApplicationContext(), "Appointment Already booked", Toast.LENGTH_SHORT).show();
                }

               else{
                    db.addOrder(username, title + "=>" + fullname, address, contact, 0, dateButton.getText().toString(), timeButton.getText().toString(), Float.parseFloat(fees), "appointment");
                    Toast.makeText(getApplicationContext(), "your appointment is done successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookingAppointmentActivity.this,HomeActivity.class));
                }

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker,int i,int i1,int i2)
            {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };


        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1)
            {
                timeButton.setText(i+":"+i1);
            }
        };

        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
}