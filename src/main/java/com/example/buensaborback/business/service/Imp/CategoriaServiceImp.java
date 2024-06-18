package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.mapper.SucursalMapper;
import com.example.buensaborback.business.mapper.SucursalMapperImpl;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImp extends BaseServiceImp<Categoria,Long> implements CategoriaService {

    @Autowired
    SucursalMapperImpl mapper;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    SucursalServiceImpl sucursalService;

    @Override
    public List<Categoria> findByEsInsumoTrue() {
        return categoriaRepository.findByEsInsumoTrue();
    }

    @Override
    public List<Categoria> findByEsInsumoFalse() {
        return categoriaRepository.findByEsInsumoFalse();
    }




    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.getById(id);
    }






    @Override
   public List<Categoria> findCategoriasBySucursalAndArticuloType( Long sucursalId){
        return categoriaRepository.findCategoriasBySucursalAndArticuloType(sucursalId);
    }


}
