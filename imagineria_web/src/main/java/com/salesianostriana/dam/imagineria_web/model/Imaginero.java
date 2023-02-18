package com.salesianostriana.dam.imagineria_web.model;

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
public class Imaginero {

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

    private String name;

    private int edad;

    private String localidad;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Obras> obras;
}
