package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.validation.annotation.StrongPassword;
import com.salesianostriana.dam.imagineria_web.validation.annotation.UniqueEmail;
import com.salesianostriana.dam.imagineria_web.validation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDtoUser {

    @UniqueUsername(message = "{user.username.unique}")
    @NotBlank(message = "{user.username.blank}")
    private String username;

    @NotBlank(message = "{user.name.blank}")
    private String name;

    @UniqueEmail(message = "{user.email.unique}")
    @NotBlank(message = "{user.email.blank}")
    private String email;

    @StrongPassword
    @NotEmpty(message = "{user.password.notempty}")
    private String password;

    @NotEmpty(message = "{user.verifyPassword.notempty}")
    private String verifyPassword;

    //@NotBlank(message = "{user.obras.list.blank}"
    //private List<EditDtoObras> obras;


}
