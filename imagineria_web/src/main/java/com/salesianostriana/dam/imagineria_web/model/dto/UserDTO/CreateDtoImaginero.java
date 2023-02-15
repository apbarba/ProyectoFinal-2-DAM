package com.salesianostriana.dam.imagineria_web.model.dto.UserDTO;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDtoImaginero {



    private String username;

    private String name;

    private String email;

    private String password;

   // private String verifyPassword;

    //private List<GetDtoObras> obras;


}
