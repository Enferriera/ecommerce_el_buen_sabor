package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.*;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.Rol;
import com.example.buensaborback.domain.enums.TipoEnvio;
import com.example.buensaborback.repositories.PedidoRepository;
import com.example.buensaborback.repositories.StockInsumoSucursalRepository;
import com.itextpdf.io.IOException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PedidoServiceImpl extends BaseServiceImp<Pedido, Long> implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private EmpleadoService empleadoService;


    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;

    @Autowired
    private StockInsumoSucursalRepository stockInsumoSucursalRepository;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Autowired
    private FacturaService facturaService;

    @Override
    @Transactional(rollbackOn = ServicioException.class)
    public Pedido create(Pedido pedido) {
        validarStock(pedido);
        pedido.calcularTotal();
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE_PAGO);
        aplicarDescuento(pedido);
        calcularTiempoEstimado(pedido);
        calcularTotalCosto(pedido);
        try {
            emailServiceImpl.sendAltaPedidoEmail(pedido.getCliente().getUsuarioCliente().getEmail(), pedido);
        } catch (Exception e) {
            log.error(e);
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public boolean aplicarDescuento(Pedido pedido) {
        if (pedido.getTipoEnvio() == TipoEnvio.TAKE_AWAY) {
            pedido.setTotal(pedido.getTotal() * 0.9); // Aplicar 10% de descuento
            return true;
        }
        return false;
    }

    @Override
    public void validarStock(Pedido pedido) throws RuntimeException {

        for (DetallePedido detalle : pedido.getDetallePedidos()) {

            if (detalle.getArticulo() != null) {
                // Caso de artículo
                Articulo articulo = detalle.getArticulo();
                int cantidadRequerida = detalle.getCantidad();

                if (articulo instanceof ArticuloInsumo) {
                    ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                    StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                    if (stock.getStockActual() < cantidadRequerida) {
                        throw new RuntimeException("Stock insuficiente para el artículo: " + insumo.getDenominacion());
                    }

                    // Decrementar el stock
                    stock.setStockActual(stock.getStockActual() - cantidadRequerida);
                    stockInsumoSucursalRepository.save(stock);

                } else if (articulo instanceof ArticuloManufacturado) {
                    ArticuloManufacturado manufacturado = articuloManufacturadoService.getById(articulo.getId());

                    for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                        ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                        int cantidadNecesaria = detalleManufacturado.getCantidad() * cantidadRequerida;
                        StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                        if (stock.getStockActual() < cantidadNecesaria) {
                            throw new RuntimeException("Stock insuficiente para el artículo: " + insumo.getDenominacion());
                        }

                        // Decrementar el stock
                        stock.setStockActual(stock.getStockActual() - cantidadNecesaria);
                        stockInsumoSucursalRepository.save(stock);
                    }
                } else {
                    throw new RuntimeException("Tipo de artículo desconocido: " + articulo.getClass().getName());
                }

            } else if (detalle.getPromocion() != null) {
                // Caso de promoción
                Promocion promocion = detalle.getPromocion();
                int cantidadRequerida = detalle.getCantidad();

                for (PromocionDetalle promocionDetalle : promocion.getPromocionDetalles()) {
                    Articulo articulo = promocionDetalle.getArticulo();
                    int cantidadNecesaria = promocionDetalle.getCantidad() * cantidadRequerida;
                    if (articulo instanceof ArticuloInsumo) {
                        ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                        StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                        if (stock.getStockActual() < cantidadNecesaria) {
                            throw new RuntimeException("Stock insuficiente para el artículo: " + insumo.getDenominacion());
                        }

                        // Decrementar el stock
                        stock.setStockActual(stock.getStockActual() - cantidadNecesaria);
                        stockInsumoSucursalRepository.save(stock);

                    } else if (articulo instanceof ArticuloManufacturado) {
                        ArticuloManufacturado manufacturado = articuloManufacturadoService.getById(articulo.getId());

                        for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                            ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                            int cantidadNecesariaManufacturado = detalleManufacturado.getCantidad() * cantidadRequerida;
                            StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                            if (stock.getStockActual() < cantidadNecesariaManufacturado) {
                                throw new RuntimeException("Stock insuficiente para el artículo: " + insumo.getDenominacion());
                            }

                            // Decrementar el stock
                            stock.setStockActual(stock.getStockActual() - cantidadNecesariaManufacturado);
                            stockInsumoSucursalRepository.save(stock);
                        }
                    } else {
                        throw new RuntimeException("Tipo de artículo desconocido: " + articulo.getClass().getName());
                    }
                }

            } else {
                throw new RuntimeException("El detalle del pedido no tiene artículo ni promoción.");
            }
        }
    }

    private StockInsumoSucursal obtenerStockInsumoSucursal(ArticuloInsumo insumo, Sucursal sucursal) {
        return sucursal.getStocksSucursal()
                .stream()
                .filter(stock -> stock.getArticuloInsumo().equals(insumo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró stock para el insumo: " + insumo.getDenominacion() + " en la sucursal: " + sucursal.getNombre()));
    }

    @Override
    public void calcularTiempoEstimado(Pedido pedido) {
        int tiempoArticulos = pedido.getDetallePedidos().stream()
                .mapToInt(detalle -> {
                    if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) detalle.getArticulo();
                        return articuloManufacturado.getTiempoEstimadoMinutos();
                    } else if (detalle.getArticulo() instanceof ArticuloInsumo) {
                        // No hay tiempo estimado para ArticuloInsumo, asumimos 0
                        return 0;
                    } else if (detalle.getPromocion() != null) {
                        // Si es una promoción, suma los tiempos estimados de los artículos en la promoción
                        Promocion promocion = detalle.getPromocion();
                        return promocion.getPromocionDetalles().stream()
                                .mapToInt(promocionDetalle -> {
                                    Articulo articulo = promocionDetalle.getArticulo();
                                    if (articulo instanceof ArticuloManufacturado) {
                                        return ((ArticuloManufacturado) articulo).getTiempoEstimadoMinutos();
                                    } else {
                                        return 0; // Asumimos 0 para ArticuloInsumo en promociones también
                                    }
                                })
                                .sum();
                    } else {
                        return 0;
                    }
                })
                .sum();

        int tiempoCocina = obtenerPedidosEnCocina(pedido.getSucursal().getId()).stream()
                .flatMap(p -> p.getDetallePedidos().stream())
                .mapToInt(detalle -> {
                    if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) detalle.getArticulo();
                        return articuloManufacturado.getTiempoEstimadoMinutos();
                    } else if (detalle.getArticulo() instanceof ArticuloInsumo) {
                        // No hay tiempo estimado para ArticuloInsumo, asumimos 0
                        return 0;
                    } else if (detalle.getPromocion() != null) {
                        // Si es una promoción, suma los tiempos estimados de los artículos en la promoción
                        Promocion promocion = detalle.getPromocion();
                        return promocion.getPromocionDetalles().stream()
                                .mapToInt(promocionDetalle -> {
                                    Articulo articulo = promocionDetalle.getArticulo();
                                    if (articulo instanceof ArticuloManufacturado) {
                                        return ((ArticuloManufacturado) articulo).getTiempoEstimadoMinutos();
                                    } else {
                                        return 0; // Asumimos 0 para ArticuloInsumo en promociones también
                                    }
                                })
                                .sum();
                    } else {
                        return 0;
                    }
                })
                .sum();

        int cantidadCocineros = contarCocineros(pedido.getSucursal().getId());
        // Si no hay cocineros disponibles, devuelve 0
        int tiempoCocinaPromedio = cantidadCocineros > 0 ? tiempoCocina / cantidadCocineros : 0;

        int tiempoDelivery = pedido.getTipoEnvio() == TipoEnvio.DELIVERY ? 10 : 0;
        pedido.setHoraEstimadaFinalizacion(LocalTime.now().plusMinutes(tiempoArticulos + tiempoCocinaPromedio + tiempoDelivery));
    }


    @Override
    public List<Pedido> obtenerPedidosEnCocina(Long idSucursal) {
        // Implementar la lógica para obtener los pedidos que están en preparación
        return pedidoRepository.findByEstadoPedidoAndSucursalId(EstadoPedido.PREPARACION, idSucursal);
    }

    @Override
    public int contarCocineros(Long idSucursal) {
        return empleadoService.countEmpleadosBySucursalAndRol(idSucursal,Rol.COCINERO);
    }


    private void calcularTotalCosto(Pedido pedido) {
        double totalCosto = 0.0;

        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            Articulo articulo = detalle.getArticulo();
            Promocion promocion = detalle.getPromocion();

            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                totalCosto += detalle.getCantidad() * insumo.getPrecioCompra();
            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;
                for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                    totalCosto += detalle.getCantidad() * detalleManufacturado.getCantidad() * insumo.getPrecioCompra();
                }
            } else if (promocion != null) {
                for (PromocionDetalle promocionDetalle : promocion.getPromocionDetalles()) {
                    Articulo articuloPromocion = promocionDetalle.getArticulo();
                    if (articuloPromocion instanceof ArticuloInsumo) {
                        ArticuloInsumo insumo = (ArticuloInsumo) articuloPromocion;
                        totalCosto += detalle.getCantidad() * promocionDetalle.getCantidad() * insumo.getPrecioCompra();
                    } else if (articuloPromocion instanceof ArticuloManufacturado) {
                        ArticuloManufacturado manufacturado = (ArticuloManufacturado) articuloPromocion;
                        for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                            ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                            totalCosto += detalle.getCantidad() * promocionDetalle.getCantidad() * detalleManufacturado.getCantidad() * insumo.getPrecioCompra();
                        }
                    } else {
                        throw new RuntimeException("Tipo de artículo desconocido en Promoción: " + articuloPromocion.getClass().getName());
                    }
                }
            } else {
                throw new RuntimeException("Tipo de artículo desconocido: " + articulo.getClass().getName());
            }
        }

        pedido.setTotalCosto(totalCosto);
    }


    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }


    @Override
    public void revertirStock(Pedido pedido) {
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            Articulo articulo = detalle.getArticulo();
            int cantidadRequerida = detalle.getCantidad();

            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                // Incrementar el stock
                stock.setStockActual(stock.getStockActual() + cantidadRequerida);
                stockInsumoSucursalRepository.save(stock);

            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;

                for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                    int cantidadNecesaria = detalleManufacturado.getCantidad() * cantidadRequerida;
                    StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                    // Incrementar el stock
                    stock.setStockActual(stock.getStockActual() + cantidadNecesaria);
                    stockInsumoSucursalRepository.save(stock);
                }
            } else if (detalle.getPromocion() != null) {
                Promocion promocion = detalle.getPromocion();

                for (PromocionDetalle promocionDetalle : promocion.getPromocionDetalles()) {
                    Articulo articuloPromocion = promocionDetalle.getArticulo();
                    if (articuloPromocion instanceof ArticuloInsumo) {
                        ArticuloInsumo insumo = (ArticuloInsumo) articuloPromocion;
                        StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                        // Incrementar el stock
                        stock.setStockActual(stock.getStockActual() + detalle.getCantidad() * promocionDetalle.getCantidad());
                        stockInsumoSucursalRepository.save(stock);
                    } else if (articuloPromocion instanceof ArticuloManufacturado) {
                        ArticuloManufacturado manufacturado = (ArticuloManufacturado) articuloPromocion;

                        for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                            ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                            StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                            // Incrementar el stock
                            stock.setStockActual(stock.getStockActual() + detalle.getCantidad() * promocionDetalle.getCantidad() * detalleManufacturado.getCantidad());
                            stockInsumoSucursalRepository.save(stock);
                        }
                    } else {
                        throw new RuntimeException("Tipo de artículo desconocido en Promoción: " + articuloPromocion.getClass().getName());
                    }
                }
            } else {
                throw new RuntimeException("Tipo de artículo desconocido: " + articulo.getClass().getName());
            }
        }
    }

    @Override
    @Transactional(rollbackOn = ServicioException.class)
    public Pedido updateEstado(Long id, EstadoPedido estado) throws ServicioException {
        Optional<Pedido> optionalPedido = baseRepository.findById(id);
        if (optionalPedido.isEmpty()) {
            throw new ServicioException("No se encontro el pedido con el id dado.");
        }
        if (estado == null) {
            throw new ServicioException("El estado nuevo no puede ser nulo.");
        }

        Pedido pedido = optionalPedido.get();
        EstadoPedido estadoAnterior = pedido.getEstadoPedido();
        switch (pedido.getFormaPago()) {
            case EFECTIVO -> pedido=updateEstadoEfectivo(estado, pedido);
            case MERCADO_PAGO -> pedido=updateEstadoMercadoPago(estado, pedido);
        }

        try {
            emailServiceImpl.sendEmail(
                    pedido.getCliente().getUsuarioCliente().getEmail(),
                    "Actualizacion pedido Buen Sabor",
                    "Tu pedido a cambiado de estado: " + estadoAnterior + " -> " + estado
            );
        } catch (Exception e) {
            log.error(e);
        }
        return pedido;
    }

    public Pedido updateEstadoEfectivo(EstadoPedido newEstado, Pedido pedido) throws ServicioException {
        if (!pedido.getEstadoPedido().isValidNextState(newEstado, FormaPago.EFECTIVO)) {
            throw new ServicioException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos en EFECTIVO");
        }

        //TODO: incluir estas nuevas validaciones en el enum (va a ser necesario pasar como parametro el tipo envio)
        if (pedido.getTipoEnvio().equals(TipoEnvio.TAKE_AWAY) && newEstado.equals(EstadoPedido.EN_CAMINO)) {
            throw new ServicioException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos TAKE_AWAY");
        }

        if (pedido.getTipoEnvio().equals(TipoEnvio.DELIVERY) && pedido.getEstadoPedido().equals(EstadoPedido.PENDIENTE_ENTREGA) && (newEstado.equals(EstadoPedido.PAGADO) || newEstado.equals(EstadoPedido.COMPLETADO))) {
            throw new ServicioException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos DELIVERY");
        }

        switch (newEstado) {
            case PAGADO, COMPLETADO -> {
                pedido.setEstadoPedido(EstadoPedido.COMPLETADO);
                if(pedido.getFactura() == null) {
                    facturaService.saveFacturaAfterPagoEfectivo(pedido);

                    try {
                        // creamos la factura  la factura PDF
                        byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                        // traemos el email del cliente
                        String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                        // Enviar el email con la factura
                        emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Factura de Pedido " + pedido.getId(), "Adjunto encontrará la factura de su pedido.", "factura_" + pedido.getId() + ".pdf");

                    } catch (IOException | java.io.IOException e) {
                        throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                    }
                }else{
                    pedidoRepository.save(pedido);
                }

            }
            case PENDIENTE_PAGO, PENDIENTE_ENTREGA, PREPARACION, EN_CAMINO -> {
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
            }
            case CANCELADO -> {
                this.revertirStock(pedido);
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
            }
            case NOTA_CREDITO -> {
                this.revertirStock(pedido);
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
                try {
                    // creamos la factura  la factura PDF
                    byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                    // traemos el email del cliente
                    String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                    // Enviar el email con la factura
                    emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Nota de credito de Pedido " + pedido.getId(), "Adjunto encontrará la nota de Credito de su pedido.", "factura_" + pedido.getId() + ".pdf");

                } catch (IOException | java.io.IOException e) {
                    throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                }

            }
            default -> throw new ServicioException("Invalid state");
        }
        return pedido;
    }

    public Pedido updateEstadoMercadoPago(EstadoPedido newEstado, Pedido pedido) throws ServicioException {
        if (!pedido.getEstadoPedido().isValidNextState(newEstado, FormaPago.MERCADO_PAGO)) {
            throw new ServicioException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos de MERCADO_PAGO");
        }

        //TODO: incluir estas nuevas validaciones en el enum (va a ser necesario pasar como parametro el tipo envio)
        if (pedido.getTipoEnvio().equals(TipoEnvio.TAKE_AWAY) && newEstado.equals(EstadoPedido.EN_CAMINO)) {
            throw new ServicioException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos TAKE_AWAY");
        }

        if (pedido.getTipoEnvio().equals(TipoEnvio.DELIVERY) && pedido.getEstadoPedido().equals(EstadoPedido.PENDIENTE_ENTREGA) && (newEstado.equals(EstadoPedido.PAGADO) || newEstado.equals(EstadoPedido.COMPLETADO))) {
            throw new ServicioException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos DELIVERY");
        }

        switch (newEstado) {
            case PAGADO, PREPARACION, PENDIENTE_ENTREGA, EN_CAMINO, COMPLETADO -> {
                pedido.setEstadoPedido(newEstado);

               if(pedido.getFactura() == null) {
                   facturaService.saveFacturaAfterPagoEfectivo(pedido);

                   try {
                       // creamos la factura  la factura PDF
                       byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                       // traemos el email del cliente
                       String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                       // Enviar el email con la factura
                       emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Factura de Pedido " + pedido.getId(), "Adjunto encontrará la factura de su pedido.", "factura_" + pedido.getId() + ".pdf");

                   } catch (IOException | java.io.IOException e) {
                       throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                   }
               }else{
                pedidoRepository.save(pedido);
               }


            }
            case NOTA_CREDITO -> {
                if (pedido.getEstadoPedido().equals(EstadoPedido.PAGADO)) {
                    this.revertirStock(pedido);
                }
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
                try {
                    // creamos la factura  la factura PDF
                    byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                    // traemos el email del cliente
                    String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                    // Enviar el email con la factura
                    emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Nota de credito de Pedido " + pedido.getId(), "Adjunto encontrará la nota de Credito de su pedido.", "factura_" + pedido.getId() + ".pdf");

                } catch (IOException | java.io.IOException e) {
                    throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                }
            }
            case PENDIENTE_PAGO ->
                    throw new ServicioException("Estado PENDIENTE_PAGO en pedidos de MERCADO_PAGO no implementado a traves de metodo updateEstado.");
            case CANCELADO ->{
                this.revertirStock(pedido);
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
            }

            default -> throw new ServicioException("Estado seleccionado invalido.");
        }

        return pedido;
    }

    @Override
    public  List<Pedido> findByEstadoPedidoAndSucursalId(EstadoPedido estado,Long idSucursal){
        return pedidoRepository.findByEstadoPedidoAndSucursalId(estado, idSucursal);
    }

    @Override
    public  List<Pedido> findAllByUserId(Long userId,String email){
        return pedidoRepository.findAllByUserId(userId, email);
    }
}
