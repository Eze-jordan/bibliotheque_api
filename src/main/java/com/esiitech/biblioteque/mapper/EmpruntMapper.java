package com.esiitech.biblioteque.mapper;

import com.esiitech.biblioteque.dto.EmpruntDTO;
import com.esiitech.biblioteque.model.Emprunt;

public class EmpruntMapper {

    public static EmpruntDTO toDTO(Emprunt emprunt) {
        if (emprunt == null) {
            return null;
        }
        EmpruntDTO dto = new EmpruntDTO();
        dto.setId(emprunt.getId());
        dto.setUtilisateurId(emprunt.getUtilisateur().getId());
        dto.setLivreId(emprunt.getLivre().getId());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetour(emprunt.getDateRetour());
        return dto;
    }
}
