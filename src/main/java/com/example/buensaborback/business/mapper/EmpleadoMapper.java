package com.example.buensaborback.business.mapper;


import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {SucursalMapper.class,UsuarioMapper.class})
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoDto> {

}
