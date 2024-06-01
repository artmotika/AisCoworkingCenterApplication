package com.aiscoworking.aiscoworking.service;

import com.aiscoworking.aiscoworking.exception.ResourceNotFoundException;
import com.aiscoworking.aiscoworking.model.SecurityAisUser;
import com.aiscoworking.aiscoworking.repository.AisUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Console;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final AisUserRepository aisUserRepository;

    public JpaUserDetailsService(AisUserRepository aisUserRepository) {
        this.aisUserRepository = aisUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return aisUserRepository
                .findByUsername(username)
                .map(SecurityAisUser::new)
                .orElseThrow(() -> new ResourceNotFoundException("AisUser", "username", username));
    }
}
