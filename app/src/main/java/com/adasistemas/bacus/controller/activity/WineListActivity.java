package com.adasistemas.bacus.controller.activity;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.controller.fargment.WineListFragment;
import com.adasistemas.bacus.controller.fargment.WineyFragment;

public class WineListActivity extends AppCompatActivity implements WineListFragment.onWineSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_list);

        //Usar toolbar en vez de ActionBar
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm= getSupportFragmentManager();

        if (findViewById(R.id.list) != null){
            Fragment listFragment= fm.findFragmentById(R.id.list);
            if (listFragment == null){
                listFragment= new WineListFragment();
                fm.beginTransaction()
                        .add(R.id.list,listFragment)
                        .commit();

            }
        }

        if (findViewById(R.id.winery) != null){
            Fragment wineryFragment= fm.findFragmentById(R.id.winery);
            if (wineryFragment == null){
                wineryFragment= new WineyFragment().newInstance(PreferenceManager.getDefaultSharedPreferences(this).getInt(WineyFragment.PREF_LAST_WINE_INDEX,0));
                fm.beginTransaction()
                        .add(R.id.winery,wineryFragment)
                        .commit();

            }
        }
    }

    @Override
    public void onWineSelected(int wineIndex) {
        WineyFragment wineryFragment = (WineyFragment) getSupportFragmentManager().findFragmentById(R.id.winery);

        if (wineryFragment != null){
            wineryFragment.changeWine(wineIndex);
        }else{
            Intent wineryIntent= new Intent(this, WineryActivity.class);
            wineryIntent.putExtra(WineryActivity.EXTRA_WINE_INDEX,wineIndex);
            startActivity(wineryIntent);
        }
    }
}
