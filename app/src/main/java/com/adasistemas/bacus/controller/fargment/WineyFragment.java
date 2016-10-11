package com.adasistemas.bacus.controller.fargment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;
import com.adasistemas.bacus.model.Winery;

/**
 * Created by ruben on 10/10/2016.
 */

public class WineyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root= inflater.inflate(R.layout.fragment_winery,container,false);

        Winery winery= Winery.getIntance();

        Wine bembibre =  winery.getWine(0);

        Wine vegabal =  winery.getWine(1);

        FragmentTabHost tabHost = (FragmentTabHost) root.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(),getActivity().getSupportFragmentManager(),android.R.id.tabcontent);

        //Añadimos la primera pestaña
        Bundle arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE,bembibre);

        tabHost.addTab(tabHost.newTabSpec(bembibre.getName())
                    .setIndicator(bembibre.getName()), WineFragment.class, arguments);

        //Añadimos la segunda pestaña
         arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE,vegabal);

        tabHost.addTab(tabHost.newTabSpec(vegabal.getName())
                .setIndicator(vegabal.getName()), WineFragment.class, arguments);

        return root;
    }


}
