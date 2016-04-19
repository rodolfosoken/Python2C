package lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AnalisadorLexico {
	public int contLinha = 0; // contador de linhas para o reportar erros
	private String linha;
	private char simbolo = ' '; // simbolo a ser analisado
	private int indexChar = -1; // aponta para o caracter a ser lido
	private TabelaSimbolos tabela = new TabelaSimbolos(null);
	private BufferedReader br = null; // buffer para ler o cod fonte
	private String url; // caminho para o dir do codigo fonte

	public AnalisadorLexico(String url) {
		inicializaTabela(); // inicializa a tabela com as palavras reservadas
		this.url = url;
		try {
			br = new BufferedReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void inicializaTabela() {
		reserva(new Palavra(Tipo.FALSE, "false"));
		// colocar todas as palavras reservaddas aqui.
	}

	private void reserva(Palavra p) { // reserva a palavra
		tabela.put(p.lexema, p);
	}

	private void leLinha() { // le uma linha do código fonte
		try {
			indexChar = 0; // faz o ponteiro voltar ao inicio da linha
			contLinha++;
			linha = br.readLine();
		} catch (IOException e) {
			System.out.println("erro ao ler aquivo fonte");
			e.printStackTrace();
		}
	}

	private void leChar() {
		if (indexChar < 0)
			leLinha(); // verifica se a linha já foi inicializada;
		if (indexChar < linha.length())
			simbolo = linha.charAt(indexChar++); // le o caracter e depois
													// incrementa o ponteiro;
	}

	public Token analisa() {
		leChar();
		while (simbolo == ' ' || simbolo == '\t')
			leChar(); // elimina espaços em branco
		if (Character.isDigit(simbolo)) {
			

		}
		if (Character.isLetter(simbolo)) {

		}

		return null;

	}

}
