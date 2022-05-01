package com.unmsm.tesisbackend.entidades;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table ( name = " TA_INGRESO " )

public class Ingreso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long idTipoIngreso;
	private long idAlumno;
	private long idFormaPago;
	private long idEntidadBancaria;
	private long idMes;

	
	@Column(length = 10)
	private String numeroOperacion;
	@Column(length = 150)
	private String observacion;
	
	private float total;
	private float cuenta;
	
	private Date fechaPago;
	private boolean estado;
	
public Ingreso() {
		
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdTipoIngreso() {
		return idTipoIngreso;
	}


	public void setIdTipoIngreso(long idTipoIngreso) {
		this.idTipoIngreso = idTipoIngreso;
	}


	public long getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(long idAlumno) {
		this.idAlumno = idAlumno;
	}


	public long getIdFormaPago() {
		return idFormaPago;
	}


	public void setIdFormaPago(long idFormaPago) {
		this.idFormaPago = idFormaPago;
	}


	public long getIdEntidadBancaria() {
		return idEntidadBancaria;
	}


	public void setIdEntidadBancaria(long idEntidadBancaria) {
		this.idEntidadBancaria = idEntidadBancaria;
	}


	public long getIdMes() {
		return idMes;
	}


	public void setIdMes(long idMes) {
		this.idMes = idMes;
	}


	public String getNumeroOperacion() {
		return numeroOperacion;
	}


	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public float getCuenta() {
		return cuenta;
	}


	public void setCuenta(float cuenta) {
		this.cuenta = cuenta;
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	
	

}
