package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Imp.PedidoFacadeImpl;
import com.example.buensaborback.business.facade.MercadoPagoFacade;
import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.PreferenceMP;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins="*")
public class PedidoController extends BaseControllerImp<Pedido, PedidoDto, PedidoDto, Long, PedidoFacadeImpl> {

    public PedidoController(PedidoFacadeImpl facade) {
        super(facade);
    }

    @Autowired
    private PedidoFacade pedidoFacade;
    @Autowired
    private MercadoPagoFacade preference;

    @PostMapping("/create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody PedidoDto pedido){


        return preference.crearPreference(pedido);
    }



    @PostMapping("/create")
    public ResponseEntity<PedidoDto> create(@RequestBody PedidoCreateDto dto) {
        return ResponseEntity.ok().body(pedidoFacade.create(dto));
    }

    @PutMapping("/cambiaEstado/{id}")
    public ResponseEntity<?> cambiaEstado(@RequestParam String estadoPedido, @PathVariable Long id ) {
       try {

           EstadoPedido estadoPedidoValido = EstadoPedido.valueOf(estadoPedido.toUpperCase());

           return ResponseEntity.ok(facade.updateEstado(id, estadoPedidoValido));
       }catch (ServicioException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }


    @GetMapping("/getAllPedidoPorUserId/{id}/{email}")
    public ResponseEntity<List<PedidoDto>> getAllPedidoPorUserId(@PathVariable Long id, @PathVariable String email) {
        return ResponseEntity.ok(facade.findAllByUserId(id,email));
    }


}
