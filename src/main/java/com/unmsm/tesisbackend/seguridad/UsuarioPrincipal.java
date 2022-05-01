package com.unmsm.tesisbackend.seguridad;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.unmsm.tesisbackend.entidades.Usuario;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {

	private long idUsuario;	

    private int tipoUsuario;
    private int estadoUsuario;
    private int conexionUsuario;
    private String nombreUsuario;
    private String contrasenaUsuario;

  //Auditoria de creación
    private long idUsuCreacion;
    private Date fechaCreacion;
    private String ipCreacion;
    
    private Collection<? extends GrantedAuthority> authorities;
    
	public UsuarioPrincipal(long idUsuario, int tipoUsuario, int estadoUsuario,
			String nombreUsuario, String contrasenaUsuario, long idUsuCrea, Date fechaCreacion, String ipCreacion,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.idUsuario = idUsuario;
		this.tipoUsuario = tipoUsuario;
		this.estadoUsuario = estadoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasenaUsuario = contrasenaUsuario;
		this.idUsuCreacion = idUsuCrea;
		this.fechaCreacion = fechaCreacion;
		this.ipCreacion = ipCreacion;
		this.authorities = authorities;
	}

	public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getIdUsuario(), 
        							usuario.getTipoUsuario(),
        							usuario.getEstadoUsuario(),
        							usuario.getNombreUsuario(),
        							usuario.getContrasenaUsuario(),
        							usuario.getIdUsuCreacion(),
        							usuario.getFechaCreacion(),
        							usuario.getIpCreacion(),
        							authorities);
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

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		return this.contrasenaUsuario;
	}

	@Override
	public String getUsername() {
	return this.nombreUsuario;
	}
}