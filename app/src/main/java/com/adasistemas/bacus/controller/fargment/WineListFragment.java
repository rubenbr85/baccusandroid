package com.adasistemas.bacus.controller.fargment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adasistemas.bacus.R;
import com.adasistemas.bacus.controller.activity.WineryActivity;
import com.adasistemas.bacus.model.Wine;
import com.adasistemas.bacus.model.Winery;

/**
 * A simple {@link Fragment} subclass.
 */
public class WineListFragment extends Fragment {

    private onWineSelectedListener mOnWineSelectedListener = null;

    public WineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_wine_list, container, false);

        ListView listView = (ListView)root.findViewById(android.R.id.list);
        Winery winery = Winery.getIntance();

        ArrayAdapter<Wine> adapter= new ArrayAdapter<Wine>(getActivity(),android.R.layout.simple_list_item_1,winery.getWineList());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Antes, pero ahora con la tablet se puede comportar de dos maneras
                //Intent wineryIntent= new Intent(getActivity(), WineryActivity.class);
                //wineryIntent.putExtra(WineryActivity.EXTRA_WINE_INDEX,position);
                //startActivity(wineryIntent);
                if (mOnWineSelectedListener != null){
                    mOnWineSelectedListener.onWineSelected(position);
                }

            }
        });
        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnWineSelectedListener= null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnWineSelectedListener= (onWineSelectedListener) getActivity();
    }

    public  interface onWineSelectedListener{
        void onWineSelected(int wineIndex);
    }
}
