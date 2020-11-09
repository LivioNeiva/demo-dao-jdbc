package model.dao;

import java.util.List;

import model.entidade.Departamento;
import model.entidade.Seller;

public interface SellerDao {

	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);//apagar registro por id
	Seller findById(Integer id);//encontrar registro por id
	List<Seller> findAll();//encontrar todos registro tudo
	List<Seller> findByDepartment(Departamento departamento);//localizar pelo departamento, ou listar pelo departamento
}
