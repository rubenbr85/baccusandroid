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

/**
 * Created by ruben on 10/10/2016.
 */

public class WineyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root= inflater.inflate(R.layout.fragment_winery,container,false);

        Wine bembibre =  new Wine(
                5,
                "Bembibre"
                ,"Tinto"
                ,R.drawable.bembibre
                ,"Dominio de Tares"
                ,"http://www.dominiodetares.com/portfolio/bembibre/"
                ,"Vendiamiado a mano racimo a racimo, fermentado con su propia levadura natural y criado durante 16 meses en barricas de roble francés y americano con 24 meses extra en botellaVino de intenso color granate, nariz de frutos rojos y negros confitados, recuerdos de ciruela pasa y frutos secos tostados. Boca densa, pulida y cálida."
                ,"El Bierzo");
        bembibre.addGrape("Mencia");

        Wine vegabal =  new Wine(
                4,
                "Vengabal"
                ,"Tempranillo"
                ,R.drawable.vendaval
                ,"Dominio de Tares"
                ,"http://www.vegabal.com/es"
                ,"kjaskja jsa sjak sjklaj sklaj slkaj lkj alks jlakj sas."
                ,"Valdepeñas");
        bembibre.addGrape("Tempranillo");

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
