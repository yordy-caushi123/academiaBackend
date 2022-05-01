package com.unmsm.tesisbackend.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.unmsm.tesisbackend.enumeraciones.RolNombre;

@Entity
@Table ( name = " TA_ROLES" )
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getId() {
        return id_rol;
    }

    public void setId(Long id) {
        this.id_rol = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}