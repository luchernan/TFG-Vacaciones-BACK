package com.example.vacaciones.dao;


import com.example.vacaciones.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {

    @Query(value = """
        SELECT v.id
        FROM viajes v
        WHERE v.usuario_id = :userId
        UNION
        SELECT c.viaje_id
        FROM comentarios c
        WHERE c.usuario_id = :userId
        """, nativeQuery = true)
    List<Integer> findParticipatedTripIds(@Param("userId") Integer userId);


    List<Viaje> findByDestinoId(Integer destinoId);

}