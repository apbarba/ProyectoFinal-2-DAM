package com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ImagineroResponse {

    protected String id;

    protected String username;

    protected String email;

    protected String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;

    public static ImagineroResponse fromImaginero(Imaginero imaginero){

        return ImagineroResponse.builder()
                .id(imaginero.getId().toString())
                .username(imaginero.getUsername())
                .email(imaginero.getEmail())
                .name(imaginero.getName())
                .createdAt(imaginero.getCreatAt())
                .build();
    }
}
