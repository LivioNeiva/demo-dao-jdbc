package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	
	//esse conection vai ser um obj do banco de dados do jdbc
	private static Connection conn = null;	
	
	//criar o metodo para conectar com banco de dados, vamos instanciar um obj do tipo connection
	public static Connection getConnection() {
		
		try {//se conn estiver null faça
			if(conn == null) {//codigo abaixo faz a conexao com banco de dados
				Properties props = loadProperties();//adicionar as propriedades de conexação ao obj de proriedade
				String url = props.getProperty("dburl");//o obj props pega o valor(propriedade) dbur1 esta arquivo db.properties
				conn = DriverManager.getConnection(url, props);//obter uma conexão com banco de dados
			}
		}catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		//o metodo retorna a variavel conn com todas as coneções q for preciso
		return conn;
	}
	//vamos fechar a conexão
	public static void closeConnection() {
		//se a conecxão nao estiver aberta
		if(conn != null) {
			try {
				conn.close();//fecha a conexao
			}catch(SQLException ex) {
				throw new DbException(ex.getMessage());//aciona a excessao criada
			}
		}
	}
	
	//vamos carregar arquivo db.properties e guardalo dentro de um obj especifico
	//criando uma classe do tipo properties
	private static Properties loadProperties() {
		//Um programa que precise ler algum dado de algum local (uma fonte) precisa de um InputStream ou um Reader
		//FileInputStream -ler as informações do arquivo
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties pros = new Properties();
			pros.load(fs);
			return pros;
		}catch(IOException ex) {
			throw new DbException(ex.getMessage());
			//vou lançar a minha exceção personalizada eu crie da classe DbException
		}
	}
	/*
	 * Connection, Statment, Result, sao recursos externos q nao sao controlados 
	 * pela JVM do java é preciso q seja fechado os recursos manualmente, para evitar 
	 * q o programa tenha algum tipo de vazamento de memoria.
	 */
	
	public static void closeStatment(Statement st) {
		if(st!=null) {
			try {
				st.close();
			}
			catch(SQLException ex) {
				throw new DbException(ex.getMessage());
			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null) {
			rs.close();
			}
		}catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
	}
}
