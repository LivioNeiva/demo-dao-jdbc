package db;

public class DbIntegrityException extends RuntimeException {
	
	//criando uma excer��o personalizada de integridade referencial
	/*
	 * criaremos essa excer��o personalizada, pois � muito comum q aconte�a erros
	 * de integridade referencial.
	 * integridade referencial  - sao erros acontece ao apagar um registro q seja
	 * chave estrangeira em outra tabela.
	 * ex. naopodemos apagar o departamento 2 da tabela departamento, pois a mesma
	 * � chave estrangeira de dois registro na tabela seller(vendedor)
	 */
	
	//https://www.devmedia.com.br/entendendo-serialversionuid-em-java/29017
	private static final long serialVersionUID = 1L;
	
	//passando a msg para construtor padr�o
	public DbIntegrityException (String msg) {
		super((msg));
	}
	
}
