package application;

import java.util.Date;
import java.util.List;

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
		//criando obj tipo SellerDao, obj de comunicação entre as classes e banco de dados
		//instanciando o obj com classe Daofactory com método statico
		//metodo statico createSellerDao() instancia classe SellerDaoJDBC com argumento getConnection
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TESTANDO 1 SELLER FINDBYID ===");
		
		Seller seller1 = sellerDao.findById(3);
		System.out.println(seller1);
		
		System.out.println("\n === TESTANDO 2 SELLER DEPARTAMENTO FINDBYID ===");
		
		Departamento dep = new Departamento(4, null);//departamento apenas com id
		System.out.println(dep);
		System.out.println();
		
		List<Seller> list = sellerDao.findByDepartment(dep);//lista recebendo vendedores lotados no departamento 4
		
		for(Seller vendedor : list) {
			System.out.println(vendedor);
		}
		
		System.out.println("\n === TESTANDO 3 SELLER findAll ===");
		
		List<Seller> seller2 = sellerDao.findAll();
		
		for(Seller vend : seller2) {
			System.out.println(vend);
		}
	}

}
