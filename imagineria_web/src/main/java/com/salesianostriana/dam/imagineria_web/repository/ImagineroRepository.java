package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagineroRepository extends JpaRepository<Imaginero, UUID> {

    Optional<Imaginero> findByUsername(String username);

    List<Imaginero> findByName(String name);
}
