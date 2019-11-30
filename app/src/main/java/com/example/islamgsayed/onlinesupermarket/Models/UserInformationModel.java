package com.example.islamgsayed.onlinesupermarket.Models;

/**
 * Created by Islam.G sayed on 7/17/2018.
 */

public class UserInformationModel  {
     String name;
     String email ;
     String password ;
   String Phone ;

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setPhone(String phone) {
//        this.Phone = phone;
//    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return Phone;
    }

    public UserInformationModel(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.Phone = phone;
    }
}
