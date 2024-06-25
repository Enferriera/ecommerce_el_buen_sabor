package com.example.buensaborback.domain.dto.pedidoDto;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetallePedidoCreateDto extends BaseDto {
    private int cantidad;
    private Long idArticulo;
    private Long idPromocion;
}
