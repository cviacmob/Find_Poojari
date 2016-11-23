package com.cviac.find_poojari.datamodel;

import java.io.Serializable;

public class VideoInfo implements Serializable
{

    private int photoID;

    private String ytubeURL;

    public VideoInfo(int photoID, String ytubeURL)
    {
        this.photoID = photoID;
        this.ytubeURL = ytubeURL;
    }

    public int getPhotoID()
    {
        return photoID;
    }

    public void setPhotoID(int photoID)
    {
        this.photoID = photoID;
    }

    public String getYtubeURL()
    {
        return ytubeURL;
    }

    public void setYtubeURL(String ytubeURL)
    {
        this.ytubeURL = ytubeURL;
    }
}