package com.adasistemas.bacus.controller.fargment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
import com.adasistemas.bacus.controller.activity.SettingActivity;
import com.adasistemas.bacus.controller.activity.WebActivity;
import com.adasistemas.bacus.controller.activity.WineActivity;
import com.adasistemas.bacus.model.Wine;

/**
 * Created by ruben on 10/10/2016.
 */

public class WineFragment extends Fragment {
    public  static  String ARG_WINE = "com.adasistemas.bacus.controller.fargment.WineActivity.ARG_WINE";

    private static  final String TAG = WineFragment.class.getSimpleName();
    private static  final int SETTING_REQUEST = 1;
    private static  final String STATE_IMAGE_SCALE_TYPE = "com.adasistemas.bacus.controller.fargment.WineFragment.STATE_IMAGE_SCALE_TYPE";

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_wine,container,false);

        //Recogemos el modelo
        mWine = (Wine) getArguments().getSerializable(ARG_WINE);

        //Accedemos a las vistas
        mWineImage = (ImageView) root.findViewById(R.id.wine_image);
        mWineNameText = (TextView) root.findViewById(R.id.wine_name);
        mWineTypeText = (TextView) root.findViewById(R.id.wine_type);
        mWineOriginText = (TextView) root.findViewById(R.id.wine_origin);
        mWineRatingBar = (RatingBar) root.findViewById(R.id.wine_rating);
        mWineCompanyText = (TextView) root.findViewById(R.id.wine_company);
        mWineNotesText = (TextView) root.findViewById(R.id.wine_notes);
        mWineGrapesContainer = (ViewGroup) root.findViewById(R.id.grapes_container);
        mGoToWebButton= (ImageButton) root.findViewById(R.id.go_to_web_button);

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
            TextView grapeText = new TextView(getActivity());
            grapeText.setText(mWine.getGrape(i));
            grapeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            mWineGrapesContainer.addView(grapeText);
        }

        //Configuramos botonoes
        mGoToWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(getActivity(),WebActivity.class);

                webIntent.putExtra(WebActivity.EXTRA_WINE,mWine);

                startActivity(webIntent);
            }
        });


        //Configuramos como se ve la imagen

        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_IMAGE_SCALE_TYPE)){
            mWineImage.setScaleType((ImageView.ScaleType) savedInstanceState.getSerializable(STATE_IMAGE_SCALE_TYPE) );
        }
        return root;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_wine, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.action_settings){
            SettingFragment settingFragment = new SettingFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable(SettingFragment.ARG_WINE_IMAGE_SCLAE_TYPE, mWineImage.getScaleType());
            settingFragment.setArguments(arguments);
            settingFragment.setTargetFragment(this,SETTING_REQUEST);

            settingFragment.show(getFragmentManager(),null);
            return true;
        }

        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (PreferenceManager.getDefaultSharedPreferences(getActivity()).contains(SettingFragment.PREF_IMAGE_SCALE_TYPE)){
            String scaleTypeString = PreferenceManager.getDefaultSharedPreferences(getActivity())
                                            .getString(SettingFragment.PREF_IMAGE_SCALE_TYPE,null);

            ImageView.ScaleType scaleType = ImageView.ScaleType.valueOf(scaleTypeString);
            mWineImage.setScaleType(scaleType);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_REQUEST && resultCode == Activity.RESULT_OK){
            ImageView.ScaleType scaleType = (ImageView.ScaleType) data.getSerializableExtra(SettingActivity.EXTRA_WINE_IMAGE_SCLAE_TYPE);
            mWineImage.setScaleType(scaleType);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mWineImage != null){
            outState.putSerializable(STATE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());
        }

    }
}
