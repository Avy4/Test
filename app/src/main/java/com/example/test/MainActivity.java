package com.example.test;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity{



    ImageView brawlerImg, gadgetOnePic, gadgetTwoPic, starPowerOnePic, starPowerTwoPic;
    TextView brawlerName, gadgetOneName, gadgetTwoName, starPowerOneName, starPowerTwoName, description;

    Button backButton, infoButton;
    String brawlerID = "";
    String api = "https://api.brawlapi.com";
    List<String> keys= new ArrayList<String>();
    List<String> attrbutes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImgViews
        brawlerImg = findViewById(R.id.brawlerPicture);
        gadgetOnePic = findViewById(R.id.gadgetOne);
        gadgetTwoPic = findViewById(R.id.gadgetTwo);
        starPowerOnePic = findViewById(R.id.starPowerOne);
        starPowerTwoPic = findViewById(R.id.starPowerTwo);

        //TextViews
        brawlerName = findViewById(R.id.brawlerName);
        gadgetOneName = findViewById(R.id.gadgetOneName);
        gadgetTwoName = findViewById(R.id.gadgetTwoName);
        starPowerOneName = findViewById(R.id.starPowerOneName);
        starPowerTwoName = findViewById(R.id.starPowerTwoName);
        description = findViewById(R.id.description);

        backButton = findViewById(R.id.back);
        infoButton = findViewById(R.id.info);

        keys.add("g1");
        keys.add("g2");
        keys.add("sP1");
        keys.add("sP2");
        keys.add("color");
        keys.add("rarity");
        keys.add("class");


        Intent intent = getIntent();
        brawlerID = intent.getStringExtra("key");

        Retrofit retrofit = Builder(api);

        requestBrawler brawler = retrofit.create(requestBrawler.class);
        backButton.setOnClickListener(this::onClick);
        infoButton.setOnClickListener(this::onClick);
        Intent desc = new Intent(MainActivity.this, MainActivity3.class);


        brawler.getBrawler(brawlerID).enqueue(new retrofit2.Callback<BrawlerData>() {
            @Override
            public void onResponse(Call<BrawlerData> call, Response<BrawlerData> response) {
                if(response.isSuccessful()){

                    //Brawler
                    if(response.body().getImageUrl3() == null){
                        Picasso.get().load(response.body().getImageUrl()).into(brawlerImg);
                    }
                    else{
                        Picasso.get().load(response.body().getImageUrl3()).into(brawlerImg);
                    }

                    brawlerName.setText(response.body().getName());
                    description.setText(response.body().getDescription());

                    //gadgets
                    gadgetOneName.setText(response.body().gadgets[0].getName());
                    gadgetTwoName.setText(response.body().gadgets[1].getName());
                    Picasso.get().load(response.body().gadgets[0].getImageUrl()).into(gadgetOnePic);
                    Picasso.get().load(response.body().gadgets[1].getImageUrl()).into(gadgetTwoPic);

                    //starpowers
                    starPowerOneName.setText(response.body().starPowers[0].getName());
                    starPowerTwoName.setText(response.body().starPowers[1].getName());
                    Picasso.get().load(response.body().starPowers[0].getImageUrl()).into(starPowerOnePic);
                    Picasso.get().load(response.body().starPowers[1].getImageUrl()).into(starPowerTwoPic);

                    //descriptions
                    attrbutes.add(response.body().gadgets[0].getDescription());
                    attrbutes.add(response.body().gadgets[1].getDescription());
                    attrbutes.add(response.body().starPowers[0].getDescription());
                    attrbutes.add(response.body().starPowers[1].getDescription());
                    attrbutes.add(response.body().rarity.getColor());
                    attrbutes.add(response.body().rarity.getName());
                    attrbutes.add(response.body().brawlerClass.getName());



                }
            }

            @Override
            public void onFailure(Call<BrawlerData> call, Throwable throwable) {

            }
        });

    }


    public void onClick(View v) {
        if (v == backButton){
            Intent back = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(back);
        }
        else{
            Intent info = new Intent(MainActivity.this, MainActivity3.class);
            for(int i = 0; i < 7; i++){
                info.putExtra(keys.get(i), attrbutes.get(i));
            }
            startActivity(info);
        }
    }


    interface requestBrawler{
        @Headers({"Accept: application/json"})
        @GET("/v1/brawlers/{brawlerID}")
        Call<BrawlerData> getBrawler(@Path("brawlerID") String brawlerID);
    }

    public Retrofit Builder(String api){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retroBoomin = new Retrofit.Builder()
        .baseUrl(api)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
        return retroBoomin;
    }





}

 class  Callback extends WebViewClient {
    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return false;
    }
}