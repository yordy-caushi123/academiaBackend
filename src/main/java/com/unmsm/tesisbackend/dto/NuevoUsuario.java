package com.unmsm.tesisbackend.dto;

import java.util.Date;
import java.util.Set;

public class NuevoUsuario {
    private int tipoUsuario;
    private int estadoUsuario;
    private int conexionUsuario;
    private String nombreUsuario;
    private String contrasenaUsuario;
   
    //Auditoria de creación
    private long idUsuCreacion;
    private Date fechaCreacion;
    private String ipCreacion;

    private Set<String> roles;

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(int estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public int getConexionUsuario() {
		return conexionUsuario;
	}

	public void setConexionUsuario(int conexionUsuario) {
		this.conexionUsuario = conexionUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenaUsuario() {
		return contrasenaUsuario;
	}

	public void setContrasenaUsuario(String contrasenaUsuario) {
		this.contrasenaUsuario = contrasenaUsuario;
	}

	public long getIdUsuCreacion() {
		return idUsuCreacion;
	}

	public void setIdUsuCreacion(long idUsucreacion) {
		this.idUsuCreacion = idUsucreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIpCreacion() {
		return ipCreacion;
	}

	public void setIpCreacion(String ipCreacion) {
		this.ipCreacion = ipCreacion;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}