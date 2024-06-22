package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoFacade extends BaseFacade <PedidoDto, PedidoDto, Long> {
    public PedidoDto create(PedidoCreateDto pedidoDto);

    List<PedidoDto> getPedidosEnPreparacion(Long idSucursal);
    public List<PedidoDto> findByEstadoPedido(EstadoPedido estado,Long idSucursal);

    public PedidoDto updateEstado(Long id, EstadoPedido estado) throws ServicioException;

    List<PedidoDto> findAllByUserId(Long userId, String email);
}
