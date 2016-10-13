package com.adasistemas.bacus.controller.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.controller.adapter.WineyPagerAdapter;
import com.adasistemas.bacus.model.Winery;

/**
 * Created by ruben on 10/10/2016.
 */

public class WineyFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager mPager = null;
    private ActionBar mActionBar = null;
    private Winery mWinery = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Indicar que hay opciones de menu
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_winery,menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue=  super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_next && mPager.getCurrentItem() < mWinery.getWineCount() - 1){
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            return  true;
        }else if(item.getItemId() == R.id.menu_prev && mPager.getCurrentItem() > 0){
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            return  true;
        }
        else{
            return superValue;
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuNext = menu.findItem(R.id.menu_next);
        MenuItem menuPrec = menu.findItem(R.id.menu_prev);

        menuNext.setEnabled(mPager.getCurrentItem() < mWinery.getWineCount() - 1);
        menuPrec.setEnabled(mPager.getCurrentItem() > 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root= inflater.inflate(R.layout.fragment_winery,container,false);

        //Rellenamos el viewPager
        mPager = (ViewPager) root.findViewById(R.id.pager);
        mPager.setAdapter(new WineyPagerAdapter(getFragmentManager()));

        mWinery= Winery.getIntance();

        mActionBar = (ActionBar)((AppCompatActivity) getActivity()).getSupportActionBar();

        mPager.setOnPageChangeListener(this);

        updateActionBar(0);

        return root;
    }


    private void updateActionBar(int index){
        mActionBar.setTitle(mWinery.getWine(index).getName());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateActionBar(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
