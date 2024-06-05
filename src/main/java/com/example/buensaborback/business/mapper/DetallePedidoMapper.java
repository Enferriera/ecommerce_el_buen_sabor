package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ArticuloService;
import com.example.buensaborback.domain.dto.pedidoDto.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.DetallePedidoDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, ArticuloService.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoDto> {

    @Mapping(source="idArticulo",target="articulo",qualifiedByName = "getById")
    DetallePedido toCreateEntity(DetallePedidoCreateDto source);
}
