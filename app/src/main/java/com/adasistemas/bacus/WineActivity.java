package com.adasistemas.bacus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WineActivity extends AppCompatActivity {
    private static  final String TAG = WineActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        Log.v(TAG,"Hola Amundiom estamos en Baccus");
    }
}
