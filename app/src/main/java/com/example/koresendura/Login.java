package com.example.koresendura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        FrameLayout start =(FrameLayout)findViewById(R.id.button2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputusername = username.getText().toString();
                String inputpassword = password.getText().toString();
                final String inputusername2 = inputusername;
                final String inputpassword2 = inputpassword;

                if(inputusername2.equals("admin")&&inputpassword2.equals("print")){
                    username.setText("");
                    password.setText("");
                    Intent myIntent = new Intent(Login.this, selectsize.class);
                    Login.this.startActivity(myIntent);
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

                }else if(inputusername2.equals("admin")&&inputpassword2.equals("test")){
                    username.setText("");
                    password.setText("");
                    Intent myIntent = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(myIntent);
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Application need username and password to Use",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
