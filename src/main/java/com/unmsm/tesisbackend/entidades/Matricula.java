package com.unmsm.tesisbackend.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table ( name = " TA_MATRICULA " )

public class Matricula {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long idAlumno;
	private long idEscuela;
	private long idSede;
	private long idCiclo;
	private long idTurno;
	private long idModalidad;
	private long idProcedencia;
	private long idReferido;
	private long idMes;
	private long idEjecutivo;
	
	
	@Column(length = 150)
	private String observacion;
	private boolean estado;
	
	
	public Matricula() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(long idAlumno) {
		this.idAlumno = idAlumno;
	}


	public long getIdEscuela() {
		return idEscuela;
	}


	public void setIdEscuela(long idEscuela) {
		this.idEscuela = idEscuela;
	}


	public long getIdSede() {
		return idSede;
	}


	public void setIdSede(long idSede) {
		this.idSede = idSede;
	}


	public long getIdCiclo() {
		return idCiclo;
	}


	public void setIdCiclo(long idCiclo) {
		this.idCiclo = idCiclo;
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


	public long getIdProcedencia() {
		return idProcedencia;
	}


	public void setIdProcedencia(long idProcedencia) {
		this.idProcedencia = idProcedencia;
	}


	public long getIdReferido() {
		return idReferido;
	}


	public void setIdReferido(long idReferido) {
		this.idReferido = idReferido;
	}


	public long getIdMes() {
		return idMes;
	}


	public void setIdMes(long idMes) {
		this.idMes = idMes;
	}


	public long getIdEjecutivo() {
		return idEjecutivo;
	}


	public void setIdEjecutivo(long idEjecutivo) {
		this.idEjecutivo = idEjecutivo;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	

}
