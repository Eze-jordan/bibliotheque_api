package com.esiitech.biblioteque.repository;

import com.esiitech.biblioteque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByDateRetourIsNull(); // Liste des emprunts en cours
}
