package com.unmsm.tesisbackend.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table ( name = " TA_TURNOMODALIDAD " )

public class TurnoModalidad {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long idTurno;
	private long idModalidad;
	private boolean estado;
	
	
	
	public TurnoModalidad() {
		
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getIdTurno() {
		return idTurno;
	}



	public void setIdTurno(long idTurno) {
		this.idTurno = idTurno;
	}



	public long getIdModalidad() {
		return idModalidad;
	}



	public void setIdModalidad(long idModalidad) {
		this.idModalidad = idModalidad;
	}



	public boolean isEstado() {
		return estado;
	}



	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
