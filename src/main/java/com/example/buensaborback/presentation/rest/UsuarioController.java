package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.UsuarioFacadeImpl;
import com.example.buensaborback.business.facade.UsuarioFacade;
import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController extends BaseControllerImp<Usuario, UsuarioDto, UsuarioDto, Long, UsuarioFacadeImpl> {

    @Autowired
    UsuarioFacade usuarioFacade;
    public UsuarioController(UsuarioFacadeImpl facade) {
        super(facade);
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto dto) {
        return ResponseEntity.ok().body(usuarioFacade.create(dto));
    }
}
