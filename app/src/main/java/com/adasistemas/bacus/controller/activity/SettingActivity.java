package com.adasistemas.bacus.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.controller.fargment.SettingFragment;

/**
 * Created by ruben on 03/10/2016.
 */

public class SettingActivity extends FragmentContainerActivity {
    public static final  String EXTRA_WINE_IMAGE_SCLAE_TYPE = "com.adasistemas.bacus.controller.EXTRA_WINE_IMAGE_SCLAE_TYPE";

    @Override
    protected Fragment createFragment() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(SettingFragment.ARG_WINE_IMAGE_SCLAE_TYPE,getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCLAE_TYPE));
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
