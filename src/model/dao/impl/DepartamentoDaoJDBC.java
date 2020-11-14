package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entidade.Departamento;

public class DepartamentoDaoJDBC implements DepartmentDao{

	private Connection conn;
	
	public DepartamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Departamento obj) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into department "
					+ "(Name) "
					+ "values "
					+ "(?) ",
					Statement.RETURN_GENERATED_KEYS);
					
			st.setString(1, obj.getNome());
			
			int numLinhas = st.executeUpdate();
			
			if(numLinhas > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeStatment(st);
			}else {
				throw new DbException("ERRO INESPERADO || NENHUMA LINHA FOI AFETADA");
			}
			
		}catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}finally {
			DB.closeStatment(st);
		}
		
		
		
	}

	@Override
	public void update(Departamento obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("update department "
					+ "set Name = ? "
					+ "where id = ? ");
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		}catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}finally {
			DB.closeStatment(st);
		}
		
		
	}

	@Override
	public void DeleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
