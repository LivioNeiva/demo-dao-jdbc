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
	//localiza o vendedor pelo id
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "// lista todos os campos tabela seller e inclui um campocom nome do departamento ao qual registro percençe
					+ "FROM seller INNER JOIN department "//tabela seller tem uma junção com a tabela  departamento 
					+ "ON seller.DepartmentId = department.id "//a junção é feita atraves dos campo DepartmentId = department.id
					+ "where seller.id = ?");//qual id queremos seja listado. Ex 3
			
			st.setInt(1, id);//adicionando o campo id para informamos qual registro será listado
			rs = st.executeQuery();//executando a consulta e adicionando noobj result
			
			if(rs.next()) {//o obj result inicia index 0, next avança para prox index, será 1
				Departamento dep = instanciarDepartamento(rs);
				Seller obj = instaciarSeller(rs, dep);
				return obj;
			}
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatment(st);//fechando o prepared startment
			DB.closeResultSet(rs);//fechando o ResultSet
		}
		return null;
	}
	@Override
	public List<Seller> findAll(){
		return null;
	}
	
	private Seller instaciarSeller(ResultSet rs, Departamento dep) throws SQLException{
		
		Seller obj = new Seller();// criando obj do tipo Seller(vendedor)
			obj.setId(rs.getInt("Id"));//extraindo o campo id do obj ResultSet no qual se encontra o resultado da consulta sql
			obj.setNome(rs.getString("Name"));//extraindo o campo nome do obj resultSet no qual se encontra o resultado da consulta sql
			obj.setEmail(rs.getString("Email"));//extraindo o campo Email do obj ResultSet no qual se encontra o resultado da consulta sql
			obj.setSalario(rs.getDouble("BaseSalary"));//extraindo campo salario do obj ResultSet no qual se encontra o resultado da consulta sql
			obj.setBirthDate(rs.getDate("BirthDate"));//extraindo campo data do obj ResultSet no qual se encontra o resultado da consulta sql
			obj.setDepartamento(dep);//o obj dep está o id e nome do departamento
		return obj;
		
	}
	private Departamento instanciarDepartamento(ResultSet rs) throws SQLException{
		Departamento dep = new Departamento();//criando obj do tipo departamento
		//o resultado da consulta está dentro do obj rs(ResultSet)
			dep.setId(rs.getInt("DepartmentId"));//vamos extrair campo id departamento da sonsulta sql e setar no obj dep
			dep.setNome(rs.getString("DepName"));//vamos extrair campo nome do departamento da consulta sql e setar obj dep
		return dep;
	}
	
}
