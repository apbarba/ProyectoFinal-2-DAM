package com.salesianostriana.dam.imagineria_web.model.dto.clienteDto;

import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.GetDtoObras;
import com.salesianostriana.dam.imagineria_web.model.dto.comentariosDTO.GetDtoComentarios;
import com.salesianostriana.dam.imagineria_web.model.dto.compraDTO.GetDtoCompras;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoCliente {

    private Long id;

    private String name;

    private int edad;

    private List<GetDtoCompras> compras;

    private List<GetDtoObras> obrasFav;

    private List<GetDtoComentarios> comentarios;
}
