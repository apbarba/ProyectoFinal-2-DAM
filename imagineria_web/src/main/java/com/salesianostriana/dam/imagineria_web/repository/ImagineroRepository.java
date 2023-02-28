package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImagineroRepository extends JpaRepository<Imaginero, UUID>, JpaSpecificationExecutor<Imaginero> {

    List<Imaginero> findByName(String imaginero);

    //SE ME MUESTRA AL IMAGINERO CON LA LISTA DE OBRAS
    @Query("SELECT i FROM Imaginero i LEFT JOIN FETCH i.obras WHERE i.id = ?1")
    public Optional<Imaginero> findByIdWithObras(UUID id);

}
