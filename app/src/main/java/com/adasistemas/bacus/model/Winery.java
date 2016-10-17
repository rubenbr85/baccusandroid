package com.adasistemas.bacus.model;

import com.adasistemas.bacus.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ruben on 11/10/2016.
 */

public class Winery {

    //Patron singelton
    private static  Winery sIntance = null;
    private List<Wine> mWines = null;

    public  static Winery getIntance(){
        if (sIntance == null){
            sIntance= new Winery();
        }

        return sIntance;
    }

    public Winery(){
        Wine bembibre =  new Wine(
                5,
                "Bembibre"
                ,"Tinto"
                , R.drawable.bembibre
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

        Wine zarate =  new Wine(
                4,
                "Zárate"
                ,"Blanco"
                , R.drawable.zarate
                ,"Miguel Calatayud"
                ,"http://bodegas-zarate.com/productos/vinos/albarino-zarate/"
                ,"El albariño Zarate es un vino blanco monovarietal que pertenece a la Denominación de Origen Rías Baixas. Considerado por la crítica especializada como uno de los grandes vinos blancos del mundo, el albariño ya es todo un mito."
                ,"Rías bajas");
        bembibre.addGrape("Albariño");

        Wine champagne =  new Wine(
                5,
                "Champagne"
                ,"Otros"
                , R.drawable.champagne
                ,"Champagne Taittinge"
                ,"http://bodegas-zarate.com/productos/vinos/albarino-zarate/"
                ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc purus. Curabitur eu velit mauris. Curabitur magna nisi, ullamcorper ac bibendum ac, laoreet et justo. Praesent vitae tortor quis diam luctus condimentum. Suspendisse potenti. In magna elit, interdum sit amet facilisis dictum, bibendum nec libero. Maecenas pellentesque posuere vehicula. Vivamus eget nisl urna, quis egestas sem. Vivamus at venenatis quam. Sed eu nulla a orci fringilla pulvinar ut eu diam. Morbi nibh nibh, bibendum at laoreet egestas, scelerisque et nisi. Donec ligula quam, semper nec bibendum in, semper eget dolor. In hac habitasse platea dictumst. Maecenas adipiscing semper rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;"
                ,"Comtes de Champagne");
        bembibre.addGrape("Albariño");

        //Añadirmos los vinos al array
        mWines = Arrays.asList(new Wine[] {bembibre, vegabal, zarate, champagne});
    }


    public Wine getWine(int index){
        return mWines.get(index);
    }

    public int getWineCount(){
        return mWines.size();
    }

    public  List<Wine> getWineList(){
        return mWines;
    }
}
