package com.adasistemas.bacus.controller.activity;


import android.support.v4.app.Fragment;
import com.adasistemas.bacus.controller.fargment.WineyFragment;


public class WineryActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return new WineyFragment();
    }
}
