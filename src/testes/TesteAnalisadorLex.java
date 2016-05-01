package testes;

import lex.AnalisadorLexico;
import lex.Tipo;
import lex.Token;

public class TesteAnalisadorLex {

	public static void main(String[] args) {
		AnalisadorLexico lex = new AnalisadorLexico("src/testes/fonteTeste.txt");
		while (true) {
			Token t = lex.analisa();
			if(t.tipo == Tipo.EOF) break;
			if(t.tipo==Tipo.BR || t.tipo == Tipo.BRIDENT) System.out.println("");
			System.out.print(t +" ");
		}

	}

}
