package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.dto.LivreDTO;
import com.esiitech.biblioteque.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    // 🔒 Seuls les ADMIN peuvent ajouter un livre
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LivreDTO> ajouterLivre(@RequestBody LivreDTO livreDTO) {
        LivreDTO createdLivre = livreService.ajouterLivre(livreDTO);
        return ResponseEntity.ok(createdLivre);
    }

    // 🔓 Tous les utilisateurs peuvent voir les livres
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<LivreDTO>> getTousLivres() {
        List<LivreDTO> livres = livreService.getTousLivres();
        return ResponseEntity.ok(livres);
    }

    // 🔓 Tous les utilisateurs peuvent voir un livre par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<LivreDTO> getLivreParId(@PathVariable Long id) {
        LivreDTO livre = livreService.getLivreParId(id);
        return ResponseEntity.ok(livre);
    }

    // 🔒 Seuls les ADMIN peuvent modifier un livre
    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LivreDTO> modifierLivre(@PathVariable Long id, @RequestBody LivreDTO livreDTO) {
        LivreDTO updatedLivre = livreService.modifierLivre(id, livreDTO);
        return ResponseEntity.ok(updatedLivre);
    }

    // 🔒 Seuls les ADMIN peuvent supprimer un livre
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerLivre(@PathVariable Long id) {
        livreService.supprimerLivre(id);
        return ResponseEntity.noContent().build();
    }
}
