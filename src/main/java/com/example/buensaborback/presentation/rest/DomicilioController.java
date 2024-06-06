package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.DomicilioFacadeImp;
import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilios")
@CrossOrigin(origins="*")
public class DomicilioController extends BaseControllerImp<Domicilio, DomicilioDto,DomicilioDto,Long, DomicilioFacadeImp> {
    public DomicilioController(DomicilioFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/allPorUsuario/{id}")
    public ResponseEntity<List<DomicilioDto>> getAllPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok().body(facade.findAllByClienteId(id));
    }
}
