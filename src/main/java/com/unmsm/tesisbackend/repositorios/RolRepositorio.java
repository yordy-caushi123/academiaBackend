package com.unmsm.tesisbackend.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Rol;
import com.unmsm.tesisbackend.enumeraciones.RolNombre;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
	 Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
