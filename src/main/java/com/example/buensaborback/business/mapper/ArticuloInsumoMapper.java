package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.business.service.UnidadMedidaService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoShortDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses={ArticuloInsumoMapper.class, SucursalService.class, CategoriaService.class, CategoriaMapper.class, UnidadMedidaService.class, UnidadMedidaMapper.class, ArticuloMapper.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto>{
    @Mapping(source="denominacion", target = "denominacion")
    public ArticuloInsumoShortDto toShortDTO(ArticuloInsumo source);
}
