package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.ArticuloManufacturadoService;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ArticuloManufacturadoFacadeImp extends BaseFacadeImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoDto, Long> implements ArticuloManufacturadoFacade {
    public ArticuloManufacturadoFacadeImp(BaseService<ArticuloManufacturado, Long> baseService, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoDto> baseMapper) {
        super(baseService, baseMapper);

    }

    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    private ArticuloManufacturadoMapper articuloManufacturadoMapper;


    @Override
    public void changeHabilitado(Long id) {
        articuloManufacturadoService.changeHabilitado(id);
    }



    @Override
    public List<ArticuloManufacturadoDto> getHabilitadosByCategoria(String categoria) {
        return articuloManufacturadoMapper.toDTOsList(articuloManufacturadoService.getHabilitadoByCategoria(categoria));
    }



    @Override
    public List<ArticuloManufacturadoDto> findHabilitadosBySucursal(Long sucursalId) {
        return articuloManufacturadoMapper.toDTOsList(articuloManufacturadoService.findHabilitadosBySucursal(sucursalId));
    }

    @Override
    public List<ArticuloManufacturadoDto> findHabilitadosBySucursalAndCategoria(Long sucursalId, String categoriaNombre) {
        return articuloManufacturadoMapper.toDTOsList(articuloManufacturadoService.findHabilitadosBySucursalAndCategoria(sucursalId, categoriaNombre));
    }

    @Override
    public Optional<ArticuloManufacturadoDto> getManufacturadoById(Long id) {
        return Optional.ofNullable(articuloManufacturadoMapper.toDTO(articuloManufacturadoService.getManufacturadoById(id)));

    }
}
