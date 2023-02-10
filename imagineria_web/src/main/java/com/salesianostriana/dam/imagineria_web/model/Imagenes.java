package com.salesianostriana.dam.imagineria_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Imagenes {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double precio;

    private String img;

    private String estado; //Finalizado, en proceso, comprado o en venta

    @ManyToMany
    private Categoria categoria;

    public void eliminarCategoria(Categoria c) {

        c.getImagenes().remove(this);

        categoria = null;
    }


}
