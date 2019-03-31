package com.br.gestor.dao;

import org.springframework.data.repository.CrudRepository;

import com.br.gestor.model.Contato;

public interface ContatoDao extends CrudRepository<Contato, Integer> {

}
