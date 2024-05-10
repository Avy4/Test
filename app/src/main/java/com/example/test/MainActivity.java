package com.example.test;


import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity{
    WebView web;
    ImageView image;
    TextView text;

    String api;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        image = findViewById(R.id.imageView);
        text = findViewById(R.id.textView);
        //api = "https://api.brawlapi.com/v1/brawlers/16000037";
        api = "https://reqres.in";
        //api = "https://brawlapi.com";
        img(api);

        Retrofit retrofit = retro(api);
        RequestUser requestUser = retrofit.create(RequestUser.class);

        //uid is the brawler id

        requestUser.getFirst_name("3").enqueue(new retrofit2.Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                text.setText(response.body().data.first_name);
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable throwable) {
                text.setText(throwable.getMessage());
            }
        });
    }

   /* interface RequestUser{
        @GET("/v1/brawlers/{uid}")
        Call<UserData> getName(@Path("uid") String uid);
    }
    */

    interface RequestUser{
        @GET("/api/users/{uid}")
        Call<UserData> getFirst_name(@Path("uid") String uid);
    }



    public Retrofit retro(String api){

       // Gson gson = new GsonBuilder()
       //         .setLenient().create();

        Retrofit metro = new Retrofit.Builder()
        .baseUrl(api)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
        return metro;
    }

    public void img(String api) {
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        //https://api.brawlapi.com/v1/brawlers/16000038
        web.loadUrl(api);
        Picasso.get().load("https://cdn-old.brawlify.com/brawler-bs/Surge.png")
                .placeholder(R.drawable.loading)
                .into(image);
    }

}

 class Callback extends WebViewClient {
    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return false;
    }
}