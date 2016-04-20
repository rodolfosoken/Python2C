package sintaxe.lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class NumRegras {
	private int[][] tabela = new int[100][2];
	private String[] nomes = new String[100];
	private Hashtable<String, Integer> nomesVar = new Hashtable<>();
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

	public int[][] numeraRegras() throws IOException {
		linha = br.readLine();
		do{
		simbolo = linha.charAt(++indexChar);
		}while(simbolo != '{');
		++indexChar;
		simbolo = linha.charAt(++indexChar);
		nomes = linha.substring(indexChar).split(" , ");
		int cont = 0;
		for (String string : nomes) nomesVar.put(string.trim(), cont++);	
		
		while ((linha = br.readLine()) != null) {
			indexChar = -1;
			do {
				simbolo = linha.charAt(++indexChar);
			} while (!Character.isLetter(simbolo));
			StringBuilder s = new StringBuilder();
			do {
				s.append(simbolo);
				simbolo = linha.charAt(++indexChar);
			} while (simbolo != '-');
			indexChar += 3;
			simbolo = linha.charAt(indexChar);
			int contWord = linha.substring(indexChar).split(" ").length;
			tabela[contLinha][0] = nomesVar.get(s.toString().trim()); 
			tabela[contLinha++][1] = (contWord - 1);
			
		}
		
		int[][]tabela2 = new int[contLinha][2];
		for (int i = 0; i < contLinha; i++) {
			tabela2[i][0]=tabela[i][0];
			tabela2[i][1]=tabela[i][1];
		}
		return tabela2;
	}

}
