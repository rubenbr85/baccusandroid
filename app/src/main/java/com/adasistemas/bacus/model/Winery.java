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
            String id = null;
            String  name = null;
            String type = null;
            int photo = 0;
            String companyName = null;
            String companyWeb = null;
            String notes = null;
            String origin = null;
            int rating = 0; //0 to 5
            String picture = null;

            JSONObject jsonWine = wines.getJSONObject(wineIndex);
            if (jsonWine.has("name")){
                id= jsonWine.getString("_id");
                name= jsonWine.getString("name");
                type =  jsonWine.getString("type");
                companyName =  jsonWine.getString("company");
                companyWeb =  jsonWine.getString("company_web");
                notes =  jsonWine.getString("notes");
                rating =  jsonWine.getInt("rating");
                origin = jsonWine.getString("origin");
                picture = jsonWine.getString("picture");

                Wine wine = new Wine(id ,rating,name,type,picture,companyName,companyWeb,notes,origin);
                JSONArray jsonGrapes = jsonWine.getJSONArray("grapes");
                for(int grapeIndex = 0; grapeIndex < jsonGrapes.length(); grapeIndex ++){
                    wine.addGrape(jsonGrapes.getJSONObject(grapeIndex).getString("grape"));
                }

                winery.mWines.add(wine);
            }
        }

        return winery;
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
