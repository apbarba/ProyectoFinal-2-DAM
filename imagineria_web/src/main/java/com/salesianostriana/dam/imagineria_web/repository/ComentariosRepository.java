package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Cliente;
import com.salesianostriana.dam.imagineria_web.model.Comentarios;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {

    List<Comentarios> findByObra(Obras obras);

    List<Comentarios> findByCliente(Cliente cliente);
}
