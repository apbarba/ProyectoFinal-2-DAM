package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ObrasRepository extends JpaRepository<Obras, UUID>, JpaSpecificationExecutor<Obras> {

    List<Obras> findByImaginero(Imaginero imaginero);

    List<Obras> findByTitulo(String titulo);

    List<Obras> findByEstado(String estado);
}
