package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entidade.Departamento;
import model.entidade.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn=conn;
	}

	@Override
	public void insert(Seller obj) {
		
	}
	
	@Override
	public void update(Seller obj) {
		
	}
	
	//deletar o registro pelo id
	@Override
	public void deleteById(Integer id) {
		
		
	}
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.id "
					+ "where seller.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Departamento dep = new Departamento();
					dep.setId(rs.getInt("DepartmentId"));
					dep.setNome(rs.getString("DepName"));
					
					Seller obj = new Seller();
						obj.setId(rs.getInt("Id"));
						obj.setNome(rs.getString("Name"));
						obj.setEmail(rs.getString("Email"));
						obj.setSalario(rs.getDouble("BaseSalary"));
						obj.setBirthDate(rs.getDate("BirthDate"));
						obj.setDepartamento(dep);
						return obj;
			}
			
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
		return null;
	}
	@Override
	public List<Seller> findAll(){
		return null;
	}
	
}
