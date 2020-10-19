package db;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//for�ar a exce��o ser criada com construtor, informando uma mensagem
	public DbException(String msg) {
		super(msg);//ser� transmitira para super classe, � RumtimeException
	}

	
	
}




/*
ava.lang.Exception = checked

java.lang.RuntimeException = unchecked

Exce��es UNCHECKED : Representam defeitos no programa ( bugs) - muitas vezes argumentos inv�lidos
 passados ??para um m�todo n�o- privada, refletem erros na l�gica do seu programa e n�o pode ser 
 razoavelmente recuperado de em tempo de execu��o e s�o subclasses de RuntimeException , e
  geralmente s�o implementados usando IllegalArgumentException , NullPointerException , ou 
  IllegalStateException um m�todo n�o � obrigado a estabelecer uma pol�tica para as exce��es n�o 
  verificadas lan�adas por sua execu��o.

Exce��es CHECKED : Representam condi��es inv�lidas em �reas fora do controle imediato do programa
 ( problemas de entrada do usu�rio inv�lido , banco de dados, falhas de rede , arquivos ausentes, 
 entre outros) s�o subclasses de Exception um m�todo � obrigado a estabelecer uma pol�tica para 
 todas as exce��es verificadas lan�adas por sua implementa��o ( ou passar a exce��o verificada 
 mais acima na pilha , ou manipul�-lo de alguma forma)

*/