package sintaxe.lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sintaxe.Acao;
import sintaxe.Goto;

public class LeitorTabela {
	public Acao[][] tabelaAction = new Acao[163][51];
	public Goto[][] tabelaGoto = new Goto[163][27];
	private BufferedReader br;
	private String url;
	private String linha;
	private int contLinha=0;

	public LeitorTabela(String url) {
		this.url = url;
		try {
			br = new BufferedReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void criaTabelaAction() throws IOException {
		contLinha = 0;
		while ((linha = br.readLine()) != null && contLinha < 163) {
			String s[] = linha.split(";");
			int i = 0;
			for (String string : s) {
				if ((!string.isEmpty()) && i < 51) { // delimitar para não pegar a goto
					String[] res = string.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"); //separa numeros e letras
					if(res[0].charAt(0)=='a') 
						tabelaAction[contLinha][i++] = new Acao('a', 0);
					else tabelaAction[contLinha][i++] = new Acao(res[0].charAt(0),Integer.parseInt(res[1]));
				} else if (!string.isEmpty()) {
					tabelaGoto[contLinha][i - 51] = new Goto(String.valueOf(i-51),Integer.parseInt(string));
					i++;
				}else{
					i++;
				}

			}

			contLinha++;
		}

	}
}
