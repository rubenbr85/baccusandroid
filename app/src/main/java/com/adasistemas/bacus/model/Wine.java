/**
 * Created by ruben on 19/09/2016.
 */
package com.adasistemas.bacus.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Wine implements Serializable{
    private String  mName = null;
    private String mType = null;
    private Bitmap mPhoto = null;
    private String mCompanyName = null;
    private String mCompanyWeb = null;
    private String mNotes = null;
    private String mOrigin = null;
    private int mRating = 0; //0 to 5
    private String mPhotoURL = null;

    private List<String> mGrapes = new LinkedList<>();

    public Wine(int rating, String name, String type, String photoURL, String companyName, String companyWeb, String notes, String origin) {
        mRating = rating;
        mName = name;
        mType = type;
        mPhotoURL = photoURL;
        mCompanyName = companyName;
        mCompanyWeb = companyWeb;
        mNotes = notes;
        mOrigin = origin;
    }

    public int getRating() {
        return mRating;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

    public Bitmap getPhoto()
    {
        if (mPhoto == null){
            mPhoto = getBitmapFromURL(getPhotoURL());
        }
        return mPhoto;
    }

    private Bitmap getBitmapFromURL(String photoURL) {
        InputStream in = null;
        try{
            in = new URL(photoURL).openStream();
            return BitmapFactory.decodeStream(in);

        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Error downloading image",ex);
            return null;
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                   // e.printStackTrace();
                }
            }
        }

    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public String getPhotoURL() {
        return mPhotoURL;
    }

    public String getCompanyWeb() {
        return mCompanyWeb;
    }

    public String getNotes() {
        return mNotes;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void addGrape(String grape){
        mGrapes.add(grape);
    }

    public int getGrapeCount(){
        return  mGrapes.size();
    }

    public String getGrape(int index){
        return mGrapes.get(index);

    }

    @Override
    public String toString(){
        return getName();
    }
}
