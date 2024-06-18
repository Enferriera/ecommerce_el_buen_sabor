package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.ArticuloManufacturadoService;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticuloManufacturadoServiceImp extends BaseServiceImp<ArticuloManufacturado,Long> implements ArticuloManufacturadoService {

    @Autowired
    ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    ImagenArticuloRepository imagenArticuloRepository;

    @Autowired
    ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

    @Autowired
    ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;



    @Override
    public void changeHabilitado(Long id) {
        var articulo = getById(id);
        articulo.setHabilitado(!articulo.isHabilitado());
        baseRepository.save(articulo);
    }


    public Optional<ArticuloManufacturado> findByCodigo(String codigo) {
        return articuloManufacturadoRepository.findByCodigo(codigo);
    }

    @Override
    public List<ArticuloManufacturado> getHabilitadoByCategoria(String categoria) {
        return articuloManufacturadoRepository.findByHabilitadoTrueAndCategoriaDenominacion(categoria);
    }


    @Override
    @Transactional

   public List<ArticuloManufacturado> findHabilitadosBySucursal( Long sucursalId){
        return articuloManufacturadoRepository.findHabilitadosBySucursal(sucursalId);
    }

    @Override
    @Transactional
    public List<ArticuloManufacturado> findHabilitadosBySucursalAndCategoria(Long sucursalId, String categoriaNombre) {
        return articuloManufacturadoRepository.findHabilitadosBySucursalAndCategoria(sucursalId, categoriaNombre);
    }

    @Override
    public ArticuloManufacturado getManufacturadoById(Long id) {
        return articuloManufacturadoRepository.getById(id);
    }
}
