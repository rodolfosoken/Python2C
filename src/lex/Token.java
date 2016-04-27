package lex;

public class Token {
	public final int tipo;
	public Token(int tipo){
		this.tipo = tipo;
	}
	
	public String toString(){
		if (this.tipo == 236) return "<br>";
		return "<"+Character.toString((char)this.tipo)+">";
	}

}
