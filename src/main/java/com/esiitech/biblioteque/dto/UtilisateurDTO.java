package com.esiitech.biblioteque.dto;

import com.esiitech.biblioteque.model.Utilisateur;
import com.esiitech.biblioteque.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role; // Stocké sous forme de String

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UtilisateurDTO() {}

    // ✅ Constructeur prenant un Utilisateur (conversion entité -> DTO)
    public UtilisateurDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.email = utilisateur.getEmail();
        this.motDePasse = utilisateur.getMotDePasse();
        this.role = utilisateur.getRole().name(); // Conversion Enum -> String
    }
}
