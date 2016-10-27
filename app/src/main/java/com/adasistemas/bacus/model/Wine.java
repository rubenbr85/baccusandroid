/**
 * Created by ruben on 19/09/2016.
 */
package com.adasistemas.bacus.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Wine implements Serializable{
    private String  mId = null;
    private String  mName = null;
    private String mType = null;
    //private Bitmap mPhoto = null;
    private String mCompanyName = null;
    private String mCompanyWeb = null;
    private String mNotes = null;
    private String mOrigin = null;
    private int mRating = 0; //0 to 5
    private String mPhotoURL = null;

    private List<String> mGrapes = new LinkedList<>();

    public Wine(String id, int rating, String name, String type, String photoURL, String companyName, String companyWeb, String notes, String origin) {
        mId = id;
        mRating = rating;
        mName = name;
        mType = type;
        mPhotoURL = photoURL;
        mCompanyName = companyName;
        mCompanyWeb = companyWeb;
        mNotes = notes;
        mOrigin = origin;
    }

    public String getId() {
        return mId;
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

    public Bitmap getPhoto(Context context)
    {
        //if (mPhoto == null){
         //   mPhoto = getBitmapFromURL(getPhotoURL(),context);
        //}
        //return mPhoto;

        return   getBitmapFromURL(getPhotoURL(),context);
    }

    private Bitmap getBitmapFromURL(String photoURL, Context context) {
        File imageFile = new File(context.getCacheDir(), getId());
        if (imageFile.exists()){
            return  BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }
        InputStream in = null;
        try{
            in = new URL(photoURL).openStream();
            Bitmap bmp = BitmapFactory.decodeStream(in);

            //Lo guardamos en cache
            FileOutputStream fos = new FileOutputStream(imageFile);
            bmp.compress(Bitmap.CompressFormat.PNG,90,fos);

            return bmp;
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
