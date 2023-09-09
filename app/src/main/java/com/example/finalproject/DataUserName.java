package com.example.finalproject;

public class DataUserName {


    private String UserName ;
    private String Email ;
    private String Password ;

    private int id ;


    public DataUserName(String userName, String email, String password) {
        UserName = userName;
        Email = email;
        Password = password;
    }



    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
