package sintaxe.lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class NumRegras {
	private int[] tabela = new int[100];
	private int contLinha = 0;
	private BufferedReader br;
	private String url;
	private String linha;
	private char simbolo = ' '; // simbolo a ser analisado
	private int indexChar = -1; // aponta para o caracter a ser lido

	public NumRegras(String url) {
		this.url = url;
		try {
			br = new BufferedReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int[] numeraRegras() throws IOException {
		while ((linha = br.readLine()) != null) {
			indexChar = 0;
			simbolo = linha.charAt(++indexChar);
			do {
				simbolo = linha.charAt(++indexChar);
			} while (simbolo != '>');
			++indexChar;
			simbolo = linha.charAt(++indexChar);
			int contWord = linha.substring(indexChar).split(" ").length;
			tabela[contLinha++] = (contWord - 1);
		}
		int[]tabela2 = new int[contLinha];
		for (int i = 0; i < contLinha; i++) {
			tabela2[i]=tabela[i];
		}
		return tabela2;
	}

}
