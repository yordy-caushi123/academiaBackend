package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.TipoAsistencia;

public interface TipoAsistenciaRepositorio extends JpaRepository<TipoAsistencia, Long>{
	
}