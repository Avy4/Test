package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    TextView sP1, sP2, g1, g2, rarity, archetype;
    String sP1desc, sP2desc, g1desc, g2desc, color, rare, arch;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sP1 = findViewById(R.id.sPDesc1);
        sP2 = findViewById(R.id.sPDesc2);
        g1 = findViewById(R.id.gadgetDesc1);
        g2 = findViewById(R.id.gadgetDesc2);
        rarity = findViewById(R.id.rarity);
        archetype = findViewById(R.id.archetype);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();

        sP1desc = intent.getStringExtra("sP1");
        sP2desc = intent.getStringExtra("sP2");
        g1desc = intent.getStringExtra("g1");
        g2desc = intent.getStringExtra("g2");
        color = intent.getStringExtra("color");
        rare = intent.getStringExtra("rarity");
        arch = intent.getStringExtra("class");

        sP1.setText(sP1desc);
        sP2.setText(sP2desc);
        g1.setText(g1desc);
        g2.setText(g2desc);
        rarity.setText(rare);
        archetype.setText(arch);
        rarity.setTextColor(Color.parseColor(color));

        backButton.setOnClickListener(this::onClick);

    }

    public void onClick(View v) {
            Intent back = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(back);
    }

}