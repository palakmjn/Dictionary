package com.example.android.miwok;

/**
 * Created by User on 1/11/2018.
 */

public class Word {
    private String mengtrans;
    private String mmiwoktrans;
    private int mimageid;
    private int maudioid;
    private static final int id=-1;
    public Word(String miwok,String eng,int audid){
        mengtrans=eng;
        mmiwoktrans=miwok;
        maudioid=audid;
    }
    public Word(String miwok,String eng,int imageid,int audid){
        mengtrans=eng;
        mmiwoktrans=miwok;
        mimageid=imageid;
        maudioid=audid;
    }
    public String getengtrans(){
        return mengtrans;
    }
    public String getmiwoktrans(){
        return mmiwoktrans;
    }
    public int getimage(){
        return mimageid;
    }
    public  boolean hasImage(){
        return mimageid!=id;
    }
    public int getaudio(){
        return maudioid;
    }
}

