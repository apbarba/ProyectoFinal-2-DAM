package com.salesianostriana.dam.imagineria_web.services;

import com.salesianostriana.dam.imagineria_web.exception.EmptyUserListException;
import com.salesianostriana.dam.imagineria_web.exception.ObrasNotFoundException;
import com.salesianostriana.dam.imagineria_web.exception.UserNotFoundException;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.UserRole;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.CreateDtoUser;
import com.salesianostriana.dam.imagineria_web.repository.ObrasRepository;
import com.salesianostriana.dam.imagineria_web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.validation.constraints.NotEmpty;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository imagineroRepository;

    private final ObrasRepository obrasRepository;

    private final PasswordEncoder passwordEncoder;

    public User createImaginero(CreateDtoUser getDtoImaginero, EnumSet<UserRole> roles){

        User imaginero = User.builder()
                .username(getDtoImaginero.getUsername())
                .password(passwordEncoder.encode(getDtoImaginero.getPassword()))
                .email(getDtoImaginero.getEmail())
                .name(getDtoImaginero.getName())
                .avatar(getDtoImaginero.getAvatar())
                .rol(roles)
                .build();

        return imagineroRepository.save(imaginero);
    }

    public User createImagineroWithUserRole(CreateDtoUser getDtoImaginero){

        return createImaginero(getDtoImaginero, EnumSet.of(UserRole.USER));
    }

    public User createImagineroWithAdminRole(CreateDtoUser getDtoImaginero){

        return createImaginero(getDtoImaginero, EnumSet.of(UserRole.ADMIN));
    }

    public List<User> findAll(){

        List<User> users = imagineroRepository.findAll();

        if (users.isEmpty()){

            throw new EmptyUserListException();
        }

        return users;
    }

    public Optional<User> findByUsername(String username){

        return  imagineroRepository.findByUsername(username);
    }

    public User edit(User imaginero){

        return imagineroRepository.findById(imaginero.getId())
                .map(im ->{
                    im.setName(imaginero.getName());
                    im.setUsername(im.getUsername());

                    return imagineroRepository.save(im);

                }).orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario con este id"));
    }

    public Optional<User> changePassword(UUID imagineroId, String newPassword){

        return imagineroRepository.findById(imagineroId)
                .map(im -> {

                    im.setPassword(passwordEncoder.encode(newPassword));

                    return imagineroRepository.save(im);

                }).or(() -> Optional.empty());
    }

    public Optional<User> editEmail(User imaginero){

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

    public void delete(User imaginero){

        deleteById(imaginero.getId());
    }

    public boolean passwordMatch(User imaginero, String clearPassword){

        return passwordEncoder.matches(clearPassword, imaginero.getPassword());
    }

    public Optional<User> findById(UUID id){

        return imagineroRepository.findById(id);
    }

//    public User findById(UUID id){
//
//        return imagineroRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }

    public List<User> findByName(String name){

        return imagineroRepository.findByName(name);
    }

    public User save(User imaginero){

        return imagineroRepository.save(imaginero);
    }

    public boolean userExists(String username) {

        return imagineroRepository.existsByUsername(username);
    }

    public boolean emailExists(String email){

        return imagineroRepository.existsByEmail(email);
    }

    //AGREGA A UNA OBRA COMO FAV Y LO METE EN UNA LISTA
    public User addFavorito(UUID userId, UUID obraId) {
        User user = imagineroRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Obras obra = obrasRepository.findById(obraId)
                .orElseThrow(() -> new ObrasNotFoundException(obraId));

        List<Obras> favoritos = user.getFavoritos();
        if (favoritos == null) {
            favoritos = new ArrayList<>();
        }
        favoritos.add(obra);
        user.setFavoritos(favoritos);

        imagineroRepository.save(user);

        return user;
    }

    //HACE LO MISMO QUE EL FIND DE ABAJO PERO SIN EL ENTITYFRAPH
    public List<Obras> getFavoritos(UUID userId) {

        User user = imagineroRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return user.getFavoritos();
    }

    //SE BUSCA A UN USUARIO POR ID Y SE MUESTRA SU LISTA DE OBRAS FAV
    public User findUserWithFavoritedObras(UUID userId) {

        return imagineroRepository.findByIdWithFavoritos(userId);
    }
    public void removeFavObra(UUID userId, UUID obraId){

        User user = imagineroRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.getFavoritos().removeIf(obras -> obras.getId().equals(obraId));

        imagineroRepository.save(user);
    }

    public void changeAvatar(UUID userId, String avatarFilename) {
        User user = imagineroRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        user.changeAvatar(avatarFilename);

        imagineroRepository.save(user);
    }
}
