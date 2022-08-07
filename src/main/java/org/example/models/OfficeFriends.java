package org.example.models;

import java.io.Serializable;
import java.util.Date;

public class OfficeFriends extends Recipient implements Serializable {
    private static final long serialVersionUID = 77282916272829282L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    private String name;
    private String email;
    private String designation;
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Date birthday;

    @Override
    public String toString() {
        return "OfficeFriends{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
