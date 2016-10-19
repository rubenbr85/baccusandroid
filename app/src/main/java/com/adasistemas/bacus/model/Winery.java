package com.adasistemas.bacus.model;

import android.os.Build;
import android.os.StrictMode;

import com.adasistemas.bacus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruben on 11/10/2016.
 */

public class Winery {

    private static  final String winesURL = "http://static.keepcoding.io/baccus/wines.json";

    //Patron singelton
    private static  Winery sIntance = null;
    private List<Wine> mWines = null;

    public  static Winery getIntance(){
        if (sIntance == null){
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    //A partir de version de Android no se permite descargar datos en hilo principal, para forzarlo es asi:
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                sIntance= downloadWines();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return sIntance;
    }

    private static Winery downloadWines() throws IOException, JSONException {
        Winery winery = new Winery();
        winery.mWines = new LinkedList<Wine>();

        URLConnection conn = new URL(winesURL).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line = null;

        while((line = reader.readLine()) != null){
            response.append(line);
        }

        reader.close();

        JSONArray wines = new JSONArray(response.toString());

        for(int wineIndex = 0; wineIndex < wines.length();wineIndex++ ){
             String  name = null;
             String type = null;
             int photo = 0;
             String companyName = null;
             String companyWeb = null;
             String notes = null;
             String origin = null;
             int rating = 0; //0 to 5

            JSONObject jsonWine = wines.getJSONObject(wineIndex);
            if (jsonWine.has("name")){
                name= jsonWine.getString("name");
                type =  jsonWine.getString("type");
                companyName =  jsonWine.getString("company");
                companyWeb =  jsonWine.getString("company_web");
                notes =  jsonWine.getString("notes");
                rating =  jsonWine.getInt("rating");
                origin = jsonWine.getString("origin");

                Wine wine = new Wine(rating,name,type,R.drawable.vendaval,companyName,companyWeb,notes,origin);
                JSONArray jsonGrapes = jsonWine.getJSONArray("grapes");
                for(int grapeIndex = 0; grapeIndex < jsonGrapes.length(); grapeIndex ++){
                    wine.addGrape(jsonGrapes.getJSONObject(grapeIndex).getString("grape"));
                }

                winery.mWines.add(wine);
            }
        }

        return winery;
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
