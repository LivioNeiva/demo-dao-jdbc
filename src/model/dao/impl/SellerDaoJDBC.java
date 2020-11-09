package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				Seller obj = instanciarSeller(rs, dep);
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
	@Override
	public List<Seller> findByDepartment(Departamento departamento){
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		st = conn.prepareStatement("select seller.*, department.Name as DepName "
				+ "from seller INNER JOIN department "
				+ "on seller.DepartmentId = department.Id "
				+ "where DepartmentId = ? "
				+ "order by Name");
		
		st.setInt(1, departamento.getId());
		rs = st.executeQuery();
		//sao varios valores no resultSet, temos criar uma lista para armazena-los, o retorno do metodo é do tipo list, temos retornar uma lista
		List<Seller> list = new ArrayList<>();
		//a chave é int, a identificação da chave são os valores do tipo departamento
		Map<Integer, Departamento> map = new HashMap<>();//vamos controlar a repetição do departamento com a estrutura map
			//while percorre o resultSet enquanto estiver um prox
			while(rs.next()) {//se eu usar so o while para cada linha do resultSet eu terei um nova instancia do tipo Departamento, para resolver, usarei if
				//vou add para o obj dep o meu valor de chave do map 
				//para cada resultado do ResultSet eu terei q instanciar um obj do tipo departamento, e do tipo seller
				//e add na lista. Seu eu usar so o while, eu terei q criar um novo obj de departameto para cada vendedor
				
				//estamos testando se o departamento ja existe
				//vamos passar o id q ja estiver no ResultSet(rs), dentro do map vai ficar armazenado qualquer departamento q for instnciado
				//vou buscar com metodo get, um departamento com id do campo "DepartmentId", se o depatmento nao existir, retorna null
				//retorna null para o obj dep.
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				//o if vai fazer o controle para nao repetir o obj departamento, apenas uma unica instanciação
				if(dep == null) {//se dep estiver null instacia o obj departamento
					dep = instanciarDepartamento(rs);//no prox laço o obj dep nao estára null, entao nao haverá um novo departamento
					//add na chave do map o id departamento, e add o departamento nos valores da chave do map
					map.put(rs.getInt("DepartmentId"), dep);//adicinaremos o id departamento no map, assim no prox enlace o dep nao estára null
				}
				//no laço so entraremos no if apenas uma vez, o dep ficara sempre com mesmo departamento
				Seller obj = instanciarSeller(rs, dep);//o obj seller recebe o vendedor e seu respectivo deparamento
				list.add(obj);//add o obj seller a lista do tipo seller.
				
			}
		return list;
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatment(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	private Seller instanciarSeller(ResultSet rs, Departamento dep) throws SQLException{
		
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
