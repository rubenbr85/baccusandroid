package com.adasistemas.bacus.controller.activity;


import android.support.v4.app.Fragment;
import com.adasistemas.bacus.controller.fargment.WineyFragment;


public class WineryActivity extends FragmentContainerActivity {

    public static  final String EXTRA_WINE_INDEX= "com.adasistemas.bacus.controller.activity.WineryActivity.EXTRA_WINE_INDEX";

    @Override
    protected Fragment createFragment() {
        return new WineyFragment().newInstance(getIntent().getIntExtra(EXTRA_WINE_INDEX,0));
    }
}
