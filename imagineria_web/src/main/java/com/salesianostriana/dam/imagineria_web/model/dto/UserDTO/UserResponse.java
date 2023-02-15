package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.imagineria_web.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserResponse {

    protected String id;

    protected String username;

    protected String email;

    protected String fullname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime createdAt;

    public static UserResponse fromUser(User imaginero){

        return UserResponse.builder()
                .id(imaginero.getId().toString())
                .username(imaginero.getUsername())
                .email(imaginero.getEmail())
                .fullname(imaginero.getFullname())
                .createdAt(imaginero.getCreatAt())
                .build();
    }
}
