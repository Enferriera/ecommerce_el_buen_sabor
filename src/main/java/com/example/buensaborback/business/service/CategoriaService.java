package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria,Long> {
    List<Categoria> findByEsInsumoTrue();
    List<Categoria> findByEsInsumoFalse();


    List<Categoria> findCategoriasBySucursalAndArticuloType( Long sucursalId);

    Categoria getCategoriaById(Long id);

}
