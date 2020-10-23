package model.dao;

import java.util.List;

import model.entidade.Departamento;

public interface DepartmentDao {
	
	void insert(Departamento Obj);
	void update(Departamento obj);
	void DeleteById(Integer id);
	Departamento findById(Integer id);
	List<Departamento> findAll();
}
