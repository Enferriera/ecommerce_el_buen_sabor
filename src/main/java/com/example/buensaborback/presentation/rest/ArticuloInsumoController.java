package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloInsumoFacadeImp;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulosInsumos")
@CrossOrigin(origins="*")
public class ArticuloInsumoController  {

  @Autowired
  private ArticuloInsumoFacadeImp facade;
    @GetMapping("/buscar/elaborados")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarTrue() {
        //logger.info("INICIO GET ALL insumos PARA ELABORAR");
        return ResponseEntity.ok().body(facade.findByEsParaElaborarTrue());
    }

    @GetMapping("/buscar/noElaborados")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarFalse() {
        //logger.info("INICIO GET ALL insumos (gaseosas)");
        return ResponseEntity.ok().body(facade.findByEsParaElaborarFalse());
    }

    @GetMapping("/buscar/noElaborados/{categoria}")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarFalse(@PathVariable String categoria) {

        return ResponseEntity.ok().body(facade.getHabilitadosByCategoriaNoParaElaborar(categoria));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return ResponseEntity.ok().body(facade.getInsumoById(id));
    }



}
