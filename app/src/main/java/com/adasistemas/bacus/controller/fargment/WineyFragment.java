package com.adasistemas.bacus.controller.fargment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    public static final String ARG_WINE_INDEX= "com.adasistemas.bacus.controller.fargment.WineyFragment.ARG_WINE_INDEX";
    public static final String PREF_LAST_WINE_INDEX = "lastWine";

    private ViewPager mPager = null;
    private ActionBar mActionBar = null;
    private Winery mWinery = null;

    public  static  WineyFragment newInstance(int wineIndex){
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_WINE_INDEX,wineIndex);
        WineyFragment fragment = new WineyFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

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
        if (mPager != null){
            if (item.getItemId() == R.id.menu_next && mPager.getCurrentItem() < mWinery.getWineCount() - 1){
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return  true;
            }else if(item.getItemId() == R.id.menu_prev && mPager.getCurrentItem() > 0){
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return  true;
            }
            else{
                return super.onOptionsItemSelected(item);
            }
        }else{
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuNext = menu.findItem(R.id.menu_next);
        MenuItem menuPrec = menu.findItem(R.id.menu_prev);
        if (mPager != null){
            menuNext.setEnabled(mPager.getCurrentItem() < mWinery.getWineCount() - 1);
            menuPrec.setEnabled(mPager.getCurrentItem() > 0);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View root= inflater.inflate(R.layout.fragment_winery,container,false);



        AsyncTask<Void, Void, Winery> wineryDownloader = new AsyncTask<Void, Void, Winery>() {

            @Override
            protected Winery doInBackground(Void... params) {
                return Winery.getIntance();
            }

            @Override
            protected void onPostExecute(Winery winery) {
                mWinery=winery;

                mActionBar = (ActionBar)((AppCompatActivity) getActivity()).getSupportActionBar();

                //Rellenamos el viewPager
                mPager = (ViewPager) root.findViewById(R.id.pager);
                mPager.setAdapter(new WineyPagerAdapter(getFragmentManager()));

                mPager.setOnPageChangeListener(WineyFragment.this);

                int initialWineIndex = getArguments().getInt(ARG_WINE_INDEX);

                mPager.setCurrentItem(initialWineIndex);
                updateActionBar(initialWineIndex);
            }

        };

        wineryDownloader.execute();
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

        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .edit()
                .putInt(PREF_LAST_WINE_INDEX, position)
                .commit();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void changeWine(int wineIndex){
        mPager.setCurrentItem(wineIndex);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); //Corregir el reesabler el giro la posicion del vino
        if (mPager != null){
            getArguments().putInt(ARG_WINE_INDEX, mPager.getCurrentItem());
        }

    }
}
