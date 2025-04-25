package com.example.vacaciones.dao;

import com.example.vacaciones.entity.Fotoperfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FotoperfilRepository extends JpaRepository<Fotoperfil, Integer> {
    Optional<Fotoperfil> findByUrl(String url);
}
