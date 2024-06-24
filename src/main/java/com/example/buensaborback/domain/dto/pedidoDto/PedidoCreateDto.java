package com.example.buensaborback.domain.dto.pedidoDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoCreateDto extends BaseDto {
    private String formaPago;
    private String tipoEnvio;
    private String estadoPedido;
    private LocalDate fechaPedido=LocalDate.now();
    private DomicilioDto domicilio;
    private Long idSucursal;
    private Long idCliente;
    private Set<DetallePedidoCreateDto> detallePedidos= new HashSet<>();
}
