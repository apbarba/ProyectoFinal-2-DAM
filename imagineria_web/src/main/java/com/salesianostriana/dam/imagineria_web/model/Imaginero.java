package com.salesianostriana.dam.imagineria_web.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.salesianostriana.dam.imagineria_web.model.Obras;
import lombok.*;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "obras"})
/**
 * Clase imaginero con las variables que tiene cada uno
 */
public class Imaginero {

    /**
     * identificador  unico para cada imaginero existente
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
    @Column(columnDefinition = "uuid", name = "imaginero_id")
    private UUID id;

    /**
     * nombre de imaginero
     */
    private String name;

    /**
     * edad del imaginero
     */
    private int edad;

    /**
     * lugar de donde es el imaginero
     */
    private String localidad;

    /**
     * relacion con obra para saber las obras que ha hecho un imaginero
     */
    @OneToMany(mappedBy = "imaginero", fetch = FetchType.LAZY)
    private List<Obras> obras;
}
