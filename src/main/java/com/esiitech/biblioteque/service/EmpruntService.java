package com.esiitech.biblioteque.service;

import com.esiitech.biblioteque.dto.EmpruntDTO;
import com.esiitech.biblioteque.model.Emprunt;
import com.esiitech.biblioteque.model.Livre;
import com.esiitech.biblioteque.model.Utilisateur;
import com.esiitech.biblioteque.repository.EmpruntRepository;
import com.esiitech.biblioteque.repository.LivreRepository;
import com.esiitech.biblioteque.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final LivreRepository livreRepository;

    public EmpruntService(EmpruntRepository empruntRepository, UtilisateurRepository utilisateurRepository, LivreRepository livreRepository) {
        this.empruntRepository = empruntRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.livreRepository = livreRepository;
    }

    // ✅ Ajouter un emprunt
    public EmpruntDTO ajouterEmprunt(EmpruntDTO empruntDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(empruntDTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Livre livre = livreRepository.findById(empruntDTO.getLivreId())
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        Emprunt emprunt = new Emprunt();
        emprunt.setUtilisateur(utilisateur);
        emprunt.setLivre(livre);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetour(LocalDate.now());

        Emprunt savedEmprunt = empruntRepository.save(emprunt);
        return new EmpruntDTO(savedEmprunt);
    }

    // ✅ Récupérer tous les emprunts
    public List<EmpruntDTO> getTousEmprunts() {
        return empruntRepository.findAll()
                .stream()
                .map(EmpruntDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Récupérer un emprunt par ID
    public EmpruntDTO getEmpruntParId(Long id) {
        Emprunt emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));
        return new EmpruntDTO(emprunt);
    }

    // ✅ Modifier un emprunt (ex: mettre une date de retour)
    public EmpruntDTO modifierEmprunt(Long id, EmpruntDTO empruntDTO) {
        Emprunt emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprunt non trouvé"));

        emprunt.setDateRetour(empruntDTO.getDateRetour());

        Emprunt updatedEmprunt = empruntRepository.save(emprunt);
        return new EmpruntDTO(updatedEmprunt);
    }

    // ✅ Supprimer un emprunt
    public void supprimerEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }
}
