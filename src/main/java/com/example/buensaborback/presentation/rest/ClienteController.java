package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ClienteFacadeImpl;
import com.example.buensaborback.domain.dto.clienteDto.ClienteDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController extends BaseControllerImp<Cliente,ClienteDto, ClienteDto,Long, ClienteFacadeImpl> {
    public ClienteController(ClienteFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/getClientePorUserId/{userId}")
    public ResponseEntity<ClienteShortDto> getClienteByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(facade.obtenerClientePorUsuarioClienteId(userId));
    }
}
