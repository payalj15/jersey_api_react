/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.heroku;

/**
 *
 * @author shiv
 */
//@XmlRootElement       //only needed if we also want to generate XML

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Message {
    
    private String firstName;
    
    private String lastName;
    
    private String Email;
    
    private String Role;
    
    private String Password;
    
    private int id;
    public void setid (int id){
        this.id=id;
    }
    public int getid() {
        return id;
    }
    
    public String getPassword() {
        return Password;
    }
 
    public void setPassword(String firstName) {
        this.Password = Password;
    }
    
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return Email;
    }
 
    public void setEmail(String Email) {
        this.Email = Email;
    }
 
    public String getRole() {
        return Role;
    }
 
    public void setRole(String role) {
        this.Role = role;
    }
    
    
}
