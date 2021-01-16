package com.example.mrclaim.Model;

public class Profile_model {


    public Profile_model(){}

    private String currentUID;
    private String firstname;
    private String lastname;
    private String nic;
    private String birthday;

    public Profile_model(String currentUID, String firstname, String lastname, String nic, String birthday) {
        this.currentUID = currentUID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nic = nic;
        this.birthday = birthday;
    }

    public String getCurrentUID() {
        return currentUID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNic() {
        return nic;
    }

    public String getBirthday() {
        return birthday;
    }
}
