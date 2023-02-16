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

    @NotEmpty(message = "{userChange.oldPassword.notempty}")
    private String oldPassword;

    @NotEmpty(message = "{userChange.newPassword.notempty}")
    private String newPassword;

    @NotEmpty(message = "{userChange.verifyNewPassword}")
    private String verifyNewPassword;
}
