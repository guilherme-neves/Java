package com.br.gestor.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.br.gestor.model.Gestor;

public interface GestorDao extends CrudRepository<Gestor, Integer> {

	@Query("SELECT g, r.descricao FROM Gestor g inner join Ramal r on g.ramal = r.id_ramal")
	List<Gestor> listar();

	@Query("select g from Gestor g where g.data_inicio >=?1")
	List<Gestor> Listar(Date data);

	@Query("select g from Gestor g order by g.data_inicio desc")
	List<Gestor> listarOrdem();

	@Query("select count(*) from Gestor g where g.tipo =?1 ")
	Long Entrada1(String dado);
	
	@Query("select count(*) from Gestor g where g.tipo =?1 and g.data_inicio >=?2  ")
	Long Entrada(String dado, Date data);

	// Buscar Ramal
	@Query("select g from Gestor g where g.ramal like ?1% order by g.data_inicio desc")
	List<Gestor> Buscar(String ramal);
	
	// Buscar Telefone
	@Query("select g from Gestor g where g.numero like ?1% order by g.data_inicio desc")
	List<Gestor> BuscarTelefone(String telefone);
	
	
}
