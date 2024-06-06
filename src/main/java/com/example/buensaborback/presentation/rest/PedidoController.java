package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.PedidoFacadeImpl;
import com.example.buensaborback.business.facade.MercadoPagoFacade;
import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.service.Imp.PedidoServiceImpl;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImp<Pedido, PedidoDto, PedidoDto, Long, PedidoFacadeImpl> {

    public PedidoController(PedidoFacadeImpl facade) {
        super(facade);
    }

    @Autowired
    private PedidoFacade pedidoFacade;
    @Autowired
    private MercadoPagoFacade preference;

    @PostMapping("/create_preference_mp")
    public ResponseEntity<PreferenceMP> crearPreferenciaMercadoPago(@RequestBody PedidoDto pedido){

        return ResponseEntity.ok().body(preference.crearPreference(pedido));
    }
}
