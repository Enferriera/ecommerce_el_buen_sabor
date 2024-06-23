package com.example.buensaborback.domain.dto.empleadoDto;

import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpleadoDto extends PersonaDto {

    private Set<PedidoDto> pedidos= new HashSet<>();


    private SucursalDto sucursal;

    private UsuarioDto usuario;
}
