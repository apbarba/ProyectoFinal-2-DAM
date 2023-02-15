package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    private String oldPassword;

    private String newPassword;

    private String verifyNewPassword;
}
