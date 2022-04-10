package com.cuenta.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuenta.api.models.entity.Cuenta;

@Service
public class CuentaService implements ICuentaService{
	@Autowired
	private ICuentaService repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> findAll(){
		return (List<Cuenta>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cuenta findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Cuenta save(Cuenta cuenta) {
		return repository.save(cuenta);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
