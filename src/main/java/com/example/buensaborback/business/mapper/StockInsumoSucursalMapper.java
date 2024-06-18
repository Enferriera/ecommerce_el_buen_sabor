package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.SucursalService;

import com.example.buensaborback.domain.dto.articuloInsumoDto.StockInsumoShortDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses={SucursalMapper.class, SucursalService.class,ArticuloInsumoMapper.class})
public interface StockInsumoSucursalMapper extends BaseMapper<StockInsumoSucursal, StockInsumoShortDto,StockInsumoShortDto>{


}
