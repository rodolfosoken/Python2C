package testes;

import lex.AnalisadorLexico;

public class TesteAnalisadorLex {
	
	public static void main(String[] args) {
		AnalisadorLexico lex = new AnalisadorLexico("src/testes/fonteTeste.txt");
		System.out.println(lex.analisa());
		System.out.println(lex.analisa());
	}

}
