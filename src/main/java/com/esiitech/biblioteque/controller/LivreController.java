package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.dto.LivreDTO;
import com.esiitech.biblioteque.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    // Ajouter un nouveau livre
    @PostMapping
    public ResponseEntity<LivreDTO> ajouterLivre(@RequestBody LivreDTO livreDTO) {
        LivreDTO createdLivre = livreService.ajouterLivre(livreDTO);
        return ResponseEntity.ok(createdLivre);
    }

    // Récupérer tous les livres
    @GetMapping
    public ResponseEntity<List<LivreDTO>> getTousLivres() {
        List<LivreDTO> livres = livreService.getTousLivres();
        return ResponseEntity.ok(livres);
    }

    // Récupérer un livre par son ID
    @GetMapping("/{id}")
    public ResponseEntity<LivreDTO> getLivreParId(@PathVariable Long id) {
        LivreDTO livre = livreService.getLivreParId(id);
        return ResponseEntity.ok(livre);
    }

    // Modifier un livre
    @PutMapping("/{id}")
    public ResponseEntity<LivreDTO> modifierLivre(@PathVariable Long id, @RequestBody LivreDTO livreDTO) {
        LivreDTO updatedLivre = livreService.modifierLivre(id, livreDTO);
        return ResponseEntity.ok(updatedLivre);
    }

    // Supprimer un livre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerLivre(@PathVariable Long id) {
        livreService.supprimerLivre(id);
        return ResponseEntity.noContent().build();
    }
}
