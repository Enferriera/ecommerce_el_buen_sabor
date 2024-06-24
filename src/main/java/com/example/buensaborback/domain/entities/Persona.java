package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
@Audited
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona extends Base {
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDate fechaNacimiento;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @NotAudited
    private ImagenPersona imagenPersona;

}
