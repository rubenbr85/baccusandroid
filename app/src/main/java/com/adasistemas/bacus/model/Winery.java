package com.adasistemas.bacus.model;

import android.os.Build;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruben on 11/10/2016.
 */

public class Winery {

    public static enum WineType{
        RED, WHITE, ROSE, OTHER
    }

    private static  final String winesURL = "http://static.keepcoding.io/baccus/wines.json";

    //Patron singelton
    private static  Winery sIntance = null;
    private List<Wine> mWinesList = null;
    private HashMap<WineType,List<Wine>> mWinesByTYpe =null;

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

    public  static boolean isInstanceAvaliable() {
        return sIntance != null;
    }
    private static Winery downloadWines() throws IOException, JSONException {
        Winery winery = new Winery();
        winery.mWinesList = new LinkedList<Wine>();
        winery.mWinesByTYpe = new HashMap<>();
        //Inicializar la lista de cada tipo de vino
        for (WineType type : WineType.values()){
            winery.mWinesByTYpe.put(type,  new LinkedList<Wine>());
        }


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

                //winery.mWinesList.add(wine);
                if (type.equalsIgnoreCase("tinto")){
                    winery.mWinesByTYpe.get(WineType.RED).add(wine);
                } else if (type.equalsIgnoreCase("blanco")){
                    winery.mWinesByTYpe.get(WineType.WHITE).add(wine);
                } else if (type.equalsIgnoreCase("rosado")){
                    winery.mWinesByTYpe.get(WineType.ROSE).add(wine);
                }else{
                    winery.mWinesByTYpe.get(WineType.OTHER).add(wine);
                }
            }
        }

        for (WineType type : WineType.values()){
            List<Wine> wineList = winery.mWinesByTYpe.get(type);
            winery.mWinesList.addAll(wineList);
        }

        return winery;
    }


    public Wine getWine(int index){
        return mWinesList.get(index);
    }

    public Wine getWine(WineType type, int index){
        return mWinesByTYpe.get(type).get(index);
    }

    public int getWineCount(){
        return mWinesList.size();
    }

    public int getWineCount(WineType type){
        return mWinesByTYpe.get(type).size();
    }

    public  List<Wine> getWineList(){
        return mWinesList;
    }

    public int getAbsolutePosition(WineType type, int relativePosition){
        Wine wine = getWine(type,relativePosition);
        return mWinesList.indexOf(wine);
    }

}
