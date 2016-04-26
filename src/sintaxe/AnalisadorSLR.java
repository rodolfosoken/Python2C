package sintaxe;

import java.io.IOException;
import java.util.Stack;

import lex.AnalisadorLexico;
import lex.Token;

public class AnalisadorSLR {

	private AnalisadorLexico lex;
	private String urlRegras;

	public AnalisadorSLR(AnalisadorLexico lex, String urlRegras) {
		this.lex = lex;
		this.urlRegras = urlRegras;
		
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
										// tabela.. na tabela
			if(tipoToken > 255) tipoToken -= 255;  // para os tokens com valores reservados podemos colocar na ordem que desejarmos
			else if (tipoToken < 255 && tipoToken < 48 ) tipoToken -= 4; //para os tokens com valores basicos precisamos coloca-los ordenados
			else if(tipoToken == 59) tipoToken = 45;
			else if(tipoToken > 59 && tipoToken < 63) tipoToken -= 14;
			else if(tipoToken == 91) tipoToken = 49;
			else if(tipoToken == 93) tipoToken = 50;
			Acao acao = action[topo][tipoToken];
			if (acao.operacao == 'S') {
				pilha.push(acao.numero);
				token = lex.analisa();
			}else if(acao.operacao == 'R'){
				for (int i = 0; i < qtdRegra[acao.numero][1]; i++) { //qtdRegra[acao.numero][1] eh a quantidade de tokens que a regra do reduce possui
					pilha.pop();	//desempilha os simbolos da producao				
				}
				topo = pilha.pop();
				pilha.push(go[topo][qtdRegra[acao.numero][0]].estado); //qtdRegra[acao.numero][0] eh o numero da regra no indice da tabela goto
				System.out.println("Reduce com a regra"+ qtdRegra[acao.numero][0]);
			}else if(acao.operacao == 'a') System.out.println("Analise terminou");
			else System.out.println("erro");
			
		}

	}

}
