package com.esiitech.biblioteque.service;

import com.esiitech.biblioteque.dto.UtilisateurDTO;
import com.esiitech.biblioteque.model.Role;
import com.esiitech.biblioteque.model.Utilisateur;
import com.esiitech.biblioteque.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Ajouter un utilisateur
    public UtilisateurDTO ajouterUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        utilisateur.setRole(Role.valueOf(utilisateurDTO.getRole())); // Conversion String -> Enum
        utilisateur.setEmprunts(null); // Assurer que la liste des emprunts est bien initialisée

        Utilisateur savedUser = utilisateurRepository.save(utilisateur);
        return new UtilisateurDTO(savedUser);
    }

    // ✅ Récupérer tous les utilisateurs
    public List<UtilisateurDTO> getTousUtilisateurs() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ Récupérer un utilisateur par ID
    public UtilisateurDTO getUtilisateurParId(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return new UtilisateurDTO(utilisateur);
    }

    // ✅ Modifier un utilisateur
    public UtilisateurDTO modifierUtilisateur(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
        utilisateur.setRole(Role.valueOf(utilisateurDTO.getRole()));

        Utilisateur updatedUser = utilisateurRepository.save(utilisateur);
        return new UtilisateurDTO(updatedUser);
    }

    // ✅ Supprimer un utilisateur
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
