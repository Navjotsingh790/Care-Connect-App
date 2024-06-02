package com.mastercoding.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][]packages= {
                    {"package 1:Full Body Checkup","","","","999"},
                    {"package 2:Blood Glucose Fasting","","","","299"},
                    {"package 3:COVID-19 Antibody-IgG","","","","899"},
                    {"package 4:Thyroid Check","","","","499"},
                    {"package 5:Immunity Check","","","","699"},
                    {"package 6:Liver Function Test ","","","","1299"},
                    {"package 7:Kidney Function Test ","","","","899"},
                    {"package 8:Thyroid Stimulating Hormone","","","","500"},
                    {"package 9:ECG","","","","499"},
                    {"package 10:Echocardiography","","","","1500"}
    };

    private String[] package_details= {
                    "Blood Glucose Fasting\n" +
                            "Complete Hemogram\n" +
                            "HbA1c\n" +
                            "Iron Studies\n" +
                            "Kidney Function Test\n"+
                            "LDH Lactate Dehydrogenase,Serum\n" +
                            "Lipid Profile\n"+
                            "liver Function Test",
                    "Blood Glucose Fasting",
                    "COVID -19 Antibody -IgG",
                    "Thyroid Profile-Total(T3,T4 & TSH Ultra-sensitive)",
                    "Complete Hemogram\n"+
                            "CRP (C Reactive Protein) Quantative,Serum\n"+
                            "Iron Studies\n" +
                            "Kidney Function Test\n"+
                            "Vitamin D Total-25 Hydroxy\n"+
                            "Liver Function Test\n"+
                            "Lipid Profile",
                    "various enzymes in the blood that indicate liver health.\n"+
                            "various protiens test in the blood",
                    "urea\n"+"creatinine",
                    "electrical activity of the heart\n"+"arrhythmias\n"+"other heart conditions",
                    "lungs, heart, and bones\n"+"create images of the chest",
                    "Measures the level of vitamin D in the blood\n"+"vitamin D deficiency\n"+"bone health",
                    " level of vitamin B12 in the blood\n"+"B12 deficiency\n"+"anemia"
    };

    HashMap<String ,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart=findViewById(R.id.buttonLTAddToCart);
        btnBack=findViewById(R.id.buttonLTBack);
        listView=findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line 1",packages[i][0]);
            item.put("line 2",packages[i][1]);
            item.put("line 3",packages[i][2]);
            item.put("line 4",packages[i][3]);
            item.put("line 5","Cons-Fees: "+packages[i][4]+"/-");
            list.add(item);

        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });
    }
}