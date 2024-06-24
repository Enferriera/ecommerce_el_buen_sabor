package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LocalidadMapper.class,ClienteMapper.class, ClienteService.class})
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto, DomicilioDto> {

        @Mapping(source="idClientes", target = "clientes", qualifiedByName = "getById")
        Domicilio toCreateEntity(DomicilioCreateDto dto);
}
