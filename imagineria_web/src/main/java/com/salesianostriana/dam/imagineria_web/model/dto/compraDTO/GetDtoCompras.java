package com.salesianostriana.dam.imagineria_web.model.dto.compraDTO;

import com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO.GetDtoObras;
import com.salesianostriana.dam.imagineria_web.model.dto.clienteDto.GetDtoCliente;
import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoCompras {

    private Long id;

    private GetDtoObras obras;

    private GetDtoCliente cliente;

    private int valoracion;

    private double precio;
}
