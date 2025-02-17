package com.esiitech.biblioteque.dto;

import com.esiitech.biblioteque.model.Emprunt;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EmpruntDTO {
    private Long id;
    private Long utilisateurId;
    private Long livreId;
    private LocalDate dateEmprunt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getLivreId() {
        return livreId;
    }

    public void setLivreId(Long livreId) {
        this.livreId = livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    private LocalDate dateRetour;

    public EmpruntDTO() {}

    // ✅ Constructeur prenant un Emprunt (conversion entité -> DTO)
    public EmpruntDTO(Emprunt emprunt) {
        this.id = emprunt.getId();
        this.utilisateurId = emprunt.getUtilisateur().getId();
        this.livreId = emprunt.getLivre().getId();
        this.dateEmprunt = emprunt.getDateEmprunt();
        this.dateRetour = emprunt.getDateRetour();
    }
}
