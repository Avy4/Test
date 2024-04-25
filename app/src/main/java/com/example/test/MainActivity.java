package com.example.test;


import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity{
    WebView web;
    ImageView image;
    JSONObject obj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        image = findViewById(R.id.imageView);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        //https://api.brawlapi.com/v1/brawlers/16000038
        web.loadUrl("https://api.brawlapi.com/v1/brawlers/16000037");
        Picasso.get().load("https://cdn-old.brawlify.com/brawler-bs/Surge.png")
                .placeholder(R.drawable.loading)
                .into(image);



    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}