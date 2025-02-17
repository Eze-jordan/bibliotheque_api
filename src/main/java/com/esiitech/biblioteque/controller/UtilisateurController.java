package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.dto.UtilisateurDTO;
import com.esiitech.biblioteque.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Ajouter un nouvel utilisateur
    @PostMapping
    public ResponseEntity<UtilisateurDTO> ajouterUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO createdUtilisateur = utilisateurService.ajouterUtilisateur(utilisateurDTO);
        return ResponseEntity.ok(createdUtilisateur);
    }

    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getTousUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getTousUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurParId(@PathVariable Long id) {
        UtilisateurDTO utilisateur = utilisateurService.getUtilisateurParId(id);
        return ResponseEntity.ok(utilisateur);
    }

    // Modifier un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> modifierUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO updatedUtilisateur = utilisateurService.modifierUtilisateur(id, utilisateurDTO);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
