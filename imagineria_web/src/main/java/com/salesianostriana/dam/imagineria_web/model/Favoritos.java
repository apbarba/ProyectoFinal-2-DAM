package com.salesianostriana.dam.imagineria_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Favoritos {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Obras obras;
}
