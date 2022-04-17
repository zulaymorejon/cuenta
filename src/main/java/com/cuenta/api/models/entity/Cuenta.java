package com.cuenta.api.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cuenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "[0-9]+",message = "Solo permite valores numericos")
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(unique=true, name="numcuenta")
	private String numeroCuenta;
	
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(name = "cliente")
	private String cliente;
	
	@NotEmpty(message = "El campo no puede ser vacio")
	@Column(name = "tipocuenta")
	private String tipoCuenta;
	
	@Column(name = "saldoinicial")
	private Double saldoInicial;
	
	@Column(name = "estado")
	private String estado;
	
	private static final long serialVersionUID = 4334455564095195932L;
}
