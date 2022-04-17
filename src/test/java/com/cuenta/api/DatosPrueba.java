package com.cuenta.api;

import java.util.Arrays;
import java.util.List;

import com.cuenta.api.models.entity.Cuenta;

public class DatosPrueba {
	
	public static final List<Cuenta> cuentas = Arrays.asList(new Cuenta(1L, "123456", "Zulay", "Ahorro", 500.00, "Activo"),
			new Cuenta(2L, "123457", "Edith", "Ahorro", 150.00, "Activo"));
	
	public static final Cuenta cuentaResponse= new Cuenta(1L, "123456", "Zulay", "Ahorro", 500.00, "Activo");
	
	public static Cuenta empleadoRequest() {
		Cuenta cuenta = new Cuenta();
		return cuenta.builder()
		.numeroCuenta("123456")
		.cliente("0929008316")
		.tipoCuenta("Ahorro")
		.saldoInicial(500.00)
		.estado("Activo")
		.build();
		
	}

}
