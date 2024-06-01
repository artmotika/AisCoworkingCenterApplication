package com.aiscoworking.aiscoworking.repository;

import com.aiscoworking.aiscoworking.model.AisUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AisUserRepository extends JpaRepository<AisUser, Long> {
    Optional<AisUser> findByUsername(String username);
}
