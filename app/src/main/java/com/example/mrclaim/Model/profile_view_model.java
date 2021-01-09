package com.example.mrclaim.Model;

import android.widget.TextView;

public class profile_view_model {


   private String CurrentUID;
    private String Firstname;
    private String Lastname;
    private String NIC;
    private String Birthday;

    public String getCurrentUID() {
        return CurrentUID;
    }

    public void setCurrentUID(String currentUID) {
        CurrentUID = currentUID;
    }

    public profile_view_model(String currentUID, String firstname, String lastname, String NIC, String birthday) {
        CurrentUID = currentUID;
        Firstname = firstname;
        Lastname = lastname;
        this.NIC = NIC;
        Birthday = birthday;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
}
