package com.br.jwt.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String senha;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	 public int hashCode()
	  {
	    int prime = 31;
	    int result = 1;
	    result = 31 * result + (this.login == null ? 0 : this.login.hashCode());
	    return result;
	  }
	  
	  public boolean equals(Object obj)
	  {
	    if (this == obj) {
	      return true;
	    }
	    if (obj == null) {
	      return false;
	    }
	    if (getClass() != obj.getClass()) {
	      return false;
	    }
	    Usuario other = (Usuario)obj;
	    if (this.login == null)
	    {
	      if (other.login != null) {
	        return false;
	      }
	    }
	    else if (!this.login.equals(other.login)) {
	      return false;
	    }
	    return true;
	  }
	  
	
	  
	  public Collection<? extends GrantedAuthority> getAuthorities()
	  {
	    return Collections.EMPTY_LIST;
	  }
	  
	  public String getPassword()
	  {
	    return this.senha;
	  }
	  
	  public String getUsername()
	  {
	    return this.login;
	  }
	  
	  public boolean isAccountNonExpired()
	  {
	    return true;
	  }
	  
	  public boolean isAccountNonLocked()
	  {
	    return true;
	  }
	  
	  public boolean isCredentialsNonExpired()
	  {
	    return true;
	  }
	  
	  public boolean isEnabled()
	  {
	    return true;
	  }
	
}
