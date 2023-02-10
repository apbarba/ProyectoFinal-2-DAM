package com.salesianostriana.dam.imagineria_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;

    private String nameUser;

    private String password;

    private String emailUser;

    private boolean favorito;
}
