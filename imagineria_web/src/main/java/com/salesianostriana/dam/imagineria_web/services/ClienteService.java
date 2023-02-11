package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Cliente;
import com.salesianostriana.dam.imagineria_web.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente findByUsername(String username){

        return clienteRepository.findByUsername(username);
    }

    public Optional<Cliente> findById(Long id){

        return clienteRepository.findById(id);
    }

    public List<Cliente> findByName(String name){

        return clienteRepository.findByName(name);
    }

    public Cliente save(Cliente cliente){

        return clienteRepository.save(cliente);
    }
}
