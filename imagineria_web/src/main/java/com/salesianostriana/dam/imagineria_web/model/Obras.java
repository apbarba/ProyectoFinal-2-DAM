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
public class Obras {

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

    private String name;

    private double precio;

    private String titulo;

    private String img;

    private String estado; //Finalizado, en proceso, comprado o en venta

   // @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecha;

    private String estilo;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("obras")
    @JsonBackReference
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imaginero_id")
    @JsonIgnoreProperties("obras")
    private Imaginero imaginero;

    //public void eliminarCategoria(Categoria c) {

     //   c.getObras().remove(this);

     //   categoria = null;
   // }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void addUser(User u){

        this.user = u;
        user.getFavoritos().add(this);

    }

    public void removeUser(User u){

        this.user = null;
        user.getFavoritos().remove(this);
    }


}
