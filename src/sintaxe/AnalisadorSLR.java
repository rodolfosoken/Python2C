package sintaxe;

import java.io.IOException;
import java.util.Stack;

import lex.AnalisadorLexico;
import lex.Tipo;
import lex.Token;
import recursos.InfoTabela;

public class AnalisadorSLR {

	private AnalisadorLexico lex;
	private String urlRegras;
	private Acao action[][] = new Acao[InfoTabela.ALTURA][InfoTabela.LAG_ACTION];
	private Goto go[][] = new Goto[InfoTabela.ALTURA][InfoTabela.LAG_GOTO];

	public AnalisadorSLR(AnalisadorLexico lex) throws IOException {
		this.lex = lex;
		this.urlRegras = InfoTabela.urlRegrasNum;
		LeitorTabela leitor = new LeitorTabela(InfoTabela.urlTabela);
		action = leitor.tabelaAction;
		go = leitor.tabelaGoto;
	}


	public void analisa() throws IOException {
		NumRegras num = new NumRegras(this.urlRegras);
		int [][]qtdRegra = num.numeraRegras();
		Token token = lex.analisa(); // pega o primeiro simbolo da entrada.
		while(token.tipo==Tipo.BR)token = lex.analisa();
		Stack<Integer> pilha = new Stack<>(); // pilha de estados
		pilha.push(0); // estado inicial
		while (true) {
			int topo = pilha.pop(); // pega o topo da pilha
			pilha.push(topo); // mantem o estado da pilha
			
			int tipoToken = token.tipo; // pega o tipo que será o index da
										// tabela.. na tabela
			if(tipoToken > 255) tipoToken -= 256;  // para os tokens com valores reservados podemos colocar na ordem que desejarmos
			else if (tipoToken == 58 ) tipoToken = 30; //para os tokens com valores basicos precisamos coloca-los ordenados
			else if(tipoToken == 59) tipoToken = 49; 	//;
			else if(tipoToken > 59 && tipoToken < 63) tipoToken -= 11; // >=<
			else if(tipoToken == 91) tipoToken = 52;	//[
			else if(tipoToken == 93) tipoToken = 53;	//]
			else if(tipoToken == 94) tipoToken = 54;	//^
			else if(tipoToken == 38) tipoToken = 55;	//&
			Acao acao = action[topo][tipoToken];
			if(acao == null){ 
				System.out.println("ERRO! Sem ação em "+"action["+topo+"]["+tipoToken+"]"+
			": Ao ler linha "+lex.contLinha);
				break;
			}
			if (acao.operacao == 'S') {
				pilha.push(acao.numero);
				Token ant = token;
				token = lex.analisa();
				while(ant.tipo==Tipo.BR && token.tipo==Tipo.BR) token=lex.analisa(); //ignora as quebras de linhas a mais
				System.out.println("Shift: "+acao.numero);
			}else if(acao.operacao == 'R'){
				for (int i = 0; i < qtdRegra[acao.numero][1]; i++) { //qtdRegra[acao.numero][1] eh a quantidade de tokens que a regra do reduce possui
					pilha.pop();	//desempilha os simbolos da producao
				}
				topo = pilha.pop();
				pilha.push(topo);
				pilha.push(go[topo][qtdRegra[acao.numero][0]].estado); //qtdRegra[acao.numero][0] eh o numero da regra no indice da tabela goto
				System.out.println("Reduce com a regra "+acao.numero);
			}else if(acao.operacao == 'a') {System.out.println("Analise terminou");break;}
			else if(acao.operacao=='{'){
				System.out.println("Conflito em action["+topo+"]["+tipoToken+"]: Ao ler linha "+lex.contLinha);
				break;
			}
			else{ System.out.println("erro");
				break;
			}
		}

	}

}
