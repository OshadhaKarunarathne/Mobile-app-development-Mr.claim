package com.example.mrclaim;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicle implements Parcelable {

    private String vehino;
    private String expdate;
    private String imageurl;
    private String model;
    private String color;
    private String chassisNo;
    private String engineNo;
    private String engcapacity;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getEngcapacity() {
        return engcapacity;
    }

    public void setEngcapacity(String engcapacity) {
        this.engcapacity = engcapacity;
    }


    public Vehicle(String vehino, String expdate, String imageurl, String model,String color,String chassisNo,String engineNo,String engcapacity) {
        this.vehino = vehino;
        this.expdate = expdate;
        this.imageurl = imageurl;
        this.model = model;
        this.color = color;
        this.chassisNo = chassisNo;
        this.engineNo= engineNo;
        this.engcapacity=engcapacity;
    }

    protected Vehicle(Parcel in) {
        vehino = in.readString();
        expdate = in.readString();
        imageurl = in.readString();
        model = in.readString();
        color = in.readString();
        chassisNo = in.readString();
        engineNo = in.readString();
        engcapacity = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vehino);
        dest.writeString(expdate);
        dest.writeString(imageurl);
        dest.writeString(model);
        dest.writeString(color);
        dest.writeString(chassisNo);
        dest.writeString(engineNo);
        dest.writeString(engcapacity);
    }
}
