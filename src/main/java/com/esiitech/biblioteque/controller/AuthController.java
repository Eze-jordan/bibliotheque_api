package com.esiitech.biblioteque.controller;

import com.esiitech.biblioteque.config.JwtUtil;
import com.esiitech.biblioteque.dto.AuthRequest;
import com.esiitech.biblioteque.dto.AuthResponse;
import com.esiitech.biblioteque.model.Utilisateur;
import com.esiitech.biblioteque.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UtilisateurRepository utilisateurRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UtilisateurRepository utilisateurRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // ✅ NE PAS APPELER `findByEmail()` DE MANIÈRE STATIQUE
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = jwtUtil.generateToken(userDetails.getUsername(), utilisateur.getRole());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}

