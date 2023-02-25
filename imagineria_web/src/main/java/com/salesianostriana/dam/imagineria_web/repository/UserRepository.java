package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    List<User> findByName(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


    @EntityGraph(attributePaths = {"favoritos"})
    @Query("""
            SELECT u 
            FROM User u
            WHERE u.id = :userId
            """)
    User findByIdWithFavoritos(@Param("userId") UUID userId);

//CONSULTA PARA ACTUALIZAR LA LISTA DE FAVORITOS DE UN USUARIO CUANDO CUANDO ELIMINEMOS

    @Query("""
            UPDATE User u 
            SET u.favoritos = :favoritos
            WHERE u.id = :userId
            """)
    void actualizarFavList(@Param("userId")UUID userId, @Param("favoritos")List<Obras> favoritos);
}
