package com.adasistemas.bacus.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;

/**
 * Created by ruben on 21/09/2016.
 */

public class WebActivity extends AppCompatActivity {
    private static final String STATE_URL= "url";

    private  Wine mWine = null;

    //Vistas
    private WebView mBrowser = null;
    private ProgressBar mLoading= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        //Creamos el modelo
        mWine = new Wine(
                5,
                "Bembibre"
                ,"Tinto"
                ,R.drawable.vendaval
                ,"Dominio de Tares"
                ,"http://www.dominiodetares.com/portfolio/bembibre/"
                ,"Vendiamiado a mano racimo a racimo, fermentado con su propia levadura natural y criado durante 16 meses en barricas de roble francés y americano con 24 meses extra en botellaVino de intenso color granate, nariz de frutos rojos y negros confitados, recuerdos de ciruela pasa y frutos secos tostados. Boca densa, pulida y cálida."
                ,"El Bierzo");
        mWine.addGrape("Mencia");

        //Asocio vista y controlador
        mBrowser = (WebView) findViewById(R.id.browser);
        mLoading = (ProgressBar) findViewById(R.id.loading);

        //Configuro vistas
        mBrowser.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mLoading.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoading.setVisibility(View.GONE);
            }
        });

        mBrowser.getSettings().setJavaScriptEnabled(true);
        mBrowser.getSettings().setBuiltInZoomControls(true);

        //Cargo la pagina web
        if (savedInstanceState == null || !savedInstanceState.containsKey(STATE_URL)) {
            mBrowser.loadUrl(mWine.getCompanyWeb());
        }
        else{
           mBrowser.loadUrl(savedInstanceState.getString(STATE_URL));
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Guardmos en el estado para recuprarlo a la hora de girar..
        super.onSaveInstanceState(outState);

        outState.putString(STATE_URL,mBrowser.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_web, menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_reload){
            mBrowser.reload();

            return  true;
        }

        return  false;
    }
}
