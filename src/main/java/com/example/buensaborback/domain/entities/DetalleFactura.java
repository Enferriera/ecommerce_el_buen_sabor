package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Audited
public class DetalleFactura extends Base {

    private Integer cantidad;

    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "id_promocion")
    private Promocion promocion;



}
