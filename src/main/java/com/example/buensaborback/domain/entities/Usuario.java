package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.enums.Rol;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class Usuario extends Base{
    private String auth0Id;
    private String username;
    private String email;
    private Rol rol;
}
