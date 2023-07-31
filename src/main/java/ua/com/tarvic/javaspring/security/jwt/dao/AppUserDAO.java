package ua.com.tarvic.javaspring.security.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.tarvic.javaspring.security.jwt.models.AppUser;

import java.util.Optional;

public interface AppUserDAO extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByEmail(String email);
}
