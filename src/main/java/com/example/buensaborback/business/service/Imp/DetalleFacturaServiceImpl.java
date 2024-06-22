package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.DetalleFacturaService;
import com.example.buensaborback.domain.entities.DetalleFactura;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.repositories.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleFacturaServiceImpl extends BaseServiceImp<DetalleFactura,Long> implements DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public void saveDetalleFromPedido(DetallePedido detallePedido, Factura factura) throws ServicioException {
        DetalleFactura detalleFactura = new DetalleFactura();


        detalleFactura.setCantidad(detallePedido.getCantidad());
        detalleFactura.setSubtotal(detallePedido.getSubTotal());
        detalleFactura.setArticulo(detallePedido.getArticulo());

        detalleFacturaRepository.save(detalleFactura);
    }
}
