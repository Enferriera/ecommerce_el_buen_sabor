package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/articulosManufacturados")
@CrossOrigin(origins="*")
public class ArticuloManufacturadoController {

    @Autowired
    private ArticuloManufacturadoFacadeImp facade;
    @GetMapping("/getHabilitados/{sucursalId}")
    public ResponseEntity<?> getHabilitadosBySucursal(@PathVariable Long sucursalId) {
        return ResponseEntity.ok().body(facade.findHabilitadosBySucursal(sucursalId));
    }

    @GetMapping("/getHabilitados/{categoria}/{sucursalId}")
    public ResponseEntity<?> getHabilitados(@PathVariable String categoria, @PathVariable Long sucursalId) {
        return ResponseEntity.ok().body(facade.findHabilitadosBySucursalAndCategoria(sucursalId,categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ArticuloManufacturadoDto>> getManufacturadoById(@PathVariable Long id){
        return ResponseEntity.ok().body(facade.getManufacturadoById(id));
    }

}
