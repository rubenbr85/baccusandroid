package com.adasistemas.bacus.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.adasistemas.bacus.controller.fargment.WineFragment;


public class WineActivity extends FragmentContainerActivity {
    public  static  String EXTRA_WINE = "com.adasistemas.bacus.controller.activity.WineActivity.EXTRA_WINE";

    @Override
    protected Fragment createFragment() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, getIntent().getSerializableExtra(EXTRA_WINE));

        WineFragment fragment = new WineFragment();
        fragment.setArguments(arguments);

        return fragment;
    }
}
