package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import com.salesianostriana.dam.imagineria_web.model.Obras;
import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.EditDtoObras;
import com.salesianostriana.dam.imagineria_web.validation.annotation.FieldsValueMatch;
import com.salesianostriana.dam.imagineria_web.validation.annotation.StrongPassword;
import com.salesianostriana.dam.imagineria_web.validation.annotation.UniqueEmail;
import com.salesianostriana.dam.imagineria_web.validation.annotation.UniqueUsername;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password", fieldMatch = "verifyPassword",
                message = "{createUser.password.nomatch}"
        )
})
public class CreateDtoUser {

    @UniqueUsername(message = "{createUser.username.unique}")
    @NotEmpty(message = "{createUser.username.notempty}")
    private String username;

    @NotEmpty(message = "{userDto.name.notempty}")
    private String name;

    @UniqueEmail(message = "{createUser.email.unique}")
    @NotEmpty(message = "{createUser.email.notempty}")
    @Email(message = "{createUser.email.email}")
    private String email;

    @StrongPassword
    @NotEmpty(message = "{userDto.password.notempty}")
    private String password;

    @NotEmpty(message = "{userDto.verifyPassword.notempty}")
    private String verifyPassword;

    private String avatar;

    //@NotBlank(message = "{user.obras.list.blank}"
    //private List<EditDtoObras> obras;


}
