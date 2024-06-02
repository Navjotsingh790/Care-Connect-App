package com.mastercoding.healthcareapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private  String[][] order_details={};

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btn=findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listViewOD);


        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
            }
        });

        Database db=new Database(getApplicationContext(),"Healthcare",null,1);
        SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedpreferences.getString("username","").toString();
        ArrayList dbData=db.getOrderData(username);

        order_details=new String[dbData.size()][];
        for (int i=0;i<order_details.length;i++){
            order_details[i]=new String[5];
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0]=strData[0];
            order_details[i][1]=strData[1];//+" "+strData[3];
            if(strData[7].compareTo("medicine")==0){
                order_details[i][3]="Del:"+strData[4];
            }
            else {
                order_details[i][3]="Del:"+strData[4]+" "+strData[5];
            }
            order_details[i][2]="Rs."+strData[6];
            order_details[i][4]=strData[7];
        }

        list=new ArrayList();
        for (int i=0;i<order_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line 1",order_details[i][0]);
            item.put("line 2",order_details[i][1]);
            item.put("line 3",order_details[i][2]);
            item.put("line 4",order_details[i][3]);
            item.put("line 5",order_details[i][4]);
            list.add(item);

        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);



    }
}