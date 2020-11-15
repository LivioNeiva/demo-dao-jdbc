package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
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
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("delete from department "
			+ "where id = ? ",
			Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			
			int numLinhas = st.executeUpdate();
			
			if(numLinhas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id2 = rs.getInt(1);
					System.out.println("QUANTIDADE DE IDS AFETADOS? "+id2);
				}
			}else {
				System.out.println("ERRO INESPERADO || NENHUMA LINHA FOI AFETADA");
			}
			System.out.println("QUANTIDADE DE LINHAS AFETADAS: "+numLinhas);
			
		}catch(SQLException ex) {
			throw new DbIntegrityException(ex.getMessage());
		}finally {
			DB.closeStatment(st);
		}
		/*
		 * criaremos uma Exceçao personalizada DbIntegrityException
		 * trablharemos com ERRO integridade referencial
		 * integridade referencial  - sao erros acontece ao apagar um registro q seja
		 * chave estrangeira em outra tabela.
		 * ex. nao podemos apagar o departamento 2 da tabela departamento, pois a mesma
		 * é chave estrangeira de dois registro na tabela seller(vendedor)
		 */
		/*
		  No entanto, o MySQL fornece uma maneira mais eficaz chamada de ON DELETE CASCADE ação
		  referencial para uma chave estrangeira, que permite excluir dados de tabelas filho 
		  automaticamente quando você exclui os dados da tabela pai.
		  */	
		
	}

	@Override
	public Departamento findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from department where id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("Id"));
				dep.setNome(rs.getString("Name"));
				return dep;
			}
			return null;
			
		}catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
