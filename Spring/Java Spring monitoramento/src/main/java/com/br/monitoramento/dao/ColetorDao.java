package com.br.monitoramento.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.monitoramento.model.Coletor;

public interface ColetorDao extends CrudRepository<Coletor, Integer> {

	long countByTipo(String tipo);

	@Query("select count(*) from Coletor where tipo=:d")
	long Tipo(@Param("d") String tipo);

	// ####### Datas

	@Query("select count(*) from Coletor where tipo=:d and dataInicio between :start and :end ")
	long TipoData(@Param("d") String tipo, @Param("start") Date start, @Param("end") Date end);

	@Query("select count(*) from Coletor where tipo=:d and dataInicio >=:start and dataInicio <=:end ")
	long TipoData2(@Param("d") String tipo, @Param("start") Date start, @Param("end") Date end);

	@Query("select count(*) from Coletor where dataInicio >=:start and tipo=:d")
	long TipoData3(@Param("start") Date start, @Param("d") String tipo);

	@Query("select a from Coletor a where a.dataInicio>=:start and a.dataInicio<=:end")
	List<Coletor> lista(@Param("start") Date start, @Param("end") Date end);

	@Query("select a from Coletor a where a.dataInicio >=:start")
	List<Coletor> data(@Param("start") Date start);

	@Query("select a from Coletor a where  a.dataInicio >=?1 order by a.dataInicio desc")
	List<Coletor> data2(Date data);

	@Query("select a from Coletor a where a.dataInicio >=?1 ")
	List<Coletor> data3(Date data);

	// ###Origem do Ramal
    // Funcionando
	@Query("select a from Coletor a where a.origem like ?1%")
	List<Coletor> Origem2(String start);

	@Query("select a from Coletor a where a.origem like ?1% and a.dataInicio >=?2")
	List<Coletor> OrigemData(String origem, Date data);

	// ####Destino #####
	@Query("select a from Coletor a where a.destino like ?1%")
	List<Coletor> Destino(String destino);

}
