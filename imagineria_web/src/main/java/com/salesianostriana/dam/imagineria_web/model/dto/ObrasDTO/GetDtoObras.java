package com.salesianostriana.dam.imagineria_web.model.dto.ObrasDTO;

import com.salesianostriana.dam.imagineria_web.model.dto.comentariosDTO.GetDtoComentarios;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class GetDtoObras {

    private Long id;

    private String nombre;

    private String estilo;

    private Date fecha;

    private double precio;

    private String estado;

    private String img;

    private List<GetDtoComentarios> comentarios;
}
