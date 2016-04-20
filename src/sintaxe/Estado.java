package sintaxe;

public class Estado {
	public final char operacao;
	public final int numero;
	
	public Estado(char operacao, int numero){
		this.operacao = operacao;
		this.numero = numero;
	}
	
	public String toString(){
		return (operacao+String.valueOf(numero));
	}

}
