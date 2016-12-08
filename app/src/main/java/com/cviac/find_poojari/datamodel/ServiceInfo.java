package com.cviac.find_poojari.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceInfo implements Serializable {

    private int imgID;

    private String serviceNAME;

    List<ServiceInfo> sublist;

    public ServiceInfo(int imgID, String serviceNAME) {
        this.imgID = imgID;
        this.serviceNAME = serviceNAME;
        sublist = new ArrayList<ServiceInfo>();
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getServiceNAME() {
        return serviceNAME;
    }

    public void setServiceNAME(String serviceNAME) {
        this.serviceNAME = serviceNAME;
    }

    public void add(ServiceInfo sinfo) {
        sublist.add(sinfo);
    }

    public List<ServiceInfo> getSublist() {
        return sublist;
    }
}
