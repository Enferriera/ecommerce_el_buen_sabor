package com.example.buensaborback.domain.dto;

import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClienteDto extends PersonaDto {
    private Set<DomicilioDto> domicilios = new HashSet<>();

    private Set<PedidoDto> pedidos = new HashSet<>();
}
