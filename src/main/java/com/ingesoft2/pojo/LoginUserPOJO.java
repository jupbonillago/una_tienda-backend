package com.ingesoft2.pojo;

public class LoginUserPOJO {

    private String email;
    private String password;

    public LoginUserPOJO() {
        //Creacion en vacio para luego ir agregando los valores de un Person a UserPOJO.
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}