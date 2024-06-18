package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.ArticuloInsumoFacade;
import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.ArticuloInsumoService;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticuloInsumoFacadeImp extends BaseFacadeImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto, Long> implements ArticuloInsumoFacade {

    @Autowired
    ArticuloInsumoService articuloInsumoService;

    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;

    public ArticuloInsumoFacadeImp(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Transactional
    public List<ArticuloInsumoDto> findByEsParaElaborarTrue() {
        // Trae una página de entidades
        List<ArticuloInsumo> entities = articuloInsumoService.findByEsParaElaborarTrue();
        // Mapea las entidades a DTOs
        List<ArticuloInsumoDto> dtos = entities.stream()
                .map(articuloInsumoMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return dtos;
    }

    @Transactional
    public List<ArticuloInsumoDto> findByEsParaElaborarFalse() {
        // Trae una página de entidades
        List<ArticuloInsumo> entities = articuloInsumoService.findByEsParaElaborarFalse();
        // Mapea las entidades a DTOs
        List<ArticuloInsumoDto> dtos = entities.stream()
                .map(articuloInsumoMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return dtos;
    }



    @Override
    public List<ArticuloInsumoDto> getHabilitadosByCategoriaNoParaElaborar(String categoria) {
        return articuloInsumoMapper.toDTOsList(articuloInsumoService.getHabilitadosByCategoriaNoParaElaborar(categoria));
    }

    @Override
    public ArticuloInsumoDto getInsumoById(Long id) {
        return articuloInsumoMapper.toDTO(articuloInsumoService.getById(id));
    }

    @Override
    public void changeHabilitado(Long id) {
        articuloInsumoService.changeHabilitado(id);
    }

    @Override
    public List<ArticuloInsumoDto>  findByHabilitadoNoElaboradosPorIdSucursalYCategoria(Long sucursalId, String categoria){
        return articuloInsumoMapper.toDTOsList(articuloInsumoService.findByHabilitadoNoElaboradosPorIdSucursalYCategoria(sucursalId,categoria));
    }
}
