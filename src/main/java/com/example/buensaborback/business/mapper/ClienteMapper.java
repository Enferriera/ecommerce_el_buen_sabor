package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.clienteDto.ClienteCreateDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class,UsuarioClienteMapper.class,PersonaMapper.class})
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto, ClienteDto> {

    ClienteShortDto toShortDto(Cliente cliente);

    Cliente toCreateEntity(ClienteCreateDto dto);

}
