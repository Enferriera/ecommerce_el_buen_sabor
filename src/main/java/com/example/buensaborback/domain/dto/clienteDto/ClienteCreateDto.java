package com.example.buensaborback.domain.dto.clienteDto;

import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClienteCreateDto extends PersonaDto {

    private UsuarioClienteDto usuarioCliente;

}
