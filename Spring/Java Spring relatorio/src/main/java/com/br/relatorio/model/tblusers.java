package com.br.relatorio.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class tblusers {

	@Id
	private Integer user_id;
	private String user_login;
	private String user_password;
	private String user_firstname;
	private String user_surname;
	private boolean user_active;
	private String user_email;
	private String user_extension;
	private int user_is_agent;
	
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_login() {
		return user_login;
	}
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_firstname() {
		return user_firstname;
	}
	public void setUser_firstname(String user_firstname) {
		this.user_firstname = user_firstname;
	}
	public String getUser_surname() {
		return user_surname;
	}
	public void setUser_surname(String user_surname) {
		this.user_surname = user_surname;
	}
	
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_extension() {
		return user_extension;
	}
	public void setUser_extension(String user_extension) {
		this.user_extension = user_extension;
	}
	public int getUser_is_agent() {
		return user_is_agent;
	}
	public void setUser_is_agent(int user_is_agent) {
		this.user_is_agent = user_is_agent;
	}
	public boolean isUser_active() {
		return user_active;
	}
	public void setUser_active(boolean user_active) {
		this.user_active = user_active;
	}
	
	
	
		
}

