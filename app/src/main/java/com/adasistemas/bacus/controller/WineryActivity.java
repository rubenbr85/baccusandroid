package com.adasistemas.bacus.controller;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;

public class WineryActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winery);

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

        //Creamos la primera pestaña
        Intent intent = new Intent(this, WineActivity.class);
        intent.putExtra(WineActivity.EXTRA_WINE,bembibre);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec = tabHost
                    .newTabSpec(bembibre.getName())
                    .setIndicator(bembibre.getName())
                    .setContent(intent);

        tabHost.addTab(spec);

        //Creamos la segunda

         intent = new Intent(this, WineActivity.class);
        intent.putExtra(WineActivity.EXTRA_WINE,vegabal);

         tabHost = getTabHost();
         spec = tabHost
                .newTabSpec(vegabal.getName())
                .setIndicator(vegabal.getName())
                .setContent(intent);

        tabHost.addTab(spec);


    }
}
