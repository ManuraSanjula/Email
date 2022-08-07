package org.example.models;

import java.io.Serializable;
import java.util.Date;


public class PersonalRecipient extends Recipient implements Serializable {
    private static final long serialVersionUID = 526819028271827192L;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private String email;
    private String nickName;
    private Date birthday;

    @Override
    public String toString() {
        return "PersonalRecipient{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
