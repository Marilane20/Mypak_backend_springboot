package com.houseproject.MyPak.config;

import com.houseproject.MyPak.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Désactiver CSRF — on utilise JWT pas les sessions
            .csrf(AbstractHttpConfigurer::disable)

            // Définir les règles d'accès
            .authorizeHttpRequests(auth -> auth

                // Routes publiques — tout le monde peut accéder
                .requestMatchers(
                    "/api/auth/**",
                    "/api/properties/search/**",
                    "/api/properties/{id}"
                ).permitAll()

                // Routes admin seulement
                .requestMatchers("/api/admin/**")
                    .hasAuthority("ROLE_ADMIN")

                // Routes bailleurs seulement
                .requestMatchers("/api/owner/**")
                    .hasAuthority("ROLE_OWNER")

                // Tout le reste → authentification requise
                .anyRequest().authenticated()
            )

            // Pas de session — on est en mode stateless avec JWT
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Notre filtre JWT avant le filtre standard de Spring
            .authenticationManager(authenticationManager())
            .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

 

    @Bean
    public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider =
    new DaoAuthenticationProvider(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return new org.springframework.security.authentication.ProviderManager(provider);
}
}