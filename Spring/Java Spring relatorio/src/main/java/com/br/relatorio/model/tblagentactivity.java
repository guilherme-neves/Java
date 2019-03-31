package com.br.relatorio.model;




import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class tblagentactivity {

	@Id
	private Integer aa_id;
	private int aa_agent_id;
	private int aa_queue_id;
	private int aa_call_direction;
	private int aa_call_id;
	private int aa_event_type;
	
	private String aa_event_data;
	private Date aa_event_time;
	private int aa_associated_data;
	private String aa_event_duration;
	
	public Integer getAa_id() {
		return aa_id;
	}
	public void setAa_id(Integer aa_id) {
		this.aa_id = aa_id;
	}
	public int getAa_agent_id() {
		return aa_agent_id;
	}
	public void setAa_agent_id(int aa_agent_id) {
		this.aa_agent_id = aa_agent_id;
	}
	public int getAa_queue_id() {
		return aa_queue_id;
	}
	public void setAa_queue_id(int aa_queue_id) {
		this.aa_queue_id = aa_queue_id;
	}
	public int getAa_call_direction() {
		return aa_call_direction;
	}
	public void setAa_call_direction(int aa_call_direction) {
		this.aa_call_direction = aa_call_direction;
	}
	public int getAa_call_id() {
		return aa_call_id;
	}
	public void setAa_call_id(int aa_call_id) {
		this.aa_call_id = aa_call_id;
	}
	public int getAa_event_type() {
		return aa_event_type;
	}
	public void setAa_event_type(int aa_event_type) {
		this.aa_event_type = aa_event_type;
	}
	public String getAa_event_data() {
		return aa_event_data;
	}
	public void setAa_event_data(String aa_event_data) {
		this.aa_event_data = aa_event_data;
	}
	
	public int getAa_associated_data() {
		return aa_associated_data;
	}
	public void setAa_associated_data(int aa_associated_data) {
		this.aa_associated_data = aa_associated_data;
	}
	public String getAa_event_duration() {
		return aa_event_duration;
	}
	public void setAa_event_duration(String aa_event_duration) {
		this.aa_event_duration = aa_event_duration;
	}
	public Date getAa_event_time() {
		return aa_event_time;
	}
	public void setAa_event_time(Date aa_event_time) {
		this.aa_event_time = aa_event_time;
	}
	
	
	
	
	
	
	
	
	
	
	
}
