package com.cuenta.api.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuenta.api.models.entity.Cuenta;

public interface ICuentaRepository extends PagingAndSortingRepository<Cuenta, Long>{

}
