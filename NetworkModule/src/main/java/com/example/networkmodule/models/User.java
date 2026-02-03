package com.example.networkmodule.models;

public class User {
    public Integer id;
    public String email;

    public String password;
    public String firstname;
    public String lastname;
    public String surname;
    public String dateOfBirth;
    public Integer gender;
    public String token;


    public User(){}

    public User(String email,String password, String firstname, String lastname, String surname,String dateOfBirth, Integer gender)
    {
        this.email=email;
        this.password=password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
