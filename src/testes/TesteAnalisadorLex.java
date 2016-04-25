package testes;

import lex.AnalisadorLexico;
import lex.Token;

public class TesteAnalisadorLex {

	public static void main(String[] args) {
		AnalisadorLexico lex = new AnalisadorLexico("src/testes/fonteTeste.txt");
		while (true) {
			Token t = lex.analisa();
			if(t == null) break;
			System.out.println(t);
		}

	}

}
