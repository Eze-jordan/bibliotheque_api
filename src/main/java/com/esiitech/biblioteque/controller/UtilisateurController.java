package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.dto.UtilisateurDTO;
import com.esiitech.biblioteque.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@PreAuthorize("hasRole('ADMIN')")

@Tag(name = "Livres", description = "Gestion des livres")  // 📌 Catégorie dans Swagger
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Ajouter un nouvel utilisateur
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Ajouter un livre", description = "Permet à un administrateur d'ajouter un livre")
    public ResponseEntity<UtilisateurDTO> ajouterUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO createdUtilisateur = utilisateurService.ajouterUtilisateur(utilisateurDTO);
        return ResponseEntity.ok(createdUtilisateur);
    }

    // Récupérer tous les utilisateurs
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtenir tous les livres", description = "Permet aux utilisateurs et admins de voir tous les livres disponibles")
    public ResponseEntity<List<UtilisateurDTO>> getTousUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getTousUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    // Récupérer un utilisateur par son ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtenir un livre par ID", description = "Permet aux utilisateurs et admins de voir un livre spécifique")
    public ResponseEntity<UtilisateurDTO> getUtilisateurParId(@PathVariable Long id) {
        UtilisateurDTO utilisateur = utilisateurService.getUtilisateurParId(id);
        return ResponseEntity.ok(utilisateur);
    }

    // Modifier un utilisateur
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Modifier un livre", description = "Permet à un administrateur de modifier un livre")
    public ResponseEntity<UtilisateurDTO> modifierUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO updatedUtilisateur = utilisateurService.modifierUtilisateur(id, utilisateurDTO);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un livre", description = "Permet à un administrateur de supprimer un livre")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
