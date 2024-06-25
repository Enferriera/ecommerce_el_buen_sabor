package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ArticuloService;
import com.example.buensaborback.business.service.PromocionService;
import com.example.buensaborback.domain.dto.pedidoDto.DetallePedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.DetallePedidoDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Promocion;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, ArticuloService.class,PromocionMapper.class, PromocionService.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoDto> {
    @Mappings({
            @Mapping(target = "articulo", source = "idArticulo", qualifiedByName = "mapIdToArticulo"),
            @Mapping(target = "promocion", source = "idPromocion", qualifiedByName = "mapIdToPromocion")
    })
    DetallePedido toCreateEntity(DetallePedidoCreateDto source);




}
