package com.adasistemas.bacus.controller.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adasistemas.bacus.controller.fargment.WineFragment;
import com.adasistemas.bacus.controller.fargment.WineyFragment;
import com.adasistemas.bacus.model.Winery;

/**
 * Created by ruben on 11/10/2016.
 */

public class WineyPagerAdapter  extends FragmentPagerAdapter{

    private Winery mWinery= null;

    public WineyPagerAdapter(FragmentManager fm) {
        super(fm);
        mWinery= Winery.getIntance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  mWinery.getWine(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        WineFragment fragment = new WineFragment();
        Bundle arguments = new Bundle();

        arguments.putSerializable(WineFragment.ARG_WINE, mWinery.getWine(position));
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public int getCount() {
        return mWinery.getWineCount();
    }
}
