package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Asistencia;

public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Long>{
	
}