package com.adasistemas.bacus.controller.fargment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;

/**
 * Created by ruben on 10/10/2016.
 */

public class WebFragment extends Fragment{
    public static final String ARG_WINE= "com.adasistemas.bacus.controller.fargment.WebFragment.ARG_WINE";
    private static final String STATE_URL= "url";

    private Wine mWine = null;

    //Vistas
    private WebView mBrowser = null;
    private ProgressBar mLoading= null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

       View root = inflater.inflate(R.layout.fragment_web,container,false);

        mWine= (Wine) getArguments().getSerializable(ARG_WINE);

        //Asocio vista y controlador
        mBrowser = (WebView) root.findViewById(R.id.browser);
        mLoading = (ProgressBar) root.findViewById(R.id.loading);

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

        return root;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Guardmos en el estado para recuprarlo a la hora de girar..
        super.onSaveInstanceState(outState);

        outState.putString(STATE_URL,mBrowser.getUrl());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_web, menu);
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
