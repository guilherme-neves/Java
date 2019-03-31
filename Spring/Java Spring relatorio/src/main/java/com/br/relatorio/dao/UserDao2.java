package com.br.relatorio.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.br.relatorio.model.tblusers3;

public interface UserDao2 extends CrudRepository<tblusers3, Date> {

	@Query(nativeQuery = true, value = "Select \"date\"(aa_event_time) as \"Start_Date\" ,"
			+ " \"time\"(aa_event_time) as \"Start_Time\" , "
			+ " \"time\"(aa_event_time)+aa_event_duration as \"End_time\" ,"
			+ " ( EXTRACT (epoch from aa_event_duration )) as timeInSec "
			+ " from tblagentactivity, tblusers u where aa_event_type = 0 "
			+ " and aa_event_time >= '2018-06-14' and aa_event_time <= (\"date\"('2018-06-14') + interval '24 hours') "
			+ " and aa_agent_id = u.user_id and u.user_login = '221' "
			+ " and aa_event_duration!='00:00:00' "
			+ " order by \"time\"(aa_event_time) ")
	List<tblusers3> lista2();
	
	
}
