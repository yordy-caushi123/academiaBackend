package com.unmsm.tesisbackend.entidades;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table ( name = " TA_EGRESO " )

public class Egreso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long idConceptoEgreso;
	private long idMes;

	
	@Column(length = 10)
	private String numeroOperacion;
	@Column(length = 150)
	private String observacion;
	
	private float total;
	
	private Date fechaPago;
	private boolean estado;
	
	public Egreso() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdConceptoEgreso() {
		return idConceptoEgreso;
	}

	public void setIdConceptoEgreso(long idConceptoEgreso) {
		this.idConceptoEgreso = idConceptoEgreso;
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
