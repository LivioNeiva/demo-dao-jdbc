package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entidade.Departamento;
import model.entidade.Seller;

public class Program {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		
		System.out.println(obj);
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		System.out.println(seller);
		//criando obj tipo SellerDao, obj de comunica��o entre as classes e banco de dados
		//instanciando o obj com classe Daofactory com m�todo statico
		//metodo statico createSellerDao() instancia classe SellerDaoJDBC com argumento getConnection
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TESTANDO SELLER FINDBYID ===");
		
		Seller seller1 = sellerDao.findById(3);
		System.out.println(seller1);
		

	}

}
