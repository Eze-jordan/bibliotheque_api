    package com.esiitech.biblioteque.model;

    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "livres")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Livre {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public String getAuteur() {
            return auteur;
        }

        public void setAuteur(String auteur) {
            this.auteur = auteur;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public int getNombreExemplaires() {
            return NombreExemplaires;
        }

        public void setNombreExemplaires(int nombreExemplaires) {
            NombreExemplaires = nombreExemplaires;
        }

        @Column(nullable = false)
        private String titre;
        @Column(nullable = false)
        private String auteur;

        @Column(unique = true, nullable = false)
        private String isbn;
        @Column(nullable = false)
        private int NombreExemplaires;
    }
