package com.laureanray.codesimulatorandchecker.data.model;

import androidx.annotation.IdRes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;


    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
