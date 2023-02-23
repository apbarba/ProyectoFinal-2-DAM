package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    @NotEmpty(message = "{userDto.oldPassword.notempty}")
    private String oldPassword;

    @NotEmpty(message = "{userDto.newPassword.notempty}")
    private String newPassword;

    @NotEmpty(message = "{userDto.verifyNewPassword.notempty}")
    private String verifyNewPassword;
}
