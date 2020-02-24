package com.nowak.aghquotapi.requestBodies;

public class LoginDto {

    private  String name;

    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
