package com.adasistemas.bacus.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.model.Wine;

public class WineActivity extends AppCompatActivity {
    public  static  String EXTRA_WINE = "com.adasistemas.bacus.controller.WineActivity.EXTRA_WINE";

    private static  final String TAG = WineActivity.class.getSimpleName();
    private static  final int SETTING_REQUEST = 1;
    private static  final String STATE_IMAGE_SCALE_TYPE = "com.adasistemas.bacus.controller.WineActivity.STATE_IMAGE_SCALE_TYPE";

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
    private ImageButton mGoToWebButton = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        //Recogemos el modelo
        mWine = (Wine) getIntent().getSerializableExtra(EXTRA_WINE);

        //Accedemos a las vistas
        mWineImage = (ImageView) findViewById(R.id.wine_image);
        mWineNameText = (TextView) findViewById(R.id.wine_name);
        mWineTypeText = (TextView) findViewById(R.id.wine_type);
        mWineOriginText = (TextView) findViewById(R.id.wine_origin);
        mWineRatingBar = (RatingBar) findViewById(R.id.wine_rating);
        mWineCompanyText = (TextView) findViewById(R.id.wine_company);
        mWineNotesText = (TextView) findViewById(R.id.wine_notes);
        mWineGrapesContainer = (ViewGroup) findViewById(R.id.grapes_container);
        mGoToWebButton= (ImageButton) findViewById(R.id.go_to_web_button);

        //Damos valor a las vistas con el modelo
        mWineImage.setImageResource(mWine.getPhoto());
        mWineNameText.setText(mWine.getName());
        mWineTypeText.setText(mWine.getType());
        mWineOriginText.setText(mWine.getOrigin());
        mWineRatingBar.setRating(mWine.getRating());
        mWineCompanyText.setText(mWine.getCompanyName());
        mWineNotesText.setText(mWine.getNotes());

        //Actualizamos la lista de uvas
        for (int i = 0; i < mWine.getGrapeCount(); i++){
            TextView grapeText = new TextView(this);
            grapeText.setText(mWine.getGrape(i));
            grapeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            mWineGrapesContainer.addView(grapeText);
        }

        //Configuramos botonoes
        mGoToWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(WineActivity.this,WebActivity.class);

                webIntent.putExtra(WebActivity.EXTRA_WINE,mWine);

                startActivity(webIntent);
            }
        });


        //Configuramos como se ve la imagen

        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_IMAGE_SCALE_TYPE)){
            mWineImage.setScaleType((ImageView.ScaleType) savedInstanceState.getSerializable(STATE_IMAGE_SCALE_TYPE) );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wine, menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.action_settings){
            Intent settingsIntent = new Intent(this, SettingActivity.class);
            settingsIntent.putExtra(SettingActivity.EXTRA_WINE_IMAGE_SCLAE_TYPE,mWineImage.getScaleType());
            startActivityForResult(settingsIntent,SETTING_REQUEST);

            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_REQUEST && resultCode == RESULT_OK){
            ImageView.ScaleType scaleType = (ImageView.ScaleType) data.getSerializableExtra(SettingActivity.EXTRA_WINE_IMAGE_SCLAE_TYPE);
            mWineImage.setScaleType(scaleType);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(STATE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());
    }
}
