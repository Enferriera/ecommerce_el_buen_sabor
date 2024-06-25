package com.example.buensaborback.domain.dto;

import com.example.buensaborback.domain.dto.articuloDto.ArticuloDto;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloShortDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionShortDto;
import com.example.buensaborback.domain.entities.Articulo;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetalleFacturaDto extends BaseDto {
    private Integer cantidad;

    private Double subtotal;

    private ArticuloShortDto articulo;

    private PromocionShortDto promocion;
}
