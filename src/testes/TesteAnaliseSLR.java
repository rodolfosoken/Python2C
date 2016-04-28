package testes;

import java.io.IOException;

import lex.AnalisadorLexico;
import sintaxe.AnalisadorSLR;

public class TesteAnaliseSLR {
	public static void main(String[] args) throws IOException {
		AnalisadorLexico lex = new AnalisadorLexico("src/testes/fonteTeste.txt");
		AnalisadorSLR sintax = new AnalisadorSLR(lex, "src/testes/testeRegrasNum.txt", "src/testes/Pasta1.csv");
		sintax.analisa();
	}

}
