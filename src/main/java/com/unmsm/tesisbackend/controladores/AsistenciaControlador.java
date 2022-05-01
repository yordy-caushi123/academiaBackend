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


import com.unmsm.tesisbackend.entidades.Asistencia;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.AsistenciaRepositorio;




@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/asistencia")
public class AsistenciaControlador {
	@Autowired
    private AsistenciaRepositorio asistenciaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Asistencia> listar() {
        return asistenciaRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Asistencia> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Asistencia asistencia = asistenciaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(asistencia);
    }
    
    @PostMapping("/registrar")
    public Asistencia crear(@Valid @RequestBody Asistencia asistencia) {
        return asistenciaRepositorio.save(asistencia);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Asistencia> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Asistencia detalle) throws ResourceNotFoundException {
        Asistencia asistencia = asistenciaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrado para id :: " + id));

        asistencia=detalle;
        
        final Asistencia actualizado = asistenciaRepositorio.save(asistencia);
        return ResponseEntity.ok(actualizado);
    } 
}