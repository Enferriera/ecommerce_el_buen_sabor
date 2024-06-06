package com.example.buensaborback.business.facade.Imp;


import com.example.buensaborback.business.facade.MercadoPagoFacade;
import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.presentation.rest.MercadoPagoController;
import com.example.buensaborback.domain.entities.PreferenceMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoFacadeImpl implements MercadoPagoFacade {

    @Autowired
    private PedidoMapper pedidoMapper;
    @Override
    public PreferenceMP crearPreference(PedidoDto dto) {
        var pedido= pedidoMapper.toEntity(dto);
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }
}
