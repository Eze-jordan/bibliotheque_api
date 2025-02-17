package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.dto.EmpruntDTO;
import com.esiitech.biblioteque.service.EmpruntService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    // Ajouter un nouvel emprunt
    @PostMapping
    public ResponseEntity<EmpruntDTO> ajouterEmprunt(@RequestBody EmpruntDTO empruntDTO) {
        EmpruntDTO createdEmprunt = empruntService.ajouterEmprunt(empruntDTO);
        return ResponseEntity.ok(createdEmprunt);
    }

    // Récupérer tous les emprunts
    @GetMapping
    public ResponseEntity<List<EmpruntDTO>> getTousEmprunts() {
        List<EmpruntDTO> emprunts = empruntService.getTousEmprunts();
        return ResponseEntity.ok(emprunts);
    }

    // Récupérer un emprunt par son ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpruntDTO> getEmpruntParId(@PathVariable Long id) {
        EmpruntDTO emprunt = empruntService.getEmpruntParId(id);
        return ResponseEntity.ok(emprunt);
    }

    // Modifier un emprunt
    @PutMapping("/{id}")
    public ResponseEntity<EmpruntDTO> modifierEmprunt(@PathVariable Long id, @RequestBody EmpruntDTO empruntDTO) {
        EmpruntDTO updatedEmprunt = empruntService.modifierEmprunt(id, empruntDTO);
        return ResponseEntity.ok(updatedEmprunt);
    }

    // Supprimer un emprunt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmprunt(@PathVariable Long id) {
        empruntService.supprimerEmprunt(id);
        return ResponseEntity.noContent().build();
    }
}
