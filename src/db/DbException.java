package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//forçar a exceção ser criada com construtor, informando uma mensagem
	public DbException(String msg) {
		super(msg);//será transmitira para super classe, é RumtimeException
	}

	
	
}




/*
ava.lang.Exception = checked

java.lang.RuntimeException = unchecked

Exceções UNCHECKED : Representam defeitos no programa ( bugs) - muitas vezes argumentos inválidos
 passados ??para um método não- privada, refletem erros na lógica do seu programa e não pode ser 
 razoavelmente recuperado de em tempo de execução e são subclasses de RuntimeException , e
  geralmente são implementados usando IllegalArgumentException , NullPointerException , ou 
  IllegalStateException um método não é obrigado a estabelecer uma política para as exceções não 
  verificadas lançadas por sua execução.

Exceções CHECKED : Representam condições inválidas em áreas fora do controle imediato do programa
 ( problemas de entrada do usuário inválido , banco de dados, falhas de rede , arquivos ausentes, 
 entre outros) são subclasses de Exception um método é obrigado a estabelecer uma política para 
 todas as exceções verificadas lançadas por sua implementação ( ou passar a exceção verificada 
 mais acima na pilha , ou manipulá-lo de alguma forma)

*/