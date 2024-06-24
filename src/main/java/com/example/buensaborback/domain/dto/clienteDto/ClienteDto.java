package com.example.buensaborback.domain.dto.clienteDto;

import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteDto;
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

    private UsuarioClienteDto usuarioCliente;
}
