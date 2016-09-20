package com.adasistemas.bacus.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;

public class WineActivity extends AppCompatActivity {
    private static  final String TAG = WineActivity.class.getSimpleName();
    //Modelo
    private Wine mWine = null;

    //Vistas
    private ImageView mWineImage = null;
    private TextView mWineNameText = null;
    private TextView mWineTypeText = null;
    private TextView mWineOriginText = null;
    private RatingBar mWineRatingBar = null;
    private TextView mWineCompanyText = null;
    private TextView mWineNotesText = null;
    private ViewGroup mWineGrapesContainer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        //Creamos el modelo
        mWine = new Wine(
                        5,
                        "Bembibre"
                        ,"Tinto"
                        ,R.drawable.vendaval
                        ,"Dominio de Tares"
                        ,"http://www.dominiodetares.com/portfolio/bembibre/"
                        ,"Vendiamiado a mano racimo a racimo, fermentado con su propia levadura natural y criado durante 16 meses en barricas de roble francés y americano con 24 meses extra en botellaVino de intenso color granate, nariz de frutos rojos y negros confitados, recuerdos de ciruela pasa y frutos secos tostados. Boca densa, pulida y cálida."
                        ,"El Bierzo");

        //Accedemos a las vistas
        mWineImage = (ImageView) findViewById(R.id.wine_image);
        mWineNameText = (TextView) findViewById(R.id.wine_name);
        mWineTypeText = (TextView) findViewById(R.id.wine_type);
        mWineOriginText = (TextView) findViewById(R.id.wine_origin);
        mWineRatingBar = (RatingBar) findViewById(R.id.wine_rating);
        mWineCompanyText = (TextView) findViewById(R.id.wine_company);
        mWineNotesText = (TextView) findViewById(R.id.wine_notes);
        mWineGrapesContainer = (ViewGroup) findViewById(R.id.grapes_container);

        //Damos valor a las vistas con el modelo
        mWineImage.setImageResource(mWine.getPhoto());
        mWineNameText.setText(mWine.getName());
        mWineTypeText.setText(mWine.getType());
        mWineOriginText.setText(mWine.getOrigin());
        mWineRatingBar.setRating(mWine.getRating());
        mWineCompanyText.setText(mWine.getCompanyName());
        mWineNotesText.setText(mWine.getNotes());


    }

}
