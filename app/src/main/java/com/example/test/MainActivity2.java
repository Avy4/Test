package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    String start = "160000";
    String toastString = "Please enter a value between 00-81";
    int duration = Toast.LENGTH_SHORT;
    Button button;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.butt);
        edit = findViewById(R.id.edit);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        String s = edit.getText().toString();
        int i = Integer.parseInt(s);
        if(i <= 81) {
            intent.putExtra("key", start + s);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, toastString, duration).show();
        }
    }
}