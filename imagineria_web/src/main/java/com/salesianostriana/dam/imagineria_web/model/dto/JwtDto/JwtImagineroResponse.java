package com.salesianostriana.dam.imagineria_web.model.dto.JwtDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.imagineria_web.model.Imaginero;
import com.salesianostriana.dam.imagineria_web.model.dto.ImagineroDTO.ImagineroResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtImagineroResponse extends ImagineroResponse {

    private String token;
    private String refreshToken;

    public JwtImagineroResponse(ImagineroResponse userResponse) {
        id = userResponse.getId();
        username = userResponse.getUsername();
        email = userResponse.getEmail();
        name = userResponse.getName();
        createdAt = userResponse.getCreatedAt();
    }

    public static JwtImagineroResponse of (Imaginero user, String token, String refreshToken) {
        JwtImagineroResponse result = new JwtImagineroResponse(ImagineroResponse.fromImaginero(user));
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        return result;

    }

}