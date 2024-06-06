package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.CategoriaFacade;
import com.example.buensaborback.business.facade.Imp.CategoriaFacadeImp;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*")
public class CategoriaController  {

  @Autowired
  private CategoriaFacade facade;

    @GetMapping("/categoriasInsumos")
    public ResponseEntity<List<CategoriaGetDto>> getCategoriaInsumos() {
        //logger.info("INICIO GET ALL categorias de insumos");
        return ResponseEntity.ok(facade.getCategoriaInsumos());
    }

    @GetMapping("/categoriasManufacturados")
    public ResponseEntity<List<CategoriaGetDto>> getCategoriaManufacturados() {
        //logger.info("INICIO GET ALL categoria articulos manufacturados");
        return ResponseEntity.ok(facade.getCategoriaManufacturados());
    }


    @GetMapping("/buscar/ecommerce/{sucursalId}")
    public ResponseEntity<List<CategoriaGetDto>> findBySucursalId(@PathVariable Long sucursalId) {
        //logger.info("INICIO GET ALL categorias de insumos");
        return ResponseEntity.ok(facade.findCategoriasBySucursalAndArticuloType(sucursalId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaGetDto> getCategodiaById(@PathVariable Long id){
        return ResponseEntity.ok().body(facade.getCategoriaById(id));

    }


}
