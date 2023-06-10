package com.salesianostriana.dam.imagineria_web.files.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvatarChangeResponse {

    private String avatarFilename;

}
