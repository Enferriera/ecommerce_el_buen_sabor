package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;

import java.util.List;

public interface ArticuloInsumoFacade extends BaseFacade<ArticuloInsumoDto, ArticuloInsumoDto, Long> {
    public void changeHabilitado(Long id);


    public List<ArticuloInsumoDto> getHabilitadosByCategoriaNoParaElaborar(String categoria);

    public List<ArticuloInsumoDto> findByHabilitadoNoElaboradosPorIdSucursalYCategoria(Long sucursalId, String categoria);



    public ArticuloInsumoDto getInsumoById(Long id);

}
