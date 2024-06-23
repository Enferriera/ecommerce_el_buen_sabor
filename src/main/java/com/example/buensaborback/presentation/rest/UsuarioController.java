package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.UsuarioClienteFacadeImpl;
import com.example.buensaborback.business.facade.UsuarioClienteFacade;
import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController extends BaseControllerImp<UsuarioCliente, UsuarioClienteDto, UsuarioClienteDto, Long, UsuarioClienteFacadeImpl> {

    @Autowired
    UsuarioClienteFacade usuarioClienteFacade;
    public UsuarioController(UsuarioClienteFacadeImpl facade) {
        super(facade);
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioClienteDto> create(@RequestBody UsuarioClienteDto dto) {
        return ResponseEntity.ok().body(usuarioClienteFacade.create(dto));
    }
}
