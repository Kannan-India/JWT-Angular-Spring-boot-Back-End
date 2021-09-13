package com.indsys.iLabelBackEnd.authentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserProfile")
public class UserProfile {

    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("_email")
    private String email;

    @JsonProperty("_userName")
    private String userName;

    @JsonProperty("_password")
    private String password;

    @JsonProperty("_role")
    private String role;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //TODO: No constructor has been manually initialized
}
