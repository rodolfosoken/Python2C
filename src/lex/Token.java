package lex;

public class Token {
	public final int tipo;
	public Token(int tipo){
		this.tipo = tipo;
	}
	
	public String toString(){
		if (this.tipo == Tipo.BR) return "<br>";
		if(this.tipo == Tipo.BRIDENT) return "<brIdent>";
		return "<"+Character.toString((char)this.tipo)+">";
	}

}
