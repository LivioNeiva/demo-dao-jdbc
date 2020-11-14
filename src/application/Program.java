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
		/*
		System.out.println("\n === TESTANDO 3 SELLER findAll ===");
		
		List<Seller> seller2 = sellerDao.findAll();
		
		for(Seller vend : seller2) {
			System.out.println(vend);
		}
		*/
		
		Departamento d = new Departamento(9,null);
		/*
		System.out.println("\n === TESTANDO 4 SELLER INSERT ===");
		Seller seller3 = new Seller();
		seller3.setNome("Durvalina Medeiros");
		seller3.setEmail("dm@hotmail.com");
		seller3.setBirthDate(new Date());
		seller3.setSalario(2000.0);
		seller3.setDepartamento(d);
		
		sellerDao.insert(seller3);
		System.out.println("NOVO ID INSERIDO: "+seller3.getId());
		*/
		
		//outro modelo de instanciar obj para inserir as informações
		/*
		Seller newSeller = new Seller(null,"Rodolfo maia", "maia@ig.com.br", new Date(), 8000.0, d );
		sellerDao.insert(newSeller);
		System.out.println("NOVO ID INSERIDO: "+newSeller.getId());
		*/
		
		System.out.println("\n === TESTANDO 5 SELLER UPDATE ===");
		
		Seller newSeller2  = new Seller(12, "Francisco Antonio", "antonio@bala.com.br", new Date(), 1000.0, d );
		
		sellerDao.update(newSeller2);
		System.out.println("ID modificado: "+newSeller2.getId());
		
		//Exemplo 2
		System.out.println(seller);
		seller = sellerDao.findById(9);
		System.out.println(seller);
		seller.setEmail("RCaio@uol.com.br");
		sellerDao.update(seller);
		System.out.println("UPDATE COM SUCESSO: ");
		System.out.println(seller);
		
		System.out.println("\n === TESTANDO 5 SELLER DELETER ===");
		
		System.out.println(sellerDao.findById(16));
		sellerDao.deleteById(16);
		System.out.println(sellerDao.findById(16));
	
 	}

}
