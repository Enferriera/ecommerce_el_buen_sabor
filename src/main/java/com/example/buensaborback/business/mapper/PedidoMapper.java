package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.business.service.EmpleadoService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.TipoEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {DetallePedidoMapper.class,EmpleadoMapper.class, ClienteService.class, DomicilioMapper.class, FormaPago.class, Estado.class, TipoEnvio.class, SucursalMapper.class, SucursalService.class, UsuarioMapper.class, ClienteMapper.class})
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto, PedidoDto> {
@Mappings({
        @Mapping(source = "idCliente", target = "cliente", qualifiedByName = "getById"),
        @Mapping(source = "idSucursal", target = "sucursal", qualifiedByName = "getById"),
})

    Pedido toCreateEntity(PedidoCreateDto source);
}
