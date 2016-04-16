package lex;

public class Palavra extends Token{
	public final String lexema;
	public Palavra( int tipo, String lexema){
		super(tipo);
		this.lexema = lexema;
	}
}
