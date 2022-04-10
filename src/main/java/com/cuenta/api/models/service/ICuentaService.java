package com.cuenta.api.models.service;

import java.util.List;

import com.cuenta.api.models.entity.Cuenta;

public interface ICuentaService {
	List<Cuenta> findAll();
	Cuenta save(Cuenta cuenta);
	Cuenta findById(Long id);
	void deleteById(Long id);
}
