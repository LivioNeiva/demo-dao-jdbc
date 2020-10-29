package model.dao;

import java.util.List;

import model.entidade.Seller;

public interface SellerDao {

	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);//encontrar por id
	List<Seller> findAll();//encontrar tudo			
}
