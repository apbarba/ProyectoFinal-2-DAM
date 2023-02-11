package com.salesianostriana.dam.imagineria_web.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Obras {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double precio;

    private String img;

    private String estado; //Finalizado, en proceso, comprado o en venta

    private Date fecha;

    private String estilo;

    @ManyToMany
    private Categoria categoria;

    @ManyToOne
    private Imaginero imaginero;

    @OneToMany
    private List<Comentarios> comentarios;

    public void eliminarCategoria(Categoria c) {

        c.getImagenes().remove(this);

        categoria = null;
    }


}
