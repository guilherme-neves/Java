package com.br.gestor.model;

public class Municipio {
	
	private String a;
	  private String b;
	  
	  public String getCodigo()
	  {
	    return this.a;
	  }
	  
	public void setCodigo(String codigo)
	  {
	    this.a = codigo;
	  }
	  
	  public String getNome()
	  {
	    return this.b;
	  }
	  
	  public void setNome(String descricao)
	  {
	    this.b = descricao;
	  }

}
