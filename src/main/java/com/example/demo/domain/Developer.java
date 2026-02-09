package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "developers")
public class Developer {
    @Id
    private int developerId;
    private String name;
    private String description;
    private String contactEmail;
    private int userId; // associated seller user

    public Developer(int developerId, String name, String description, String contactEmail, int userId) {
        this.developerId = developerId;
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.userId = userId;
    }

    public Developer() {}

    // Getters and setters
    public int getDeveloperId() { return developerId; }
    public void setDeveloperId(int developerId) { this.developerId = developerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
