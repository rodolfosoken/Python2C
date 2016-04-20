package sintaxe;

import java.io.IOException;
import java.util.Stack;

import lex.AnalisadorLexico;
import lex.Token;
import sintaxe.lex.NumRegras;

public class AnalisadorSLR {

	private AnalisadorLexico lex;

	public AnalisadorSLR(AnalisadorLexico lex) {
		this.lex = lex;
	}

	private Acao action[][] = new Acao[200][100];
	private Goto go[][] = new Goto[200][100];

	void analisa() throws IOException {
		NumRegras num = new NumRegras("src/testes/testeRegrasNum.txt");
		int [][]qtdRegra = num.numeraRegras();
		Token token = lex.analisa(); // pega o primeiro simbolo da entrada.
		while (true) {
			Stack<Integer> pilha = new Stack<>(); // pilha de estados
			int topo = pilha.pop(); // pega o topo da pilha
			int tipoToken = token.tipo; // pega o tipo que será o index da
										// tabela.. na tabela podemos mudar a
										// ordem das colunas do action para
										// corresponder aos numeros dos tokens
			if (tipoToken < 255)	//se ele for menor que 255 nao eh um terminal 
				tipoToken -= 32;	//os caracteres de operadores estao depois de 32
			Acao acao = action[topo][tipoToken];
			if (acao.operacao == 's') {
				pilha.push(acao.numero);
				token = lex.analisa();
			}else if(acao.operacao == 'r'){
				for (int i = 0; i < qtdRegra[acao.numero][1]; i++) {
					pilha.pop();	//desempilha os simbolos da producao				
				}
				topo = pilha.pop();
				
				pilha.push(go[topo][qtdRegra[acao.numero][0]].estado);
			}
			
			
		}

	}

}
