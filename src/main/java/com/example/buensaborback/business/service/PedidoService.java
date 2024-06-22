package com.example.buensaborback.business.service;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PedidoService extends BaseService<Pedido, Long> {
    @Override
    public Pedido create(Pedido pedido);

    void validarStock(Pedido pedido);

    boolean aplicarDescuento(Pedido pedido);


    void calcularTiempoEstimado(Pedido pedido);

    List<Pedido> obtenerPedidosEnCocina(Long idSucursal);

    int contarCocineros(Long idSucursal);

    //Pedido cambiaEstado(EstadoPedido estado, Long id);

    List<Pedido> findByEstadoPedidoAndSucursalId(EstadoPedido estado,Long idSucursal);

    public Optional<Pedido> findById(Long id);

    public void revertirStock(Pedido pedido) throws RuntimeException;


    List<Pedido> findAllByUserId(Long userId,String email);
    public Pedido updateEstado(Long id, EstadoPedido estado) throws ServicioException;


}
