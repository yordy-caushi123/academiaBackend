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


import com.unmsm.tesisbackend.entidades.FormaPago;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.FormaPagoRepositorio;





@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/formapago")
public class FormaPagoControlador {
	@Autowired
    private FormaPagoRepositorio formapagoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<FormaPago> listar() {
        return formapagoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<FormaPago> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	FormaPago formapago = formapagoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("FormaPago no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(formapago);
    }
    
    @PostMapping("/registrar")
    public FormaPago crear(@Valid @RequestBody FormaPago formapago) {
        return formapagoRepositorio.save(formapago);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<FormaPago> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody FormaPago detalle) throws ResourceNotFoundException {
        FormaPago formapago = formapagoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("FormaPago no encontrado para id :: " + id));

        formapago=detalle;
        
        final FormaPago actualizado = formapagoRepositorio.save(formapago);
        return ResponseEntity.ok(actualizado);
    } 
}