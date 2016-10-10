package com.adasistemas.bacus.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.adasistemas.bacus.R;

/**
 * Created by ruben on 03/10/2016.
 */

public class SettingActivity extends Activity implements View.OnClickListener {
    public static final  String EXTRA_WINE_IMAGE_SCLAE_TYPE = "com.adasistemas.bacus.controller.EXTRA_WINE_IMAGE_SCLAE_TYPE";

    //Vistas
    private RadioGroup mRadioGroup = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mRadioGroup = (RadioGroup) findViewById(R.id.scale_type_radios);

        if (getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCLAE_TYPE).equals(ImageView.ScaleType.FIT_XY)){
            mRadioGroup.check(R.id.fit_radio);
        }else  if (getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCLAE_TYPE).equals(ImageView.ScaleType.FIT_CENTER)) {
            mRadioGroup.check(R.id.center_radio);
        }


        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        Button saveButton = (Button) findViewById(R.id.save_button);

        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cancel_button:
                cancelSettings();
                break;
            case R.id.save_button:
                saveSettings();
                break;
        }

    }

    private void saveSettings(){
    Intent config = new Intent();
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.fit_radio){
            config.putExtra(EXTRA_WINE_IMAGE_SCLAE_TYPE, ImageView.ScaleType.FIT_XY);
        }else  if (mRadioGroup.getCheckedRadioButtonId() == R.id.center_radio){
            config.putExtra(EXTRA_WINE_IMAGE_SCLAE_TYPE, ImageView.ScaleType.FIT_CENTER);
        }

        setResult(RESULT_OK,config);
        finish( );
    }

    private void cancelSettings(){
        setResult(RESULT_CANCELED);
        finish();
    }
}
