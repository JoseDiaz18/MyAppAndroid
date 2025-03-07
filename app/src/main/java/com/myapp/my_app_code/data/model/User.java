package com.myapp.my_app_code.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;

    // Constructor
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
