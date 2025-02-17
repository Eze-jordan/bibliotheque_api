package com.esiitech.biblioteque.repository;

import com.esiitech.biblioteque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
