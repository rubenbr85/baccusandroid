package com.adasistemas.bacus.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;

public class WineActivity extends AppCompatActivity {
    private static  final String TAG = WineActivity.class.getSimpleName();

    private ImageView mWineImage = null;
    private Wine mWine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        mWine = new Wine(5,"Vegaval","Tinto",R.drawable.vendaval,"","","","Valdepeñas");

        //Recoger la vista con el ID imageView
        mWineImage = (ImageView) findViewById(R.id.wine_image);
    }

    public void changeImage(View view){
        //Asignar a la vista la imagen de recursos son ese nombre
        mWineImage.setImageResource(mWine.getPhoto());
    }
}
