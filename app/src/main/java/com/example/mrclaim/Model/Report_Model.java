package com.example.mrclaim.Model;

// report.java = ReportAccivity firebase connection.
public class Report_Model {

    public Report_Model() {

    }



    private String Mobility;
    private String EmergencyVehicle;

    private String CurrentUID;
    private String VehicleNo;
    private String DateTime;
    private String State;
    private String City;
    private String Address;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCurrentUID() {
        return CurrentUID;
    }

    public void setCurrentUID(String currentUID) {
        CurrentUID = currentUID;
    }


    public String getVehicleNo() {
        return VehicleNo;
    }

    public String getMobility() {
        return Mobility;
    }

    public void setMobility(String mobility) {
        Mobility = mobility;
    }

    public String getEmergencyVehicle() {
        return EmergencyVehicle;
    }

    public void setEmergencyVehicle(String emergencyVehicle) {
        EmergencyVehicle = emergencyVehicle;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
