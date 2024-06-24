package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.DomicilioFacadeImp;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;
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

    @GetMapping("/allPorUsuario/{idCliente}")
    public ResponseEntity<List<DomicilioDto>> getAllPorUsuario(@PathVariable Long idCliente) {
        return ResponseEntity.ok().body(facade.findAllByClienteId(idCliente));
    }

    @GetMapping("/domicilioSucursal/{idSucursal}")
    public ResponseEntity<DomicilioDto> getDomicilioSucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findDomicilioBySucursalId(idSucursal));
    }

    @PostMapping("/createDomicilio")
    public ResponseEntity<DomicilioDto> createDomicilio(@RequestBody DomicilioCreateDto domicilioDto) {
        return ResponseEntity.ok().body(facade.createDomicilio(domicilioDto));
    }
}
