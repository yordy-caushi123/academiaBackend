package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.FormaPago;

public interface FormaPagoRepositorio extends JpaRepository<FormaPago, Long>{
	
}
