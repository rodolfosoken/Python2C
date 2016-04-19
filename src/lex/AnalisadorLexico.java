package lex;

public class AnalisadorLexico {
	public int linha = 1; // contador de linhas para o reportar erros
	private char simbolo = ' '; // simbolo a ser analisado
	private TabelaSimbolos tabela = new TabelaSimbolos(null);

	public AnalisadorLexico() {
		inicializaTabela(); // inicializa a tabela com as palavras reservadas
	}

	private void inicializaTabela() {
		reservar(new Palavra(Tipo.FALSE, "false"));
		// colocar todas as palavras reservaddas aqui.
	}

	private void reservar(Palavra p) { //reserva as palavras
		tabela.put(p.lexema, p);
	}

}
