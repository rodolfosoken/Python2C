package lex;

public class Num extends Token {
	public final int valor;

	Num(int valor) {
		super(Tipo.NUM);
		this.valor = valor;
	}

	public String toString() {
		return "" + valor;
	}
}
