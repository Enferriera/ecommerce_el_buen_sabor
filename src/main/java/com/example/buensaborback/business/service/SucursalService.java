package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SucursalService  extends BaseService<Sucursal, Long> {

    List<Categoria> findCategoriasBySucursalId(Long sucursalId);
    boolean existsSucursalByEsCasaMatriz(Long empresaId);
    List<Sucursal> findAllByEmpresaId(Long id);
}


