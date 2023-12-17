/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpsitchat;

/**
 *
 * @author Karina
 */
public class User {
    
    private String username;
    private String password;

    // Costruttore
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter per username
    public String getUsername() {
        return username;
    }

    // Getter per password
    public String getPassword() {
        return password;
    }
}

