package com.br.gestor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.br.gestor.model.Ramal;

public interface RamalDao extends CrudRepository<Ramal, String> {

	@Query("select r from Ramal r")
	List<Ramal> lista();
	
}
