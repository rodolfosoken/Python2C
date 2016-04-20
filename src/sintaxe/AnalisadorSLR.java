package sintaxe;

import java.util.Stack;

import lex.AnalisadorLexico;
import lex.Token;

public class AnalisadorSLR {

	private AnalisadorLexico lex;

	public AnalisadorSLR(AnalisadorLexico lex) {
		this.lex = lex;
	}

	private Estado action[][] = new Estado[100][100];

	void analisa() {
		while (true) {
			Stack<Estado> pilha = new Stack<>();
			int top = pilha.pop().numero;
			Token token = lex.analisa();
			if (action[top][token.tipo].operacao == 's') {

			}

		}

	}

}
