package com.esiitech.biblioteque.mapper;

import com.esiitech.biblioteque.dto.LivreDTO;
import com.esiitech.biblioteque.model.Livre;

public class LivreMapper {

    public static LivreDTO toDTO(Livre livre) {
        if (livre == null) {
            return null;
        }
        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setTitre(livre.getTitre());
        dto.setAuteur(livre.getAuteur());
        dto.setIsbn(livre.getIsbn());
        dto.setNombreExemplaires(livre.getNombreExemplaires());
        return dto;
    }
}
