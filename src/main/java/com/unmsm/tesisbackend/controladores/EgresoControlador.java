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

import com.unmsm.tesisbackend.entidades.Egreso;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.EgresoRepositorio;





@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/egreso")
public class EgresoControlador {
	@Autowired
    private EgresoRepositorio egresoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Egreso> listar() {
        return egresoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Egreso> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Egreso egreso = egresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Egreso no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(egreso);
    }
    
    @PostMapping("/registrar")
    public Egreso crear(@Valid @RequestBody Egreso egreso) {
        return egresoRepositorio.save(egreso);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Egreso> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Egreso detalle) throws ResourceNotFoundException {
        Egreso egreso = egresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Egreso no encontrado para id :: " + id));

        egreso=detalle;
        
        final Egreso actualizado = egresoRepositorio.save(egreso);
        return ResponseEntity.ok(actualizado);
    } 
}