package com.cuenta.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cuenta.api.models.entity.Cuenta;
import com.cuenta.api.models.repository.ICuentaRepository;
import com.cuenta.api.models.service.CuentaService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CuentaApirestApplicationTests.class})
@SpringBootTest
class CuentaApirestApplicationTests {
	
	@InjectMocks
	private CuentaService service;
		
	@Mock
	private ICuentaRepository respository;
	
	@Test
	void findAllTotalRegistrosTest() {
		when(respository.findAll()).thenReturn(DatosPrueba.cuentas);
		
		List<Cuenta> cuenta = service.findAll();
		assertEquals(2, cuenta.size());
		assertNotNull(cuenta);
		verify(respository,times(1)).findAll();
	}
	
	@Test
	void findAllVacioTest() {		
		List<Cuenta> cuenta = Collections.emptyList();
		when(respository.findAll()).thenReturn(cuenta);
		List<Cuenta> cuentaVacio = service.findAll();
		assertEquals(0, cuentaVacio.size());
		assertFalse(cuentaVacio==null);
		verify(respository,times(1)).findAll();
	}
	
	@Test
	void saveTest() {		
		
		  when(respository.save(any(Cuenta.class))).thenReturn(DatosPrueba.
		  cuentaResponse); Cuenta cuenta = service.save(DatosPrueba.empleadoRequest());
		  assertEquals("Zulay", cuenta.getCliente()); assertEquals("123456",
		  cuenta.getNumeroCuenta());
		  verify(respository,times(1)).save(any(Cuenta.class));
		 
	}
	
	@Test
	void deleteTest() {		
		respository.findById(anyLong());
		service.deleteById(1L);

		verify(respository,times(1)).deleteById(anyLong());
	}
}
