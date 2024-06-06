package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloManufacturadoFacade extends BaseFacade<ArticuloManufacturadoDto, ArticuloManufacturadoDto, Long> {
public ArticuloManufacturadoDto create(ArticuloManufacturadoCreateDto articuloManufacturadoCreateDto);
    public void changeHabilitado(Long id);



    public List<ArticuloManufacturadoDto> getHabilitadosByCategoria(String categoria);

    List<ArticuloManufacturadoDto> findHabilitadosBySucursal( Long sucursalId);

    List<ArticuloManufacturadoDto> findHabilitadosBySucursalAndCategoria( Long sucursalId,  String categoriaNombre);

}
