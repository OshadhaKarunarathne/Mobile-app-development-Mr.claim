package com.example.mrclaim.Model;

public class Gargae_Model {

    public  Gargae_Model(){}

    private String CurrentUID;
    private  String  Vehicle_No;
    private  String  Gname;
    private  String  Timedate;
    private  String  FullAddress;

    public String getCurrentUID() {
        return CurrentUID;
    }

    public void setCurrentUID(String currentUID) {
        CurrentUID = currentUID;
    }

    public Gargae_Model(String currentUID, String vehicle_No, String gname, String timedate, String fullAddress) {
        CurrentUID = currentUID;
        Vehicle_No = vehicle_No;
        Gname = gname;
        Timedate = timedate;
        FullAddress = fullAddress;
    }



    public String getVehicle_No() {
        return Vehicle_No;
    }

    public void setVehicle_No(String vehicle_No) {
        Vehicle_No = vehicle_No;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public String getTimedate() {
        return Timedate;
    }

    public void setTimedate(String timedate) {
        Timedate = timedate;
    }

    public String getFullAddress() {
        return FullAddress;
    }

    public void setFullAddress(String fullAddress) {
        FullAddress = fullAddress;
    }
}
