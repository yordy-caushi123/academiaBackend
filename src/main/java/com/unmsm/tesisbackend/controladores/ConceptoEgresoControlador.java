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

import com.unmsm.tesisbackend.entidades.ConceptoEgreso;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.ConceptoEgresoRepositorio;





@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/conceptoegreso")
public class ConceptoEgresoControlador {
	@Autowired
    private ConceptoEgresoRepositorio conceptoegresoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<ConceptoEgreso> listar() {
        return conceptoegresoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ConceptoEgreso> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	ConceptoEgreso conceptoegreso = conceptoegresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConceptoEgreso no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(conceptoegreso);
    }
    
    @PostMapping("/registrar")
    public ConceptoEgreso crear(@Valid @RequestBody ConceptoEgreso conceptoegreso) {
        return conceptoegresoRepositorio.save(conceptoegreso);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ConceptoEgreso> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody ConceptoEgreso detalle) throws ResourceNotFoundException {
        ConceptoEgreso conceptoegreso = conceptoegresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConceptoEgreso no encontrado para id :: " + id));

        conceptoegreso=detalle;
        
        final ConceptoEgreso actualizado = conceptoegresoRepositorio.save(conceptoegreso);
        return ResponseEntity.ok(actualizado);
    } 
}