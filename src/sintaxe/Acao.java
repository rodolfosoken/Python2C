package sintaxe;

public class Acao {
	public final char operacao;
	public final int numero;
	
	public Acao(char operacao, int numero){
		this.operacao = operacao;
		this.numero = numero;
	}
	
	public String toString(){
		return (operacao+String.valueOf(numero));
	}

}
