package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.ImagineroRole;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDTO.CreateDtoImaginero;
import com.salesianostriana.dam.imagineria_web.repository.ImagineroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImaginerosService {

    private final ImagineroRepository imagineroRepository;

    private final PasswordEncoder passwordEncoder;

    public Imaginero createImaginero(CreateDtoImaginero getDtoImaginero, EnumSet<ImagineroRole> roles){

        Imaginero imaginero = Imaginero.builder()
                .username(getDtoImaginero.getUsername())
                .password(passwordEncoder.encode(getDtoImaginero.getPassword()))
                .email(getDtoImaginero.getEmail())
                .fullname(getDtoImaginero.getName())
                .rol(roles)
                .build();

        return imagineroRepository.save(imaginero);
    }

    public Imaginero createImaginerWithUserRole(CreateDtoImaginero getDtoImaginero){

        return createImaginero(getDtoImaginero, EnumSet.of(ImagineroRole.USER));
    }

    public Imaginero createImaginerWithAdminRole(CreateDtoImaginero getDtoImaginero){

        return createImaginero(getDtoImaginero, EnumSet.of(ImagineroRole.ADMIN));
    }

    public List<Imaginero> findAll(){

        return imagineroRepository.findAll();
    }

    public Optional<Imaginero> findByUsername(String username){

        return  imagineroRepository.findByUsername(username);
    }

    public Optional<Imaginero> edit(Imaginero imaginero){

        return imagineroRepository.findById(imaginero.getId())
                .map(im ->{
                    im.setFullname(imaginero.getFullname());

                    return imagineroRepository.save(im);

                }).or(() -> Optional.empty());
    }

    public Optional<Imaginero> editPassword(UUID imagineroId, String newPassword){

        return imagineroRepository.findById(imagineroId)
                .map(im -> {

                    im.setPassword(passwordEncoder.encode(newPassword));

                    return imagineroRepository.save(im);

                }).or(() -> Optional.empty());
    }

    public Optional<Imaginero> editEmail(Imaginero imaginero){

        return imagineroRepository.findById(imaginero.getId())
                .map(im -> {

                    im.setEmail(im.getEmail());

                    return imagineroRepository.save(im);

                }).or(() -> Optional.empty());
    }

    public void deleteById(UUID id){

        if (imagineroRepository.existsById(id))
            imagineroRepository.deleteById(id);
    }

    public void delete(Imaginero imaginero){

        deleteById(imaginero.getId());
    }

    public boolean passwordMatch(Imaginero imaginero, String clearPassword){

        return passwordEncoder.matches(clearPassword, imaginero.getPassword());
    }

    public Optional<Imaginero> findById(UUID id){

        return imagineroRepository.findById(id);
    }

    public List<Imaginero> findByName(String name){

        return imagineroRepository.findByName(name);
    }

    public Imaginero save(Imaginero imaginero){

        return imagineroRepository.save(imaginero);
    }
}
