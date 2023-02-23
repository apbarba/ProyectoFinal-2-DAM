package com.salesianostriana.dam.imagineria_web.repository;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @EntityGraph(attributePaths = {"favoritos"})
    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    List<User> findByName(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


    //CONSULTA PARA ACTUALIZAR LA LISTA DE FAVORITOS DE UN USUARIO CUANDO CUANDO ELIMINEMOS
/*
    @Query("""
            UPDATE User u 
            SET u.favoritos = :favoritos
            WHERE u.id = :userId
            """)
    void actualizarFavList(@Param("userId")UUID userId, @Param("favoritos")List<Obras> favoritos);


    @Query("""
            UPDATE User u
            SET u.favoritos = null 
            WHERE u.id = :userId
            """)
    void clearFavList(@Param("userId")UUID userId);


    @Query("""
            DELETE FROM obras o
            WHERE o.id = :obrasId
            """)
    void deleteFavObra(@Param("obraId")UUID obraId);

    @Query("""
            SELECT u 
            FROM User u LEFT JOIN FETCH u.favoritos 
            WHERE u.id = :userId""")
    Optional<User> findByIdWithFavoritos(@Param("userId") UUID userId);
*/
}
