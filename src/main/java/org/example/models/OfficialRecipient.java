package org.example.models;

import java.io.Serializable;

public class OfficialRecipient extends Recipient implements Serializable  {
    private static final long serialVersionUID = 5738393939339393L;

    private String name;

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

    @Override
    public String toString() {
        return "OfficialRecipient{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }

    private String email;
    private String designation;
}
