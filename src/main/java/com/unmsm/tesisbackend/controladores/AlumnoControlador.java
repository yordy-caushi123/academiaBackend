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

import com.unmsm.tesisbackend.entidades.Alumno;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.AlumnoRepositorio;



@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/alumno")
public class AlumnoControlador {
	@Autowired
    private AlumnoRepositorio alumnoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Alumno> listar() {
        return alumnoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Alumno> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Alumno alumno = alumnoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(alumno);
    }
    
    @PostMapping("/registrar")
    public Alumno crear(@Valid @RequestBody Alumno alumno) {
        return alumnoRepositorio.save(alumno);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Alumno detalle) throws ResourceNotFoundException {
        Alumno alumno = alumnoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado para id :: " + id));

        alumno=detalle;
        
        final Alumno actualizado = alumnoRepositorio.save(alumno);
        return ResponseEntity.ok(actualizado);
    } 
}