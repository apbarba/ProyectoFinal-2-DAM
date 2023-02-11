package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Cliente;
import com.salesianostriana.dam.imagineria_web.model.Compras;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Long> {

    List<Compras> findByObra(Obras obras);

    List<Compras> findByCliente(Cliente cliente);
}
