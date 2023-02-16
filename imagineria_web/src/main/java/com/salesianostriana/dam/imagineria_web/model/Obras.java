package com.salesianostriana.dam.imagineria_web.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    private String titulo;

    private String img;

    private String estado; //Finalizado, en proceso, comprado o en venta

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;

    private String estilo;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Imaginero imaginero;

    public void eliminarCategoria(Categoria c) {

        c.getObras().remove(this);

        categoria = null;
    }


}