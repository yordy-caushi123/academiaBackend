package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Modalidad;

public interface ModalidadRepositorio extends JpaRepository<Modalidad, Long>{
	
}