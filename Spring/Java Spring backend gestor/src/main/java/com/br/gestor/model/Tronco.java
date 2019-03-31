package com.br.gestor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tronco {

	@Id
	private String id_tronco;
	private String descricao;

	public String getId_tronco() {
		return id_tronco;
	}

	public void setId_tronco(String id_tronco) {
		this.id_tronco = id_tronco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
