package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Cliente;
import com.salesianostriana.dam.imagineria_web.model.Compras;
import com.salesianostriana.dam.imagineria_web.repository.ComprasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComprasService {

    private final ComprasRepository comprasRepository;

    public List<Compras> findByCliente(Cliente cliente){

        return comprasRepository.findByCliente(cliente);
    }

    public Optional<Compras> findById(Long id){

        return comprasRepository.findById(id);
    }

    public Compras save(Compras compras){

        return comprasRepository.save(compras);
    }

    public void delete(Compras compras){

        comprasRepository.delete(compras);
    }
}
