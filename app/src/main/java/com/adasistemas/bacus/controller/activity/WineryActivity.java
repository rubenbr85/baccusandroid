package com.adasistemas.bacus.controller.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.adasistemas.bacus.controller.fargment.WineyFragment;


public class WineryActivity extends FragmentContainerActivity {

    public static  final String EXTRA_WINE_INDEX= "com.adasistemas.bacus.controller.activity.WineryActivity.EXTRA_WINE_INDEX";

    @Override
    protected Fragment createFragment() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Flecha atras / home

        return new WineyFragment().newInstance(getIntent().getIntExtra(EXTRA_WINE_INDEX,0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
