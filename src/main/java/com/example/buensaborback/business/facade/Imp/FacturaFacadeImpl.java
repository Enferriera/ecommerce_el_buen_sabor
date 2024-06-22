package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.FacturaFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.FacturaService;
import com.example.buensaborback.domain.dto.FacturaDto;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FacturaFacadeImpl extends BaseFacadeImp<Factura, FacturaDto,FacturaDto,Long> implements FacturaFacade {
    public FacturaFacadeImpl(BaseService<Factura, Long> baseService, BaseMapper<Factura, FacturaDto, FacturaDto> baseMapper) {
        super(baseService, baseMapper);
    }
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FacturaService facturaService;

    @Override
    @Transactional
    public  byte[] generarFacturaPDF(Long pedidoId) throws IOException{
       Pedido pedido = pedidoRepository.getById(pedidoId);
        return facturaService.generarFacturaPDF(pedido);
    }
}
