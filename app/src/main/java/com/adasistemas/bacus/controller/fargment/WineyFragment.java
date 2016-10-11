package com.adasistemas.bacus.controller.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.controller.adapter.WineyPagerAdapter;

/**
 * Created by ruben on 10/10/2016.
 */

public class WineyFragment extends Fragment {
    private ViewPager mPager = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root= inflater.inflate(R.layout.fragment_winery,container,false);

        //Rellenamos el viewPager
        mPager = (ViewPager) root.findViewById(R.id.pager);
        mPager.setAdapter(new WineyPagerAdapter(getFragmentManager()));

        return root;
    }


}
