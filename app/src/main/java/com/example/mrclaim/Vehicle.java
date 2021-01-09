package com.example.mrclaim;

public class Vehicle {

    private String vehino;
    private String expdate;
    private String imageurl;

    public Vehicle(String vehino, String expdate, String imageurl) {
        this.vehino = vehino;
        this.expdate = expdate;
        this.imageurl = imageurl;
    }

    public String getVehino() {
        return vehino;
    }

    public void setVehino(String vehino) {
        this.vehino = vehino;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
