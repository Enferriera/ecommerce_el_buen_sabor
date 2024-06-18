package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;

import java.util.List;
import java.util.Optional;

public interface ArticuloManufacturadoFacade extends BaseFacade<ArticuloManufacturadoDto, ArticuloManufacturadoDto, Long> {

    public void changeHabilitado(Long id);



    public List<ArticuloManufacturadoDto> getHabilitadosByCategoria(String categoria);

    List<ArticuloManufacturadoDto> findHabilitadosBySucursal( Long sucursalId);

    List<ArticuloManufacturadoDto> findHabilitadosBySucursalAndCategoria( Long sucursalId,  String categoriaNombre);


    public Optional<ArticuloManufacturadoDto> getManufacturadoById(Long id);
}
