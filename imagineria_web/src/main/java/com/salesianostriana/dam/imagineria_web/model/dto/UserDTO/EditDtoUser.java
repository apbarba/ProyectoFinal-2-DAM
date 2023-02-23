package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditDtoUser {

    @NotEmpty(message = "{userDto.name.notemty}")
    private String name;

    @NotBlank(message = "{createUser.username.notempty}")
    private String username;
}
