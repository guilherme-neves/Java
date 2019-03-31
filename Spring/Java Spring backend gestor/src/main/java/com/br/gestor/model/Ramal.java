package com.br.gestor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ramal {

	@Id
	private String id_ramal;
	private String descricao;

	public String getId_ramal() {
		return id_ramal;
	}

	public void setId_ramal(String id_ramal) {
		this.id_ramal = id_ramal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
