package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObrasRepository extends JpaRepository<Obras, Long> {

    List<Obras> findByImaginero(User imaginero);

    List<Obras> findByTitulo(String titulo);

    List<Obras> findByEstado(String estado);
}
