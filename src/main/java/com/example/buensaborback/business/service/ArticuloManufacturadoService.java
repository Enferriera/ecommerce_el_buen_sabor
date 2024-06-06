package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado,Long> {

    public void changeHabilitado(Long id);


    public List<ArticuloManufacturado> getHabilitados();

    public Optional<ArticuloManufacturado> findByCodigo(String codigo);

    public List<ArticuloManufacturado> getHabilitadoByCategoria(String categoria);

    List<ArticuloManufacturado> findByHabilitadoTrueAndEsParaElaborarFalseAndCategoriaDenominacion(String categoria);


    List<ArticuloManufacturado> findHabilitadosBySucursal( Long sucursalId);

    List<ArticuloManufacturado> findHabilitadosBySucursalAndCategoria(Long sucursalId,  String categoriaNombre);

}
