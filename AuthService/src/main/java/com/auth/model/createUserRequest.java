package com.auth.model;

public class createUserRequest {
    private String userid;
    private String password;
    private String role; // accept "admin" or "ADMIN"

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}


