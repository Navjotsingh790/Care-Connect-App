package com.mastercoding.healthcareapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername,edPassword;
    Button btn;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edUsername=findViewById(R.id.editTextLoginUsername);
        edPassword=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){


                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    if(db.login(username,password)==1)
                    {
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        editor.putString("username",username);
                        // to save our data with key and value.
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(getApplicationContext(), HomePage.class);
//                startActivity(i);
//                finish();
//            }
//        },2000);
    }
}