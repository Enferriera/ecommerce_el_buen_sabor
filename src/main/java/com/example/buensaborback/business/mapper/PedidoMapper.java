package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.TipoEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {DetallePedidoMapper.class,EmpleadoMapper.class, ClienteService.class, DomicilioMapper.class, FormaPago.class, EstadoPedido.class, TipoEnvio.class, SucursalMapper.class, SucursalService.class, UsuarioClienteMapper.class, ClienteMapper.class, ArticuloMapper.class, ArticuloMapper.class})
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto, PedidoDto> {
@Mappings({
        @Mapping(source = "idCliente", target = "cliente", qualifiedByName = "getById"),
        @Mapping(source = "idSucursal", target = "sucursal", qualifiedByName = "getById"),
        @Mapping(source = "formaPago", target = "formaPago", qualifiedByName = "stringToFormaPago"),
        @Mapping(source = "tipoEnvio", target = "tipoEnvio", qualifiedByName = "stringToTipoEnvio"),
        @Mapping(source = "estadoPedido", target = "estadoPedido", qualifiedByName = "stringToEstado")
})

    Pedido toCreateEntity(PedidoCreateDto source);

    @Named("stringToFormaPago")
    default FormaPago stringToFormaPago(String formaPago) {
        return FormaPago.valueOf(formaPago);
    }

    default String formaPagoToString(FormaPago formaPago) {
        return formaPago.name();
    }

    @Named("stringToTipoEnvio")
    default TipoEnvio stringToTipoEnvio(String tipoEnvio) {
        return TipoEnvio.valueOf(tipoEnvio);
    }

    default String tipoEnvioToString(TipoEnvio tipoEnvio) {
        return tipoEnvio.name();
    }

    @Named("stringToEstado")
    default EstadoPedido stringToEstado(String estado) {
        return EstadoPedido.valueOf(estado);
    }

    default String EstadoToString(EstadoPedido estadoPedido) {
        return estadoPedido.name();
    }
}
