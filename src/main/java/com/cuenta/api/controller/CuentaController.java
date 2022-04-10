package com.cuenta.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuenta.api.models.entity.Cuenta;
import com.cuenta.api.models.service.ICuentaService;

@RestController
@RequestMapping("/api")
public class CuentaController {
	
	@Autowired
	private ICuentaService service;
	
	@GetMapping("/cuentas")
	public List<Cuenta> listarCuenta(){
		return service.findAll();
	}
	
	@PostMapping("/cuentas")
	public ResponseEntity<?> guardarEmpleado(@Valid @RequestBody Cuenta cuentas, BindingResult result){
		Cuenta cuentasObj = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			response.put("error", resultError);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			cuentasObj = service.save(cuentas);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cuentas creada con exito.");
		response.put("cuenta", cuentasObj);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/cuentas/{id}")
	public ResponseEntity<?> actualizarEmpleado(@Valid @RequestBody Cuenta cuenta, BindingResult result, @PathVariable Long id){
		Cuenta cuentaFinal = null;
		Map<String, Object> response = new HashMap<>();
		Cuenta cuentaObj = service.findById(id);
		
		if(result.hasErrors()) {
			List<String> resultError = result.getFieldErrors().stream().map(r-> "El campo '"+r.getField()+"' "+r.getDefaultMessage()).collect(Collectors.toList());
			response.put("erros", resultError);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(cuentaObj == null) {
			response.put("mensaje", "No existe la cuenta con el id "+id);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			cuentaObj.setNumeroCuenta(cuenta.getNumeroCuenta());
			cuentaObj.setCliente(cuenta.getCliente());
			cuentaObj.setTipoCuenta(cuenta.getTipoCuenta());
			cuentaObj.setSaldoInicial(cuenta.getSaldoInicial());
			cuentaObj.setEstado(cuenta.getEstado());
				
			cuentaFinal = service.save(cuentaObj);
			
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente actualizado con exito.");
		response.put("cliente", cuentaFinal);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/cuentas/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			service.deleteById(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMostSpecificCause().getMessage());
			response.put("mensaje", "Se produjo un error en la aplicacion");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cuenta eliminada con exito.");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
}
