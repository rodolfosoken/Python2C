package sintaxe;

import java.util.Stack;

import lex.AnalisadorLexico;
import lex.Token;

public class AnalisadorSLR {

	private AnalisadorLexico lex;

	public AnalisadorSLR(AnalisadorLexico lex) {
		this.lex = lex;
	}

	private Estado action[][] = new Estado[200][100];

	void analisa() {
		while (true) {
			Stack<Estado> pilha = new Stack<>();
			int top = pilha.pop().numero;
			Token token = lex.analisa();
			int tipoToken = token.tipo;
			if(tipoToken < 255) tipoToken-=40; //s� para testes, mas a aquela ideia inicial estava certa...se ele achar um n-terminal que n�o seja um dos que n�s declaramos, vai dar erro
			if (action[top][tipoToken].operacao == 's') {

			}

		}

	}

}
