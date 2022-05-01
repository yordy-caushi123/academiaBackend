package com.unmsm.tesisbackend.controladores;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/login")
public class LoginControlador {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping(value = "/obtenerUsuarios/{usuario}/{contrasenia}", produces = "application/json")
	private Object obtenerUsuario(@PathVariable String usuario, @PathVariable String contrasenia) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre_usuario = '"+usuario+"' AND contrasena_usuario = '"+contrasenia+"' AND estado_usuario=1;";
			Map<String, Object> rows = jdbcTemplate.queryForMap(sql);
			return rows;
		}catch(Exception e) {
			System.out.println("Error de conexion");
			return null;
		}
	}
	
	@GetMapping(value = "/ip", produces = "application/json")
	public Object obtenerIp(HttpServletRequest request) throws UnknownHostException {
		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
		       InetAddress inetAddress = InetAddress.getLocalHost();
		       String ipAddress = inetAddress.getHostAddress();
		       ip = ipAddress;
		}
        return ip.toString();
	}

}