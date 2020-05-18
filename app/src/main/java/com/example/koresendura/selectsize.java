package com.example.koresendura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class selectsize extends AppCompatActivity {

    Button l_18x19,l_100x20,l_75x50,l_75x50box,l_100x150box,gaurav,fragile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectsize);
        l_18x19=(Button) findViewById(R.id.L_18x19);
        l_100x20=(Button)findViewById(R.id.L_100x20);
        l_75x50=(Button)findViewById(R.id.L_75x50);
        l_75x50box=(Button)findViewById(R.id.l_75x50box);
        l_100x150box=(Button)findViewById(R.id.l_100x150);
        gaurav=(Button)findViewById(R.id.gauravbtn);
        fragile=(Button)findViewById((R.id.fragilebtn));


        l_18x19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, l_18x19.class);
                selectsize.this.startActivity(myIntent);
            }
        });


        l_100x20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, Printsticker.class);
                myIntent.putExtra("labeltype", "100x20");
                selectsize.this.startActivity(myIntent);
            }
        });


        l_75x50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, Printsticker.class);
                myIntent.putExtra("labeltype", "75x50");
                selectsize.this.startActivity(myIntent);

            }
        });


        l_75x50box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, Box75x50.class);
                myIntent.putExtra("labeltype", "75x50box");
                selectsize.this.startActivity(myIntent);

            }
        });

        l_100x150box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, Box100_150.class);
                myIntent.putExtra("labeltype", "100x150box");
                selectsize.this.startActivity(myIntent);

            }
        });

        gaurav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, Gaurav.class);
                myIntent.putExtra("labeltype", "Gaurav");
                selectsize.this.startActivity(myIntent);

            }
        });

        fragile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(selectsize.this, Fragile.class);
                myIntent.putExtra("labeltype", "Fragile");
                selectsize.this.startActivity(myIntent);

            }
        });
    }
}
