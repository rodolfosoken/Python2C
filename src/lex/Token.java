package lex;

public class Token {
	public final int tipo;
	public Token(int tipo){
		this.tipo = tipo;
	}
	
	public String toString(){
		return "<"+Character.toString((char)this.tipo)+">";
	}

}
