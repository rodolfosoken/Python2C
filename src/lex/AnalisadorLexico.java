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
			contLinha++; // se ocorrer um erro o contador poderá indicar qual a linha que tentou ler.
			linha = br.readLine(); // le uma nova linha do arquivo fonte
		} catch (IOException e) {
			System.out.println("erro ao ler aquivo fonte");
			e.printStackTrace();
		}
	}

	private void leChar() {
		if (indexChar < 0 || linha.isEmpty())
			leLinha(); // verifica se a linha já foi inicializada;
		if (indexChar < linha.length())	simbolo = linha.charAt(indexChar++ ); // le o caracter e depois incrementa o ponteiro;
		else leLinha(); // ao terminar de ler os caracteres da linha, ler a proxima linha
	}

	public Token analisa() {
		leChar();
		while (simbolo == ' ' || simbolo == '\t') leChar(); // elimina espaços em branco
		if (Character.isDigit(simbolo)) { //se o primeiro simbolo lido eh um numero, entao calcula-se o valor e retorna um numero
			int valor = 0;
			do{
				valor = 10*valor + Character.digit(simbolo, 10); //multiplica as dezenas e soma com o valor na base 10 do char lido.
				leChar(); // incrementa o ponteiro
			}while(Character.isDigit(simbolo));
			return new Num(valor);
		}
		if (Character.isLetter(simbolo)) { //se o primeiro simbolo for uma letra, entao devolver um identificador
			StringBuilder id = new StringBuilder(); //nao utilizei concatenacao por estar dentro de um loop
			do{
				id.append(simbolo); // concatena os simbolos para formar o identiicador
				leChar();			
			}while(Character.isLetterOrDigit(simbolo));
			Palavra p = (Palavra) tabela.get(id.toString()); //verifica na tabela se eh uma palavra reservada
			if(p != null) return p; //se foi encontrado, retornar o token
			p = new Palavra(Tipo.ID, id.toString()); // senao criar um token e cadastrar na tabela para devolver
			tabela.put(id.toString(), p);
/*Debug*/			System.out.println("Cadastrado na tabela: " + p);
			return p;
		}
		
		simbolo = ' ';
		return new Token(simbolo); // tudo que não for reconhecido como letra ou numero é retornado como um token 
									// com o proprio valor, pois provavelmente é
									// um operador.

	}

}
