/**
 * Created by ruben on 19/09/2016.
 */
package com.adasistemas.bacus.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Wine implements Serializable{
    private String  mName = null;
    private String mType = null;
    private int mPhoto = 0;
    private String mCompanyName = null;
    private String mCompanyWeb = null;
    private String mNotes = null;
    private String mOrigin = null;
    private int mRating = 0; //0 to 5
    private List<String> mGrapes = new LinkedList<>();

    public Wine(int rating, String name, String type, int photo, String companyName, String companyWeb, String notes, String origin) {
        mRating = rating;
        mName = name;
        mType = type;
        mPhoto = photo;
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

    public int getPhoto() {
        return mPhoto;
    }

    public String getCompanyName() {
        return mCompanyName;
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
}
