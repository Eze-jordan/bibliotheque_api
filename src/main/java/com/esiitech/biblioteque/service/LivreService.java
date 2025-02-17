package com.esiitech.biblioteque.service;

import com.esiitech.biblioteque.dto.LivreDTO;
import com.esiitech.biblioteque.model.Livre;
import com.esiitech.biblioteque.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    // ✅ Ajouter un livre
    public LivreDTO ajouterLivre(LivreDTO livreDTO) {
        Livre livre = new Livre();
        livre.setTitre(livreDTO.getTitre());
        livre.setAuteur(livreDTO.getAuteur());
        livre.setIsbn(livreDTO.getIsbn());
        livre.setNombreExemplaires(livreDTO.getNombreExemplaires());

        Livre savedLivre = livreRepository.save(livre);
        return new LivreDTO(savedLivre);
    }

    // ✅ Récupérer tous les livres
    public List<LivreDTO> getTousLivres() {
        return livreRepository.findAll()
                .stream()
                .map(LivreDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Récupérer un livre par ID
    public LivreDTO getLivreParId(Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));
        return new LivreDTO(livre);
    }

    // ✅ Modifier un livre
    public LivreDTO modifierLivre(Long id, LivreDTO livreDTO) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        livre.setTitre(livreDTO.getTitre());
        livre.setAuteur(livreDTO.getAuteur());
        livre.setIsbn(livreDTO.getIsbn());
        livre.setNombreExemplaires(livreDTO.getNombreExemplaires());

        Livre updatedLivre = livreRepository.save(livre);
        return new LivreDTO(updatedLivre);
    }

    // ✅ Supprimer un livre
    public void supprimerLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
