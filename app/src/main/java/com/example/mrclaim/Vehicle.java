package com.example.mrclaim;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicle implements Parcelable {

    private String vehino;
    private String expdate;
    private String imageurl;

    public Vehicle(String vehino, String expdate, String imageurl) {
        this.vehino = vehino;
        this.expdate = expdate;
        this.imageurl = imageurl;
    }

    protected Vehicle(Parcel in) {
        vehino = in.readString();
        expdate = in.readString();
        imageurl = in.readString();
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
    }
}
