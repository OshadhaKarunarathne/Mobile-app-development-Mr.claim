package com.example.mrclaim;

public class History {

    private String vehicleNo;
    private String dateTime;

    public History(){


    }

    public History(String vehicleNo, String dateTime) {
        this.vehicleNo = vehicleNo;
        this.dateTime = dateTime;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
