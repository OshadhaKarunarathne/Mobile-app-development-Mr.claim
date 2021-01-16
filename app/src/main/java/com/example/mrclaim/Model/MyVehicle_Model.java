package com.example.mrclaim.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MyVehicle_Model implements Parcelable {


    public MyVehicle_Model() {
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setEngineNo(String engineNo) {
        EngineNo = engineNo;
    }

    public void setExDate(String exDate) {
        ExDate = exDate;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setCurrentUID(String currentUID) {
        this.currentUID = currentUID;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private  String Capacity;
    private  String Color;
    private  String EngineNo;
    private  String ExDate;
    private  String Model;
    private  String currentUID;
    private  String imagePath;
    private  String VehicleNo;




    protected MyVehicle_Model(Parcel in) {
        Capacity = in.readString();
        Color = in.readString();
        EngineNo = in.readString();
        ExDate = in.readString();
        Model = in.readString();
        currentUID = in.readString();
        imagePath = in.readString();
        VehicleNo = in.readString();
    }

    public static final Creator<MyVehicle_Model> CREATOR = new Creator<MyVehicle_Model>() {
        @Override
        public MyVehicle_Model createFromParcel(Parcel in) {
            return new MyVehicle_Model(in);
        }

        @Override
        public MyVehicle_Model[] newArray(int size) {
            return new MyVehicle_Model[size];
        }
    };
    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }


    public String getCapacity() {
        return Capacity;
    }

    public String getColor() {
        return Color;
    }

    public String getEngineNo() {
        return EngineNo;
    }

    public String getExDate() {
        return ExDate;
    }

    public String getModel() {
        return Model;
    }

    public String getCurrentUID() {
        return currentUID;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Capacity);
        dest.writeString(Color);
        dest.writeString(EngineNo);
        dest.writeString(ExDate);
        dest.writeString(Model);
        dest.writeString(currentUID);
        dest.writeString(imagePath);
        dest.writeString(VehicleNo);
    }
}
