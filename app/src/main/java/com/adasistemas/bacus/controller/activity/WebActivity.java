package com.adasistemas.bacus.controller.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.adasistemas.bacus.controller.fargment.WebFragment;
import com.adasistemas.bacus.model.Wine;

/**
 * Created by ruben on 21/09/2016.
 */

public class WebActivity extends FragmentContainerActivity {
    public static final String EXTRA_WINE= "com.adasistemas.bacus.controller.activity.WebActivity.EXTRA_WINE";

    @Override
    protected Fragment createFragment() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(WebFragment.ARG_WINE, getIntent().getSerializableExtra(EXTRA_WINE));

        WebFragment fragment = new WebFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
