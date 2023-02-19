package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, UUID>, JpaSpecificationExecutor<Favoritos> {
}
