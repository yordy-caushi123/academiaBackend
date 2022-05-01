package com.unmsm.tesisbackend.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.unmsm.tesisbackend.entidades.TipoAsistencia;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.TipoAsistenciaRepositorio;


@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/tipoasistencia")
public class TipoAsistenciaControlador {
	@Autowired
    private TipoAsistenciaRepositorio tipoasistenciaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<TipoAsistencia> listar() {
        return tipoasistenciaRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<TipoAsistencia> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	TipoAsistencia tipoasistencia = tipoasistenciaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoAsistencia no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(tipoasistencia);
    }
    
    @PostMapping("/registrar")
    public TipoAsistencia crear(@Valid @RequestBody TipoAsistencia tipoasistencia) {
        return tipoasistenciaRepositorio.save(tipoasistencia);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoAsistencia> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody TipoAsistencia detalle) throws ResourceNotFoundException {
        TipoAsistencia tipoasistencia = tipoasistenciaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoAsistencia no encontrado para id :: " + id));

        tipoasistencia=detalle;
        
        final TipoAsistencia actualizado = tipoasistenciaRepositorio.save(tipoasistencia);
        return ResponseEntity.ok(actualizado);
    } 
}