package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.PedidoService;
import com.example.buensaborback.domain.dto.pedidoDto.DetallePedidoDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Hibernate;


import java.util.Set;

@Service
public class PedidoFacadeImpl extends BaseFacadeImp<Pedido, PedidoDto, PedidoDto, Long> implements PedidoFacade {
    public PedidoFacadeImpl(BaseService<Pedido, Long> baseService, BaseMapper<Pedido, PedidoDto, PedidoDto> baseMapper, PedidoMapper pedidoMapper) {
        super(baseService, baseMapper);

    }
    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoService pedidoService;

@Transactional
public PedidoDto create(PedidoCreateDto pedidoDto) {
    System.out.println("antes de convertir " + pedidoDto.getFormaPago());
    Pedido pedido = pedidoMapper.toCreateEntity(pedidoDto);
    Set<DetallePedido> pedidosDetalles = pedido.getDetallePedidos();
    for(DetallePedido detalle: pedidosDetalles){
        if(detalle.getArticulo() instanceof ArticuloInsumo){
            System.out.println("Si es insumo");
            //Set<StockInsumoSucursal> insumosSucursal = ((ArticuloInsumo) detalle.getArticulo()).getStocksInsumo();
//            for(StockInsumoSucursal stock: insumosSucursal){
//                stock.getArticuloInsumo();
//            }
            ;
            ((ArticuloInsumo) detalle.getArticulo()).getStocksInsumo().forEach(stock -> {
                System.out.println("Stock actual antes: " + stock.getStockActual());
                if (stock.getStockActual() < stock.getStockMinimo()) {
                    throw new IllegalArgumentException("No hay suficiente stock");
                }
                stock.setStockActual(stock.getStockActual() - detalle.getCantidad());
                System.out.println("Stock actual después: " + stock.getStockActual());
            });
        }else{
            System.out.println("No es insumo");
            ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) detalle.getArticulo();

            // Primero, verificar si todos los insumos tienen suficiente stock
            boolean suficienteStock = articuloManufacturado.getArticuloManufacturadoDetalles().stream().allMatch(detalleManufacturado -> {
                int cantidadTotalInsumo = detalleManufacturado.getCantidad() * detalle.getCantidad();
                return detalleManufacturado.getArticuloInsumo().getStocksInsumo().stream().allMatch(stock ->
                        (stock.getStockActual() - cantidadTotalInsumo) >= stock.getStockMinimo()
                );
            });

            if (!suficienteStock) {
                throw new IllegalArgumentException("No hay suficiente stock en al menos uno de los insumos");
            }

            // Si todos los insumos tienen suficiente stock, procedemos a descontar el stock
            articuloManufacturado.getArticuloManufacturadoDetalles().forEach(detalleManufacturado -> {
                System.out.println("Detalle insumo: " + detalleManufacturado.getArticuloInsumo());
                System.out.println("Detalle insumo cantidad: " + detalleManufacturado.getCantidad());
                int cantidadTotalInsumo = detalleManufacturado.getCantidad() * detalle.getCantidad();
                detalleManufacturado.getArticuloInsumo().getStocksInsumo().forEach(stock -> {
                    System.out.println("Stock actual antes: " + stock.getStockActual());
                    stock.setStockActual(stock.getStockActual() - cantidadTotalInsumo);
                    System.out.println("Stock actual después: " + stock.getStockActual());
                });
            });
        }
        System.out.println(detalle.getArticulo());
    }
    return pedidoMapper.toDTO(pedidoService.create(pedido));
}



}
