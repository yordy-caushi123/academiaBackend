package com.unmsm.tesisbackend.entidades;

import java.util.Date;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.Table ; 

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity 
@Table ( name = " TA_USUARIOS" )

public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	
    private int tipoUsuario;
    private int estadoUsuario;
   
    
    @Column ( length=50 )
    private String nombreUsuario;
    
    @Column(length = 1000)
    private String contrasenaUsuario;
    
    @NotNull
    @ManyToMany
    @JoinTable(name = "TA_USUROLES", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();
    
  //Auditoria de creación
    private long idUsuCreacion;
    private Date fechaCreacion;
    
    @Column(length = 20)
    private String ipCreacion;
    
    public Usuario(){
    }
    
	public Usuario(int tipoUsuario, int estadoUsuario, String nombreUsuario,
			String contrasenaUsuario, long idUsuCrea, Date fechaCreacion, String ipCreacion) {
		super();
		this.tipoUsuario = tipoUsuario;
		this.estadoUsuario = estadoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasenaUsuario = contrasenaUsuario;
		this.idUsuCreacion = idUsuCrea;
		this.fechaCreacion = fechaCreacion;
		this.ipCreacion = ipCreacion;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public long getIdUsuCreacion() {
		return idUsuCreacion;
	}

	public void setIdUsuCreacion(long idUsuCrea) {
		this.idUsuCreacion = idUsuCrea;
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

}