package com.unmsm.tesisbackend.entidades;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table ( name = " TA_ASISTENCIA " )

public class Asistencia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long idMatricula;
	private long idTipoAsistencia;
	
	@Column(length = 1)
	private String asistencia;
	
	private Date fecha;
	private boolean estado;
	
	
	public Asistencia() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdMatricula() {
		return idMatricula;
	}


	public void setIdMatricula(long idMatricula) {
		this.idMatricula = idMatricula;
	}


	public long getIdTipoAsistencia() {
		return idTipoAsistencia;
	}


	public void setIdTipoAsistencia(long idTipoAsistencia) {
		this.idTipoAsistencia = idTipoAsistencia;
	}


	public String getAsistencia() {
		return asistencia;
	}


	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
