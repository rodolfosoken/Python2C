package sintaxe.lex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sintaxe.Acao;

public class LeitorTabelaAction {
	private Acao[][] tabela;
	private BufferedReader br;
	private String url;
	private String linha;
	private int contLinha;

	public LeitorTabelaAction(String url) {
		this.url = url;
		try {
			br = new BufferedReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Acao[][] criaTabela() throws IOException {
		contLinha = 0;
		while ((linha = br.readLine()) != null) {
			String s[] = linha.split(";");
			int i = 0;
			for (String string : s) {
				if (!string.isEmpty()) {
					tabela[contLinha][i++] = new Acao(string.trim().charAt(0),
							Character.digit(string.trim().charAt(1), 10));
				}
			}

			contLinha++;
		}

		return tabela;
	}

}
