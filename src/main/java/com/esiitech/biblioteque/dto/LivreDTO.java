package com.esiitech.biblioteque.dto;

import com.esiitech.biblioteque.model.Livre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivreDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    private String titre;
    private String auteur;
    private String isbn;
    private int nombreExemplaires;

    public LivreDTO() {}

    // ✅ Constructeur prenant un Livre (conversion entité -> DTO)
    public LivreDTO(Livre livre) {
        this.id = livre.getId();
        this.titre = livre.getTitre();
        this.auteur = livre.getAuteur();
        this.isbn = livre.getIsbn();
    }
}
