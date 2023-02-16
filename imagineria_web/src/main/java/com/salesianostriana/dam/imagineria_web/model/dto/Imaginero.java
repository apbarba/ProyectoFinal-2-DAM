package com.salesianostriana.dam.imagineria_web.model.dto;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import lombok.*;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

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

    private int edad;

    private int localidad;

    @OneToMany
    private List<Obras> obras;
}
