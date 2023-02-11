package com.salesianostriana.dam.imagineria_web.model.dto.comentariosDTO;

import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.GetDtoObras;
import com.salesianostriana.dam.imagineria_web.model.dto.clienteDto.GetDtoCliente;
import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoComentarios {

    private Long id;

    private String texto;

    private GetDtoObras obras;

    private GetDtoCliente cliente;
}
