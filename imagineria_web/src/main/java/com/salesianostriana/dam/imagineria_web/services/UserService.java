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


    public Optional<User> findByUsername(String username){

        return  imagineroRepository.findByUsername(username);
    }

    /**
     * Método que realiza el cambio de la contraseña actual por otra nueva
     * @param imagineroId, buscamos el id del usuario logeado
     * @param newPassword, pasamos la nueva contraseña con su validación
     *                     correspondiente para realizar el cambio
     * @return, nueva contraseña
     */
    public Optional<User> changePassword(UUID imagineroId, String newPassword){

        return imagineroRepository.findById(imagineroId)
                .map(im -> {

                    im.setPassword(passwordEncoder.encode(newPassword));

                    return imagineroRepository.save(im);

                }).or(() -> Optional.empty());
    }

    /**
     * Método que elimina al usuario
     * @param id, buscamos el id del usuario que queremos eliminar
     */
    public void deleteById(UUID id){

        if (imagineroRepository.existsById(id))
            imagineroRepository.deleteById(id);
    }

    /**
     * Método que elimina al usuario logeado, utilizanado el método del byId
     * @param imaginero
     */
    public void delete(User imaginero){

        deleteById(imaginero.getId());
    }

    public boolean passwordMatch(User imaginero, String clearPassword){

        return passwordEncoder.matches(clearPassword, imaginero.getPassword());
    }

    /**
     * Método que realiza la busqueda del id del usuario
     * @param id
     * @return
     */
    public Optional<User> findById(UUID id){

        return imagineroRepository.findById(id);
    }


    public User save(User imaginero){

        return imagineroRepository.save(imaginero);
    }

    public boolean userExists(String username) {

        return imagineroRepository.existsByUsername(username);
    }

    /**
     * Método que realiza el añadir una obra a la lista de favoritos
     * que tiene el usuario.
     * @param userId, necesitamos el id del usuario que se encuentra logueaso
     *                para tener su propia lista de favoirtos
     * @param obraId, necesitamos el id de la obra que guarda como favorito
     *                para mostrarla en su lista junto con los detalles de esta
     * @return
     */
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

    /**
     * Elimina una obra de la lista de favorito del usuario que se encuentra logueado
     * @param userId, necesitamos el id para saber la lista de favoritos que
     *                tiene el usuario logueaso
     * @param obraId, necesitamos saber el id de la obra para eliminarla, ya que
     *                debe de existir
     */
    public void removeFavObra(UUID userId, UUID obraId){

        User user = imagineroRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.getFavoritos().removeIf(obras -> obras.getId().equals(obraId));

        imagineroRepository.save(user);
    }

    /**
     * Método que realiza el cambio del avatar del usuario logueaso,
     * subimos un archivo nuevo para hacer el cambio
     * @param userId, necesitmos el id del usuario logueado para saber el
     *                avatar y asociarlo con el nuevo
     * @param avatarFilename, es el archivo que vamos a subir para realizar
     *                        el cambio
     */
    public void changeAvatar(UUID userId, String avatarFilename) {
        User user = imagineroRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        user.changeAvatar(avatarFilename);

        imagineroRepository.save(user);
    }
}
