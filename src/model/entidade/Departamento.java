package model.entidade;

import java.io.Serializable;

public class Departamento implements Serializable {
	
	//Seriazable - para que os objs sejam transformados em seguencias de bytes, para que os
	//objs sejam gravados em arquivos e trafegados em redes tem fazer  seriazable
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	
	public Departamento() {
		
	}
	public Departamento(Integer id, String nome) {
		this.id=id;
		this.nome=nome;
		
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return " Departamento [Codigo= "+id + ", Nome = "+nome+ "]";
	}

}