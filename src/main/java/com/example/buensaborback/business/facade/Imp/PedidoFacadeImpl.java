package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.PedidoService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoFacadeImpl extends BaseFacadeImp<Pedido, PedidoDto, PedidoDto, Long> implements PedidoFacade {
    public PedidoFacadeImpl(BaseService<Pedido, Long> baseService, BaseMapper<Pedido, PedidoDto, PedidoDto> baseMapper, PedidoMapper pedidoMapper) {
        super(baseService, baseMapper);

    }

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoService pedidoService;

    @Override
    @Transactional
    public PedidoDto create(PedidoCreateDto pedidoDto) {

        Pedido pedido = pedidoMapper.toCreateEntity(pedidoDto);

        return pedidoMapper.toDTO(pedidoService.create(pedido));
    }

    @Override
    @Transactional
    public List<PedidoDto> getPedidosEnPreparacion(Long idSucursal) {
        List<Pedido> pedidosEnCocina = pedidoService.obtenerPedidosEnCocina(idSucursal);
        return pedidosEnCocina.stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PedidoDto> findByEstadoPedido(EstadoPedido estado,Long idSucursal) {
        List<Pedido> pedidos = pedidoService.findByEstadoPedidoAndSucursalId(estado,idSucursal);
        return pedidos.stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoDto updateEstado(Long id, EstadoPedido estado) throws ServicioException {
        return pedidoMapper.toDTO(pedidoService.updateEstado(id,estado));
    }

    @Override
    @Transactional
    public  List<PedidoDto> findAllByUserId(Long userId, String email){
        return pedidoMapper.toDTOsList(pedidoService.findAllByUserId(userId,email));
    }

}
