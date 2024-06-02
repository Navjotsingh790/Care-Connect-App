package com.mastercoding.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1=
            {
                    {"Doctor Name:Lavish Khandalwal","Hospital Address :Delhi","Exp:4yrs","Mobile No.:7428858398","600"},
                    {"Doctor Name:Aman Kumar Panda","Hospital Address :Pune","Exp:2yrs","Mobile No.:9958641863","500"},
                    {"Doctor Name:Navjot Singh","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:7909052197","700"},
                    {"Doctor Name:Onkar Singh","Hospital Address :Punjab","Exp:2yrs","Mobile No.:8851496857","1200"},
                    {"Doctor Name:Tushar Karmakar","Hospital Address :Bangal","Exp:5yrs","Mobile No.:9625662617","6000"},
                    {"Doctor Name:Sunil Chettri","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:7412052197","700"},
                    {"Doctor Name:Neeraj Chopra","Hospital Address :Punjab","Exp:2yrs","Mobile No.:8851496954","1200"},
                    {"Doctor Name:Sachin Tendulkar","Hospital Address :Bangal","Exp:5yrs","Mobile No.:96254992617","6000"},

            };

    private String[][] doctor_details2=
            {
                    {"Doctor Name:Sameer Rawat","Hospital Address :Delhi","Exp:4yrs","Mobile No.:9564155555","600"},
                    {"Doctor Name:Bhagyansh kumar","Hospital Address :Pune","Exp:2yrs","Mobile No.:9984211465","500"},
                    {"Doctor Name:Gurveer Singh","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:9500000029","700"},
                    {"Doctor Name:Abhay Mahato","Hospital Address :Punjab","Exp:2yrs","Mobile No.:7841666611","1200"},
                    {"Doctor Name:Ajay Kumar","Hospital Address :Bangal","Exp:5yrs","Mobile No.:7464625198","6000"},
                    {"Doctor Name:Neymar Junior","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:7802052197","700"},
                    {"Doctor Name:Haaland","Hospital Address :Punjab","Exp:2yrs","Mobile No.:8851487457","1200"},
                    {"Doctor Name:Mbaape","Hospital Address :Bangal","Exp:5yrs","Mobile No.:9625445617","6000"},
                    {"Doctor Name:Leonel Messi","Hospital Address :Delhi","Exp:4yrs","Mobile No.:7423358398","600"},
                    {"Doctor Name:Critiano Ranaldo","Hospital Address :Pune","Exp:2yrs","Mobile No.:9958541863","500"},
                    {"Doctor Name:Neymar Junior","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:7802052197","700"},
                    {"Doctor Name:Haaland","Hospital Address :Punjab","Exp:2yrs","Mobile No.:8851487457","1200"},

            };

    private String[][] doctor_details3=
            {
                    {"Doctor Name:Jethalal ghada","Hospital Address :Delhi","Exp:4yrs","Mobile No.:9564155555","600"},
                    {"Doctor Name:Taarak Mehta","Hospital Address :Pune","Exp:2yrs","Mobile No.:79984211465","500"},
                    {"Doctor Name:Roshan Singh","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:9500000029","700"},
                    {"Doctor Name:Popatlal","Hospital Address :Punjab","Exp:2yrs","Mobile No.:7841666611","1200"},
                    {"Doctor Name:Bhide","Hospital Address :Bangal","Exp:5yrs","Mobile No.:7464625198","6000"},
                    {"Doctor Name:Bhagyansh kumar","Hospital Address :Pune","Exp:2yrs","Mobile No.:9984211465","500"},
                    {"Doctor Name:Gurveer Singh","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:9500000029","700"},
                    {"Doctor Name:Abhay Mahato","Hospital Address :Punjab","Exp:2yrs","Mobile No.:7841666611","1200"},

            };

    private String[][] doctor_details4=
            {
                    {"Doctor Name:Leonel Messi","Hospital Address :Delhi","Exp:4yrs","Mobile No.:7423358398","600"},
                    {"Doctor Name:Critiano Ranaldo","Hospital Address :Pune","Exp:2yrs","Mobile No.:9958541863","500"},
                    {"Doctor Name:Neymar Junior","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:7802052197","700"},
                    {"Doctor Name:Haaland","Hospital Address :Punjab","Exp:2yrs","Mobile No.:8851487457","1200"},
                    {"Doctor Name:Mbaape","Hospital Address :Bangal","Exp:5yrs","Mobile No.:9625445617","6000"},
                    {"Doctor Name:Roshan Singh","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:9500000029","700"},
                    {"Doctor Name:Popatlal","Hospital Address :Punjab","Exp:2yrs","Mobile No.:7841666611","1200"},
                    {"Doctor Name:Bhide","Hospital Address :Bangal","Exp:5yrs","Mobile No.:7464625198","6000"},

            };

    private String[][] doctor_details5=
            {
                    {"Doctor Name:Virat kohli","Hospital Address :Delhi","Exp:4yrs","Mobile No.:7422128398","600"},
                    {"Doctor Name:Rohit Sharma","Hospital Address :Pune","Exp:2yrs","Mobile No.:9989441863","500"},
                    {"Doctor Name:Sunil Chettri","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:7412052197","700"},
                    {"Doctor Name:Neeraj Chopra","Hospital Address :Punjab","Exp:2yrs","Mobile No.:8851496954","1200"},
                    {"Doctor Name:Sachin Tendulkar","Hospital Address :Bangal","Exp:5yrs","Mobile No.:96254992617","6000"},
                    {"Doctor Name:Gurveer Singh","Hospital Address :Jharkhand","Exp:3yrs","Mobile No.:9500000029","700"},
                    {"Doctor Name:Abhay Mahato","Hospital Address :Punjab","Exp:2yrs","Mobile No.:7841666611","1200"},
                    {"Doctor Name:Ajay Kumar","Hospital Address :Bangal","Exp:5yrs","Mobile No.:7464625198","6000"},

            };
    TextView tv;
    Button btn;

    String[][]doctor_details={};
    HashMap<String ,String> item;

    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonDDBack);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;

        else if (title.compareTo("Dietician")==0)
                doctor_details=doctor_details2;

        else if (title.compareTo("Dentist")==0)
                doctor_details=doctor_details3;

        else if (title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;

        else
            doctor_details=doctor_details5;

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));

            }
        });

        list=new ArrayList();
        for (int i=0;i<doctor_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line 1",doctor_details[i][0]);
            item.put("line 2",doctor_details[i][1]);
            item.put("line 3",doctor_details[i][2]);
            item.put("line 4",doctor_details[i][3]);
            item.put("line 5","Cons-Fees: "+doctor_details[i][4]+"/-");
            list.add(item);

        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );

        ListView lst=findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent it=new Intent(DoctorDetailsActivity.this,BookingAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });

    }
}