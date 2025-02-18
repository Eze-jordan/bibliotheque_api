package com.esiitech.biblioteque.dto;


public class AuthResponse {
    private String token;

    public AuthResponse() {}  // Constructeur vide

    public AuthResponse(String token) { // Constructeur avec paramètre
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}