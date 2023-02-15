package com.salesianostriana.dam.imagineria_web.model.dto.JwtDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.model.dto.UserDTO.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtImagineroResponse extends UserResponse {

    private String token;
    private String refreshToken;

    public JwtImagineroResponse(UserResponse userResponse) {
        id = userResponse.getId();
        username = userResponse.getUsername();
        email = userResponse.getEmail();
        name = userResponse.getName();
        createdAt = userResponse.getCreatedAt();
    }

    public static JwtImagineroResponse of (User user, String token, String refreshToken) {
        JwtImagineroResponse result = new JwtImagineroResponse(UserResponse.fromUser(user));
        result.setToken(token);
        result.setRefreshToken(refreshToken);
        return result;

    }

}