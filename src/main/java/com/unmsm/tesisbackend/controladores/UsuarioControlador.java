package com.unmsm.tesisbackend.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.tesisbackend.entidades.Usuario;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.UsuarioRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/usuario")
public class UsuarioControlador {
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	JdbcTemplate jdbctemplate;

    @GetMapping("/listar")
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    /*@GetMapping("/listarCoincidencias/{buscar}")
    private List<Usuario> usuariosCoincidencias(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
    	String query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1\r\n"+
    			"AND ( f.paterno_funcionario like '%"+cadena+"%' OR f.materno_funcionario like '%"+cadena+"%' OR f.nombres_funcionario like '%"+cadena+"%')\r\n"+
    			"ORDER BY f.paterno_funcionario";
				
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Usuario objeto = new Usuario();
			
			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setCodigo_persona((String) row.get("codigo_persona"));
			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));
			
			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
			objeto.setNombreUsuario((String) row.get("paterno_funcionario")+" "+(String) row.get("materno_funcionario")+", "+(String) row.get("nombres_funcionario"));
			objeto.setContrasena_usuario((String) row.get("procedencia_usuario_funcionario"));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			
			usuarios.add(objeto);
		}
		return usuarios;
	}
    
    @GetMapping("/listarCoincidenciasPorTipo/{buscar}/{tipo}")
    private List<Usuario> usuariosCoincidenciasPorTipo(@PathVariable(value = "buscar") String cadena, @PathVariable(value = "tipo") int tipo)
	        throws ResourceNotFoundException {
		String query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
				"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
				"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
				"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 AND u.tipo_usuario="+tipo
				 + " AND ( f.paterno_funcionario like '%"+cadena+"%' OR f.materno_funcionario like '%"+cadena+"%' OR f.nombres_funcionario like '%"+cadena+"%')"
				 + " ORDER BY f.paterno_funcionario";

    	List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Usuario objeto = new Usuario();
			
			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setCodigo_persona((String) row.get("codigo_persona"));
			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));
			
			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
			objeto.setNombreUsuario((String) row.get("paterno_funcionario")+" "+(String) row.get("materno_funcionario")+", "+(String) row.get("nombres_funcionario"));
			objeto.setContrasena_usuario((String) row.get("procedencia_usuario_funcionario"));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			
			usuarios.add(objeto);
		}
		return usuarios;
	}
    
    @GetMapping("/listarPorTipo/{buscar}")
    private List<Usuario> usuariosPorTipo(@PathVariable(value = "buscar") int tipo)
	        throws ResourceNotFoundException {
    	String query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 AND u.tipo_usuario="+tipo
    			+ " ORDER BY f.paterno_funcionario";

		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Usuario objeto = new Usuario();
			
			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setCodigo_persona((String) row.get("codigo_persona"));
			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));
			
			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
			objeto.setNombreUsuario((String) row.get("paterno_funcionario")+" "+(String) row.get("materno_funcionario")+", "+(String) row.get("nombres_funcionario"));
			objeto.setContrasena_usuario((String) row.get("procedencia_usuario_funcionario"));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			
			usuarios.add(objeto);
		}
		return usuarios;
	}
    
    @GetMapping("/listarCoincidenciasNombreUsuario/{buscar}")
    private List<Usuario> usuariosCoincidenciasNombreUsuario(@PathVariable(value = "buscar") String cadena)
	        throws ResourceNotFoundException {
	
    	String query = "SELECT * FROM ta_acn_usuarios WHERE nombre_usuario like '"+cadena+"%'";
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Usuario objeto = new Usuario();
			
			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setCodigo_persona((String) row.get("codigo_persona"));
			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));

			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
			objeto.setNombreUsuario((String) row.get("nombre_usuario"));
			objeto.setContrasena_usuario((String) row.get("contrasena_usuario"));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			
			usuarios.add(objeto);
		}
		return usuarios;
	}
    
    @GetMapping(value = "/recuperarPorCodigoFuncionario/{codigo}", produces = "application/json")
	private Usuario getUsuarioByCodigoFuncionario(@PathVariable String codigo)  {

		try {
			String sql = "SELECT * FROM ta_acn_usuarios WHERE codigo_funcionario = '"+codigo+"' ORDER BY fecha_creacion DESC";
			List<Usuario> usuarios = new ArrayList<Usuario>();
			List<Map<String, Object>> rows = jdbctemplate.queryForList(sql);
			
			for(Map row: rows) {
				Usuario objeto = new Usuario();
				
				objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
				objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
				objeto.setCodigo_persona((String) row.get("codigo_persona"));
				objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));

				objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
				objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
				objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
				objeto.setNombreUsuario((String) row.get("nombre_usuario"));
				objeto.setContrasena_usuario((String) row.get("contrasena_usuario"));
				
				objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
				objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
				objeto.setIp_creacion((String) row.get("ip_creacion"));
				
				usuarios.add(objeto);
			}
			if(usuarios.size()==0) {
				return null;
			}
			else {
				return usuarios.get(0);
			}
			
		}catch(Exception e) {
			return null;
		}
	}
    
    @GetMapping("/listarActivos")
	private List<Usuario> usuariosActivos() throws ResourceNotFoundException {
    	String query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 ORDER BY f.paterno_funcionario";
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
		
		for(Map row: rows) {
			Usuario objeto = new Usuario();
			
			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
			objeto.setCodigo_usuario((String) row.get("codigo_usuario"));
			objeto.setCodigo_persona((String) row.get("codigo_persona"));
			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));
			
			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
			objeto.setNombreUsuario((String) row.get("paterno_funcionario")+" "+(String) row.get("materno_funcionario")+", "+(String) row.get("nombres_funcionario"));
			objeto.setContrasena_usuario((String) row.get("procedencia_usuario_funcionario"));
			
			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
			objeto.setIp_creacion((String) row.get("ip_creacion"));
			
			//objeto.setRoles(null);
			
			usuarios.add(objeto);
		}

		return usuarios;
	}*/
    
    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") long id_usuario)
        throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findById(id_usuario)
          .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario));
        return ResponseEntity.ok().body(usuario);
    }
    
    @GetMapping("/recuperarPorNombreUsuario/{username}")
    public ResponseEntity<Usuario> getUsuarioByUserName(@PathVariable(value = "username") String username)
        throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findByNombreUsuario(username)
          .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + username));
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/registrar")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
    	Date fecha = new Date();
    	usuario.setFechaCreacion(fecha);
        return usuarioRepositorio.save(usuario);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable(value = "id") Long id_usuario,
         @Valid @RequestBody Usuario usuario_detalle) throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findById(id_usuario)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario));

        usuario.setTipoUsuario(usuario_detalle.getTipoUsuario());
        usuario.setEstadoUsuario(usuario_detalle.getEstadoUsuario());
        usuario.setContrasenaUsuario(usuario_detalle.getContrasenaUsuario());        
        usuario.setIdUsuCreacion(usuario_detalle.getIdUsuCreacion());
        usuario.setFechaCreacion(usuario_detalle.getFechaCreacion());
        usuario.setIpCreacion(usuario_detalle.getIpCreacion());

        final Usuario usuario_actualizado = usuarioRepositorio.save(usuario);
        return ResponseEntity.ok(usuario_actualizado);
    }
    
    @PutMapping("/cambiarContrasena/{id}")
    public ResponseEntity<Usuario> cambiarContrasena(@PathVariable(value = "id") Long id_usuario,
         @Valid @RequestBody Usuario usuario_detalle) throws ResourceNotFoundException {
    	Usuario usuario = usuarioRepositorio.findById(id_usuario)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id :: " + id_usuario));

        usuario.setContrasenaUsuario(passwordEncoder.encode(usuario_detalle.getContrasenaUsuario()));
        
        final Usuario usuario_actualizado = usuarioRepositorio.save(usuario);
        return ResponseEntity.ok(usuario_actualizado);
    }
 
    
    /*@PostMapping(value="/reporteGeneral", produces = "application/json")
    private String reporteGeneralUsuarios(@Valid @RequestBody List<Object> lista)throws ResourceNotFoundException, InvalidFormatException {
    	
    	String query = "";
    	if(lista.get(11).toString().equals("")) {
    		if(lista.get(10).toString().equals("0")) {
    			query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.dni_funcionario, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario, uf.fijo_usuario_funcionario, uf.anexo_usuario_funcionario, uf.movil_usuario_funcionario, uf.movil2_usuario_funcionario, uf.correo_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    	    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    	    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    	    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 " +
    	    			"ORDER BY f.paterno_funcionario";
    		}
    		else {
    			query ="SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.dni_funcionario, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario, uf.fijo_usuario_funcionario, uf.anexo_usuario_funcionario, uf.movil_usuario_funcionario, uf.movil2_usuario_funcionario, uf.correo_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    	    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    	    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    	    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 "+
    	    			"AND u.tipo_usuario = "+lista.get(10).toString()+
    	    			" ORDER BY f.paterno_funcionario";
    		}
    	}
    	else {
    		if(lista.get(10).toString().equals("0")) {
    			query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.dni_funcionario, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario, uf.fijo_usuario_funcionario, uf.anexo_usuario_funcionario, uf.movil_usuario_funcionario, uf.movil2_usuario_funcionario, uf.correo_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    	    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    	    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    	    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 " +
    	    			"AND ( f.paterno_funcionario like '%"+lista.get(11).toString()+"%' OR f.materno_funcionario like '%"+lista.get(11).toString()+"%' OR f.nombres_funcionario like '%"+lista.get(11).toString()+"%') " +
    	    			"ORDER BY f.paterno_funcionario";
    		}
    		else {
    			query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.dni_funcionario, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario, uf.fijo_usuario_funcionario, uf.anexo_usuario_funcionario, uf.movil_usuario_funcionario, uf.movil2_usuario_funcionario, uf.correo_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
    	    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
    	    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
    	    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 "+
    	    			"AND u.tipo_usuario = "+lista.get(10).toString()+
    	    			"AND ( f.paterno_funcionario like '%"+lista.get(11).toString()+"%' OR f.materno_funcionario like '%"+lista.get(11).toString()+"%' OR f.nombres_funcionario like '%"+lista.get(11).toString()+"%') " +
    	    			" ORDER BY f.paterno_funcionario";
    		}
    	}

    	boolean problema = false;
    	List<Usuario> usuarios = new ArrayList<Usuario>();
    	List<String> fijos = new ArrayList<String>();
    	List<String> anexos = new ArrayList<String>();
    	List<String> moviles = new ArrayList<String>();
    	List<String> moviles2 = new ArrayList<String>();
    	List<String> correos = new ArrayList<String>();
    	try {
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);	
    		for(Map row: rows) {
    			
    			Usuario objeto = new Usuario();
    			
    			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
    			objeto.setCodigo_usuario((String) row.get("procedencia_usuario_funcionario"));
    			
    			//objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
    			objeto.setCodigo_persona((String) row.get("codigo_persona"));
    			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));
    			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
    			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
    			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
    			objeto.setNombreUsuario((String) row.get("nombre_usuario"));
    			objeto.setContrasena_usuario((String) row.get("paterno_funcionario")+" "+(String) row.get("materno_funcionario")+", "+(String) row.get("nombres_funcionario"));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			
    			usuarios.add(objeto);
    			fijos.add((String) row.get("fijo_usuario_funcionario"));
    			anexos.add((String) row.get("anexo_usuario_funcionario"));
    			moviles.add((String) row.get("movil_usuario_funcionario"));
    			moviles2.add((String) row.get("movil2_usuario_funcionario"));
    			correos.add((String) row.get("correo_usuario_funcionario"));
    		}	
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
    	if(!problema) {
    		String excelFilePath = "C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/Reporte Usuarios.xlsx";
        	
        	try {
        		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);
                
                Font dataFont = workbook.createFont();
	         	dataFont.setFontName("Arial");
	         	dataFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	         	dataFont.setFontHeightInPoints((short) 12);
	            
	            CellStyle backgroundStyle = workbook.createCellStyle();
	            backgroundStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	            backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	            backgroundStyle.setBorderBottom(CellStyle.BORDER_THIN);
	            backgroundStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	            backgroundStyle.setBorderLeft(CellStyle.BORDER_THIN);
	            backgroundStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	            backgroundStyle.setBorderRight(CellStyle.BORDER_THIN);
	            backgroundStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	            backgroundStyle.setBorderTop(CellStyle.BORDER_THIN);
	            backgroundStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	            backgroundStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	            backgroundStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            backgroundStyle.setFont(dataFont);
     
                Sheet sheet = workbook.getSheetAt(0);
     
                int rowCount = 4;
                
                Row row = sheet.getRow(++rowCount);
            	
            	int columnCount = 0;
            	Cell cell = row.getCell(columnCount);
                cell.setCellValue("N°");
                cell.setCellStyle(backgroundStyle);
                columnCount ++;
                
                if(lista.get(1).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Dni");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(0).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Apellidos y Nombres");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(2).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Usuario");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(3).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Tipo");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(4).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Entidad");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
        
                if(lista.get(5).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("T. Fijo");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(6).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Anexo");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(7).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Celular 1");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(8).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Celular 2");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
                
                if(lista.get(9).toString().equals("true")) {
                	cell = row.getCell(columnCount);
                	cell.setCellValue("Correo electrónico");
                	cell.setCellStyle(backgroundStyle);
                	columnCount ++;
                }
               
                 
                for(int i=0; i<usuarios.size(); i++) {
                	row = sheet.getRow(++rowCount);
                	
                	columnCount = 0;
                	cell = row.getCell(columnCount);
                    cell.setCellValue(i+1);
                    columnCount ++;
                    
                    if(lista.get(1).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(usuarios.get(i).getCodigo_persona());
                    	columnCount ++;
                    }
                    
                    if(lista.get(0).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(usuarios.get(i).getContrasena_usuario());
                    	columnCount ++;
                    }
                    
                    if(lista.get(2).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(usuarios.get(i).getNombreUsuario());
                    	columnCount ++;
                    }
                    
                    if(lista.get(3).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	if(usuarios.get(i).getTipo_usuario()==1) {
                    		cell.setCellValue("Administrador");
                    	}
                    	if(usuarios.get(i).getTipo_usuario()==2) {
                    		cell.setCellValue("Coordinador");
                    	}
                    	if(usuarios.get(i).getTipo_usuario()==3) {
                    		cell.setCellValue("Sector");
                    	}
                    	if(usuarios.get(i).getTipo_usuario()==4) {
                    		cell.setCellValue("Operador");
                    	}
                    	columnCount ++;
                    }
                    
                    if(lista.get(4).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(usuarios.get(i).getCodigo_usuario());
                    	columnCount ++;
                    }
                    
                    if(lista.get(5).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(fijos.get(i));
                    	columnCount ++;
                    }
                    
                    if(lista.get(6).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(anexos.get(i));
                    	columnCount ++;
                    }
                    
                    if(lista.get(7).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(moviles.get(i));
                    	columnCount ++;
                    }
                    
                    if(lista.get(8).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(moviles2.get(i));
                    	columnCount ++;
                    }
                    
                    if(lista.get(9).toString().equals("true")) {
                    	cell = row.getCell(columnCount);
                    	cell.setCellValue(correos.get(i));
                    	columnCount ++;
                    }
                        
                }
                
                inputStream.close();

                FileOutputStream outputStream = new FileOutputStream("C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/temporal.xlsx");
                workbook.write(outputStream);
                outputStream.close();
                
                File file = new File("C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/temporal.xlsx");
                
                byte[] input_file = Files.readAllBytes(Paths.get("C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/temporal.xlsx"));
                
                byte[] encodedBytes = Base64.encodeBase64(input_file);
                
                String excelInBase64 = new String(encodedBytes);
                
                if(file.exists()) {
                	file.delete();
                }
                
                return excelInBase64;
        	}
        	catch(Exception e) {
        		return "";	
        	}
    	}
    	else {
    		return "";
    	}
        	
	}
    
    @GetMapping(value="/reporteParticular/{id}", produces = "application/json")
    private String reporteParticularUsuario(@PathVariable(value = "id") Long id_usuario)throws ResourceNotFoundException, InvalidFormatException {
    	
    	boolean problema = false;
    	List<Usuario> usuarios = new ArrayList<Usuario>();
    	List<String> fijos = new ArrayList<String>();
    	List<String> anexos = new ArrayList<String>();
    	List<String> moviles = new ArrayList<String>();
    	List<String> moviles2 = new ArrayList<String>();
    	List<String> correos = new ArrayList<String>();
    	try {
    		String query = "SELECT DISTINCT u.id_usuario, u.codigo_usuario, u.codigo_funcionario, u.id_funcionario, u.tipo_usuario, u.estado_usuario, u.conexion_usuario, u.nombre_usuario, u.contrasena_usuario, u.id_usuario_creacion, u.ip_creacion, u.fecha_creacion, f.dni_funcionario, f.paterno_funcionario, f.materno_funcionario, f.nombres_funcionario, uf.procedencia_usuario_funcionario, uf.fijo_usuario_funcionario, uf.anexo_usuario_funcionario, uf.movil_usuario_funcionario, uf.movil2_usuario_funcionario, uf.correo_usuario_funcionario FROM ta_acn_usuarios u\r\n" + 
	    			"JOIN ta_acn_personas f ON f.id_funcionario = u.id_funcionario\r\n" + 
	    			"JOIN ta_acn_usufuncionarios uf ON uf.id_usuario = u.id_usuario\r\n" + 
	    			"WHERE u.estado_usuario = 1 AND uf.estado_usuario_funcionario = 1 AND u.id_usuario="+id_usuario +" "+
	    			"ORDER BY f.paterno_funcionario";
    		
    		List<Map<String, Object>> rows = jdbctemplate.queryForList(query);
    		for(Map row: rows) {
    			Usuario objeto = new Usuario();

    			objeto.setId_usuario(Long.parseLong(String.valueOf(row.get("id_usuario"))));
    			objeto.setCodigo_usuario((String) row.get("procedencia_usuario_funcionario"));
    			
    			//objeto.setCodigo_funcionario((String) row.get("codigo_funcionario"));
    			objeto.setCodigo_persona((String) row.get("codigo_persona"));
    			objeto.setId_persona(Long.parseLong(String.valueOf(row.get("id_persona"))));
    			objeto.setTipo_usuario(Integer.parseInt(String.valueOf(row.get("tipo_usuario"))));
    			objeto.setEstado_usuario(Integer.parseInt(String.valueOf(row.get("estado_usuario"))));
    			objeto.setConexion_usuario(Integer.parseInt(String.valueOf(row.get("conexion_usuario"))));
    			objeto.setNombreUsuario((String) row.get("nombre_usuario"));
    			objeto.setContrasena_usuario((String) row.get("paterno_funcionario")+" "+(String) row.get("materno_funcionario")+", "+(String) row.get("nombres_funcionario"));
    			
    			objeto.setId_usuario_creacion(Long.parseLong(String.valueOf(row.get("id_usuario_creacion"))));
    			objeto.setFecha_creacion((Date) row.get("fecha_creacion"));
    			objeto.setIp_creacion((String) row.get("ip_creacion"));
    			
    			usuarios.add(objeto);
    			fijos.add((String) row.get("fijo_usuario_funcionario"));
    			anexos.add((String) row.get("anexo_usuario_funcionario"));
    			moviles.add((String) row.get("movil_usuario_funcionario"));
    			moviles2.add((String) row.get("movil2_usuario_funcionario"));
    			correos.add((String) row.get("correo_usuario_funcionario"));
    		}
    	}
    	catch(Exception e) {
    		problema = true;
    	}
    	
   		
   		if(!problema) {
   			String excelFilePath = "C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/Reporte Usuario.xlsx";
   	    	
   	    	try {
   	            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
   	            Workbook workbook = WorkbookFactory.create(inputStream);
   	 
   	            Sheet sheet = workbook.getSheetAt(0);
   	 
   	            int rowCount = 4;
   	            Row row = sheet.getRow(++rowCount);
   	        	int columnCount = 2;
   	        	Cell cell = row.getCell(columnCount);
   	            cell.setCellValue(usuarios.get(0).getContrasena_usuario());
	        	
   	            rowCount = 5;
   	            row = sheet.getRow(++rowCount);
   	        	columnCount = 2;
   	        	cell = row.getCell(columnCount);
   	            cell.setCellValue(usuarios.get(0).getCodigo_persona());
	        	 
   	            rowCount = 6;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(usuarios.get(0).getNombreUsuario());
	            
	            rowCount = 7;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            if(usuarios.get(0).getTipo_usuario()==1) {
	            	cell.setCellValue("Administrador");
	            }
	            if(usuarios.get(0).getTipo_usuario()==2) {
	            	cell.setCellValue("Coordinador");
	            }
	            if(usuarios.get(0).getTipo_usuario()==3) {
	            	cell.setCellValue("Sector");
	            }
	            if(usuarios.get(0).getTipo_usuario()==4) {
	            	cell.setCellValue("Operador");
	            }
	            
	            rowCount = 8;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(usuarios.get(0).getCodigo_usuario());

	            rowCount = 9;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(fijos.get(0));
	            
	            rowCount = 10;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(anexos.get(0));

	            rowCount =11;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(moviles.get(0));

	            rowCount = 12;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(moviles2.get(0));
	            
	            rowCount = 13;
	            row = sheet.getRow(++rowCount);
	        	columnCount = 2;
	        	cell = row.getCell(columnCount);
	            cell.setCellValue(correos.get(0));
   	            
   	            inputStream.close();

                FileOutputStream outputStream = new FileOutputStream("C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/temporal.xlsx");
                workbook.write(outputStream);
                outputStream.close();
                
                File file = new File("C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/temporal.xlsx");
                
                byte[] input_file = Files.readAllBytes(Paths.get("C:/Program Files (x86)/Apache Software Foundation/Tomcat 9.0/webapps/acn/WEB-INF/classes/static/excel/temporal.xlsx"));
   	            
   	            byte[] encodedBytes = Base64.encodeBase64(input_file);
   	            
   	            String excelInBase64 = new String(encodedBytes);
   	            
   	            if(file.exists()) {
   	            	file.delete();
   	            }
   	            
   	            return excelInBase64;
   	        } catch (IOException ex) {
   	            ex.printStackTrace();
   	            return "";
   	        }
   	        catch (EncryptedDocumentException ex) {
   	            ex.printStackTrace();
   	            return "";
   	        }
   		}
   		else {
   			return "";
   		}
   		   	    	
	}*/
    
}