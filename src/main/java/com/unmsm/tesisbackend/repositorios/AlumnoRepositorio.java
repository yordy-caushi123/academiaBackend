package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Alumno;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long>{
	
}