package lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AnalisadorLexico {
	public int contLinha = -1; // contador de linhas para o reportar erros
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
		reserva(new Palavra(Tipo.IGIG, "=="));
		reserva(new Palavra(Tipo.MAIORIG, ">="));
		reserva(new Palavra(Tipo.MENORIG, "<="));
		reserva(new Palavra(Tipo.DIFER, "!="));
		reserva(new Palavra(Tipo.AND, "and"));
		reserva(new Palavra(Tipo.OR, "or"));
		reserva(new Palavra(Tipo.NOT, "not"));
		reserva(new Palavra(Tipo.IN, "in"));
		reserva(new Palavra(Tipo.IS, "is"));
		reserva(new Palavra(Tipo.MAISIG, "+="));
		reserva(new Palavra(Tipo.MENOSIG, "-="));
		reserva(new Palavra(Tipo.VEZESIG, "*="));
		reserva(new Palavra(Tipo.ARRIGUAL, "@="));
		reserva(new Palavra(Tipo.DIVIG, "/="));
		reserva(new Palavra(Tipo.PERCIGUAL, "%="));
		reserva(new Palavra(Tipo.EIGUAL, "&="));
		reserva(new Palavra(Tipo.OUIGUAL, "|="));
		reserva(new Palavra(Tipo.POTIGUAL, "^="));
		reserva(new Palavra(Tipo.MENORMENOR, "<<"));
		reserva(new Palavra(Tipo.MAIORMAIOR, ">>"));
		reserva(new Palavra(Tipo.DIVDIV, "//"));
		reserva(new Palavra(Tipo.VEZESVEZES, "**"));
		reserva(new Palavra(Tipo.MENORMENORIGUAL, "<<="));
		reserva(new Palavra(Tipo.MAIORMAIORIGUAL, ">>="));
		reserva(new Palavra(Tipo.VEZESVEZESIGUAL, "**="));
		reserva(new Palavra(Tipo.DIVDIVIGUAL, "//="));
		reserva(new Palavra(Tipo.IFT, "if"));
		reserva(new Palavra(Tipo.DOISPONTOS, ":"));
		reserva(new Palavra(Tipo.ELIFT, "elif"));
		reserva(new Palavra(Tipo.ELSET, "else"));
		reserva(new Palavra(Tipo.WHILET, "while"));
		reserva(new Palavra(Tipo.FORT, "for"));
		reserva(new Palavra(Tipo.INPUT, "input"));
		reserva(new Palavra(Tipo.PRINT, "print"));
		reserva(new Palavra(Tipo.RANGE, "range"));
		reserva(new Palavra(Tipo.DEF, "def"));
		// colocar todas as palavras reservaddas aqui.
	}

	private void reserva(Palavra p) { // reserva a palavra
		tabela.put(p.lexema, p);
	}

	private void leLinha() { // le uma linha do código fonte
		try {
			indexChar = 0; // faz o ponteiro voltar ao inicio da linha
			contLinha++; // se ocorrer um erro o contador poderá indicar qual a
							// linha que tentou ler.
			linha = br.readLine(); // le uma nova linha do arquivo fonte
		} catch (IOException e) {
			System.out.println("erro ao ler aquivo fonte");
			e.printStackTrace();
		}
	}

	private void leChar() {
		if (indexChar < 0 || contLinha < 0){// verifica se a linha já foi
			contLinha = 0;							// inicializada;
			leLinha();
		}

		if (linha != null) {
			if (indexChar < linha.length())// verifica se o ponteiro chegou ao
											// fim da linha
				simbolo = linha.charAt(indexChar++); // le o caracter e depois
														// incrementa o
														// ponteiro;
			 else if (indexChar < linha.length() + 1) { //adiciona um token de quebra de linha
			 simbolo = '\n';
			 indexChar++;
			 }
			else {
				leLinha(); // ao terminar de ler os caracteres da linha, ler a
				// proxima linha
				if (linha != null && indexChar < linha.length()) simbolo = linha.charAt(indexChar++);
			}
		} else {
			linha = "$eof"; // termina a leitura do arquivo. Precisa começar com
							// um
							// simbolo que não é letra nem numero ou qualquer
							// palavra chave
		}

	}

	public Token analisa() {
		while (simbolo == ' ' || simbolo == '\t')
			leChar(); // elimina espaços em branco
		
		if (simbolo == '#') { // elimina comentários
			int atual = contLinha;
			leChar();
			while (atual == contLinha){ // ignora todo o resto da linha
				leChar(); // ignora comentários
				if(atual != contLinha) leChar();
				while(simbolo == '\t' || simbolo == ' ') leChar();
				if(simbolo=='#') atual = contLinha;
			}

		}

		if (Character.isDigit(simbolo)) { // se o primeiro simbolo lido eh um
											// numero, entao calcula-se o valor
											// e retorna um numero
			int valor = 0;
			do {
				valor = 10 * valor + Character.digit(simbolo, 10); // multiplica
																	// as
																	// dezenas e
																	// soma com
																	// o valor
																	// na base
																	// 10 do
																	// char
																	// lido.
				leChar(); // incrementa o ponteiro
			} while (Character.isDigit(simbolo));
			return new Num(valor);
		}
		if (Character.isLetter(simbolo)) { // se o primeiro simbolo for uma
											// letra, entao devolver um
											// identificador
			StringBuilder id = new StringBuilder();
			do {
				id.append(simbolo); // concatena os simbolos para formar o
									// identiicador
				leChar();
			} while (Character.isLetterOrDigit(simbolo));
			Palavra p = (Palavra) tabela.get(id.toString()); // verifica na
																// tabela se eh
																// uma palavra
																// reservada
			if (p != null)
				return p; // se foi encontrado, retornar o token
			p = new Palavra(Tipo.ID, id.toString()); // senao criar um token e
														// cadastrar na tabela
														// para devolver
			tabela.put(id.toString(), p);
			/// * Debug */ System.out.println("Cadastrado na tabela: " + p);
			return p;
		}

		// faz o look ahead e verifica se são os operadores de atribuição
		switch (simbolo) {
		case '=':
			leChar();
			if (simbolo == '=')
				return tabela.get("==");
			else
				return new Token('='); // o caracter na variavel simbolo sera
										// analisado na proxima iteração

		case '+':
			leChar();
			if (simbolo == '=')
				return tabela.get("+=");
			else
				return new Token('+');

		case '-':
			leChar();
			if (simbolo == '=')
				return tabela.get("-=");
			else
				return new Token('-');
		case '@':
			leChar();
			if (simbolo == '=')
				return tabela.get("@=");
			else
				return new Token('@');

		case '&':
			leChar();
			if (simbolo == '=')
				return tabela.get("&=");
			else
				return new Token('&');

		case '|':
			leChar();
			if (simbolo == '=')
				return tabela.get("|=");
			else
				return new Token('|');

		case '^':
			leChar();
			if (simbolo == '=')
				return tabela.get("^=");
			else
				return new Token('^');

		case '>':
			leChar();
			if (simbolo == '>') {
				leChar();
				if (simbolo == '=') {
					leChar(); // simbolo atual já foi analisado.
					return tabela.get(">>=");
				} else
					return tabela.get(">>");
			} else if (simbolo == '=') {
				leChar();
				return tabela.get(">=");
			} else
				return new Token('>');

		case '<':
			leChar();
			if (simbolo == '<') {
				leChar();
				if (simbolo == '=') {
					leChar(); // simbolo atual já foi analisado.
					return tabela.get("<<=");
				} else
					return tabela.get("<<");
			} else if (simbolo == '=') {
				leChar();
				return tabela.get("<=");
			} else
				return new Token('<');

		case '*':
			leChar();
			if (simbolo == '*') {
				leChar();
				if (simbolo == '=') {
					leChar(); // simbolo atual já foi analisado.
					return tabela.get("**=");
				} else
					return tabela.get("**");
			} else if (simbolo == '=') {
				leChar();
				return tabela.get("*=");
			} else
				return new Token('*');

		case '/':
			leChar();
			if (simbolo == '/') {
				leChar();
				if (simbolo == '=') {
					leChar(); // simbolo atual já foi analisado.
					return tabela.get("//=");
				} else
					return tabela.get("//");
			} else if (simbolo == '=') {
				leChar();
				return tabela.get("/=");
			} else
				return new Token('/');

		}

		if (!linha.equals("$eof")) { // verifica se a leitura do arquivo
										// terminou.
			if(simbolo == '\n') { //cria um token para quebra de linha e para identação;
				leChar();
				if(simbolo=='\t') return new Token(Tipo.BRIDENT);
				else return new Token(Tipo.BR);
			}
			
			Token t = new Token(simbolo);// tudo que não for reconhecido como
											// letra ou numero é retornado como
											// um token
			// com o proprio valor, pois provavelmente é
			// um operador.
			simbolo = ' ';
			return t;
		}
		return null; // se a linha retornar null é retornado null
	}

}
