package com.salesianostriana.dam.imagineria_web.model;

import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.GetDtoObras;
import lombok.*;

import javax.persistence.*;
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

    private String password;

    private String email;

    private String username;

    @OneToMany
    private List<Obras> obras;
}
