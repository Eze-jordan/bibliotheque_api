package com.esiitech.biblioteque.service;

import com.esiitech.biblioteque.model.Utilisateur;
import com.esiitech.biblioteque.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    // 🔥 Ajout du constructeur pour injecter utilisateurRepository
    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        return User.withUsername(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())  // Mot de passe déjà encodé avec BCrypt
                .roles(utilisateur.getRole().name())    // Convertit le rôle en `ROLE_USER` ou `ROLE_ADMIN`
                .build();
    }
}
