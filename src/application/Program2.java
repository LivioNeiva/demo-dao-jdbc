package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entidade.Departamento;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao depDao = DaoFactory.createDepartamentoDao();
		/*
		System.out.println("\n === TESTANDO 1 SELLER INSERT ===");

		Departamento dep = new Departamento(null, "Tecnologia");
		System.out.println(dep);
		depDao.insert(dep);
		System.out.println("ID NOVO OBJ INSERIDO: "+dep.getId());
		*/
		/*
		System.out.println("\n === TESTANDO 2 DEPARTAMENTO UPATE ===");
		
		Departamento dep2 = new Departamento(13, "Cozinha");
		
		depDao.update(dep2);
		System.out.println("\n === UPATE FEITO COM SUCESSO ===");
		*/
		System.out.println("\n === TESTANDO 3 DEPARTAMENTO DELETE ===");
		
		depDao.DeleteById(13);
				
		System.out.println("\n === TESTANDO 3 DEPARTAMENTO findById implementation ===");
		
		Departamento dep = new Departamento(3, null);
		
		System.out.println(depDao.findById(dep.getId()));
		
		System.out.println("\n=== TEST 3: findById =======");
		dep = depDao.findById(1);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 4: findByAll =======");
		
		List<Departamento> depList = new ArrayList<>();
		depList = depDao.findAll();
		
		for(Departamento dep2 : depList) {
			System.out.println(dep2);
		}
		
		
	}

}
