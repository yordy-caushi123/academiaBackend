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

import com.unmsm.tesisbackend.entidades.AlumnoApoderado;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.AlumnoApoderadoRepositorio;




@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/alumnoapoderado")
public class AlumnoApoderadoControlador {
	@Autowired
    private AlumnoApoderadoRepositorio alumnoapoderadoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<AlumnoApoderado> listar() {
        return alumnoapoderadoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<AlumnoApoderado> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	AlumnoApoderado alumnoapoderado = alumnoapoderadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("AlumnoApoderado no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(alumnoapoderado);
    }
    
    @PostMapping("/registrar")
    public AlumnoApoderado crear(@Valid @RequestBody AlumnoApoderado alumnoapoderado) {
        return alumnoapoderadoRepositorio.save(alumnoapoderado);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AlumnoApoderado> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody AlumnoApoderado detalle) throws ResourceNotFoundException {
        AlumnoApoderado alumnoapoderado = alumnoapoderadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("AlumnoApoderado no encontrado para id :: " + id));

        alumnoapoderado=detalle;
        
        final AlumnoApoderado actualizado = alumnoapoderadoRepositorio.save(alumnoapoderado);
        return ResponseEntity.ok(actualizado);
    } 
}