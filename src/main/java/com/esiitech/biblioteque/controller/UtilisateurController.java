package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.dto.UtilisateurDTO;
import com.esiitech.biblioteque.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Ajouter un nouvel utilisateur
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UtilisateurDTO> ajouterUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO createdUtilisateur = utilisateurService.ajouterUtilisateur(utilisateurDTO);
        return ResponseEntity.ok(createdUtilisateur);
    }

    // Récupérer tous les utilisateurs
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UtilisateurDTO>> getTousUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getTousUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UtilisateurDTO> getUtilisateurParId(@PathVariable Long id) {
        UtilisateurDTO utilisateur = utilisateurService.getUtilisateurParId(id);
        return ResponseEntity.ok(utilisateur);
    }

    // Modifier un utilisateur
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UtilisateurDTO> modifierUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO updatedUtilisateur = utilisateurService.modifierUtilisateur(id, utilisateurDTO);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
