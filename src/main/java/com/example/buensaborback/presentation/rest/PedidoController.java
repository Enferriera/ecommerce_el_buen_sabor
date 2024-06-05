package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.PedidoFacadeImpl;
import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.service.Imp.PedidoServiceImpl;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImp<Pedido, PedidoDto, PedidoDto, Long, PedidoFacadeImpl> {

    public PedidoController(PedidoFacadeImpl facade) {
        super(facade);
    }

    @Autowired
    private PedidoFacade pedidoFacade;


}
