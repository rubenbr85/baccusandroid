package com.adasistemas.bacus.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
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

public class WebActivity extends Activity {
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
        mBrowser.loadUrl(mWine.getCompanyWeb());


    }
}
