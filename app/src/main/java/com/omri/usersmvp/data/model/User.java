package com.omri.usersmvp.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by omricohen294 on 28/09/2017.
 */

@Entity(nameInDb = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String email;

    @Property(nameInDb = "pass")
    private String pass;

    @Generated(hash = 1402565211)
    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    @Override
    public String toString() {
        return "Email : " + email + " , Password " + pass;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
