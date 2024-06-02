package com.mastercoding.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail,edPassword,edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.editTextAppFullName);
        edPassword=findViewById(R.id.editTextAppContactNumber);
        edEmail=findViewById(R.id.editTextAppAddresss);
        edConfirm=findViewById(R.id.editTextAppFees);
        btn=findViewById(R.id.buttonBookAppointment);
        tv=findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view )
            {
                String username=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm=edConfirm.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0 || confirm.length()==0 || email.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (password.compareTo(confirm)==0){
                        if(isValid(password))
                        {
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters ,having letter,digits and special symbol",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password and confirm password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8)
        {
            return false;
        }
        else
        {
            for (int p=0;p<passwordhere.length();p++)
            {
                if (Character.isLetter(passwordhere.charAt(p)))
                {
                    f1=1;
                }
            }
            for (int r=0;r<passwordhere.length();r++)
            {
                if(Character.isDigit(passwordhere.charAt(r)))
                {
                    f2=1;
                }
            }

            for(int s=0; s<passwordhere.length();s++)
            {
                char c=passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64)
                {
                    f3=1;
                }
            }

            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;

        }
    }
}