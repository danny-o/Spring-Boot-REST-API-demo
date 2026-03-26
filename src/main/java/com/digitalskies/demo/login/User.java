package com.digitalskies.demo.login;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private long id;

    private String email;

    private  String hashedPassword;

    public User(){

    }

    public User(String email,String hashedPassword) {
        this.id = id;
        this.email=email;
        this.hashedPassword = hashedPassword;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


}
