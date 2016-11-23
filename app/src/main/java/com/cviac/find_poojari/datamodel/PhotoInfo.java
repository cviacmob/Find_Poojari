package com.cviac.find_poojari.datamodel;

import java.io.Serializable;

public class PhotoInfo implements Serializable{

    private int photo_pics;

    public PhotoInfo(int photo_pics)
    {
        this.photo_pics=photo_pics;
    }

    public int getPhoto_pics()
    {
        return photo_pics;
    }

    public void setPhoto_pics(int photo_pics)
    {
        this.photo_pics = photo_pics;
    }

}
