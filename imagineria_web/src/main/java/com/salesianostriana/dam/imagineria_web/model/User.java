package com.salesianostriana.dam.imagineria_web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@EntityListeners(AuditingEntityListener.class)
@Getter
@Table(name = "imaginero_entity")
@NamedEntityGraph(
        name = "user.favoritos",
        attributeNodes = @NamedAttributeNode("favoritos")
)
/**
 * User va a representar a los usuarios de nuestra aplicación
 */
public class User implements UserDetails {

    /**
     * Será el identificador como único usuario, cada uno es único
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    private UUID id;

    /**
     * Nombre del usuario
     */
    private String name;
    /**
     * contraseña del usuario
     */
    private String password;

    /**
     * email del usuario
     */
    private String email;

    /**
     * username identificador unico del usuario
     */
    @NaturalId
    private String username;

    /**
     * comprobar el password por si no coinciden
     */
    private String verifyPassword;

    /**
     * avatar del usuario
     */
    private String avatar;

    /**
     * Relación con obras para marcar como favorito
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Obras> favoritos = new ArrayList<>();

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> rol;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rol.stream()
                .map(rol -> "ROLE_" + rol)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword(){

        return password;
    }

    @Override
    public String getUsername(){

        return username;
    }

    public void changeAvatar(String newAvatar) {
        this.avatar = newAvatar;
    }
}
