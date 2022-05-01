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

import com.unmsm.tesisbackend.entidades.ConceptoDetalle;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.ConceptoDetalleRepositorio;





@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/conceptodetalle")
public class ConceptoDetalleControlador {
	@Autowired
    private ConceptoDetalleRepositorio conceptodetalleRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<ConceptoDetalle> listar() {
        return conceptodetalleRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<ConceptoDetalle> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	ConceptoDetalle conceptodetalle = conceptodetalleRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConceptoDetalle no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(conceptodetalle);
    }
    
    @PostMapping("/registrar")
    public ConceptoDetalle crear(@Valid @RequestBody ConceptoDetalle conceptodetalle) {
        return conceptodetalleRepositorio.save(conceptodetalle);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ConceptoDetalle> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody ConceptoDetalle detalle) throws ResourceNotFoundException {
        ConceptoDetalle conceptodetalle = conceptodetalleRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConceptoDetalle no encontrado para id :: " + id));

        conceptodetalle=detalle;
        
        final ConceptoDetalle actualizado = conceptodetalleRepositorio.save(conceptodetalle);
        return ResponseEntity.ok(actualizado);
    } 
}