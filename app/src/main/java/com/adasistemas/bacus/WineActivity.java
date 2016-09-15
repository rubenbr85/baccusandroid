package com.adasistemas.bacus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class WineActivity extends AppCompatActivity {
    private static  final String TAG = WineActivity.class.getSimpleName();

    private ImageView mWineImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        //Recoger la vista con el ID imageView
        mWineImage = (ImageView) findViewById(R.id.imageView);

        //Asignar a la vista la imagen de recursos son ese nombre
        mWineImage.setImageResource(R.drawable.vendaval);
    }
}
