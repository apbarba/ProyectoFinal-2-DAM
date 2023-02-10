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
public class Imaginero {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    private String email;

    private String username;
}
