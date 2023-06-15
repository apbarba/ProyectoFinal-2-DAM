package com.salesianostriana.dam.imagineria_web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "obras")
/**
 * clase modelo de obras, de las que contenga nuestra aplicación
 */
public class Obras {

    /**
     * Identificador único para cada obra
     */
    @Id
    @GeneratedValue
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
    @Column(columnDefinition = "uuid", name = "obras_id")
    private UUID id;

    /**
     * Nombre de la obra
     */
    private String name;

    /**
     * precio de la obra
     */
    private double precio;

    /**
     * titulo descriptivo de la obra
     */
    private String titulo;

    /**
     * Una imagen de la obra
     */
    private String img;

    /**
     * Estado de como se encuentra la obra, adquirida, desaparecida...
     */
    private String estado;

    /**
     * Fecha de creación de la obra
     */
   // @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;

    /**
     * Estilo de la obra
     */
    private String estilo;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Relación con categoria, ya que cada obra está en una categoria
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("obras")
    @JsonBackReference
    private Categoria categoria;

    /**
     * Relación con imaginero para saber que imaginero es quién la realizó
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imaginero_id")
   // @JsonIgnoreProperties("obras")
    private Imaginero imaginero;

    private String nombreImaginero;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void addUser(User u){

        this.user = u;
        user.getFavoritos().add(this);

    }

}
