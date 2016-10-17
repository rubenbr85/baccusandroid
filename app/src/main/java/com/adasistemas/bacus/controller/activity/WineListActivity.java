package com.adasistemas.bacus.controller.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adasistemas.bacus.controller.fargment.WineListFragment;

public class WineListActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return new WineListFragment();
    }
}
