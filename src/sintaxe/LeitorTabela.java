package sintaxe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import recursos.InfoTabela;

public class LeitorTabela {
	private final int altTab = InfoTabela.ALTURA;
	private final int lagTabAc = InfoTabela.LAG_ACTION;
	private final int lagTabGo = InfoTabela.LAG_GOTO;
	public Acao[][] tabelaAction = new Acao[altTab][lagTabAc];
	public Goto[][] tabelaGoto = new Goto[altTab][lagTabGo];
	private BufferedReader br;
	@SuppressWarnings("unused")
	private String url;
	private String linha;
	private int contLinha=0;

	public LeitorTabela(String url) throws IOException {
		this.url = url;
		try {
			br = new BufferedReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		criaTabela();
	}

	private void criaTabela() throws IOException {
		contLinha = 0;
		while ((linha = br.readLine()) != null && contLinha < altTab) {
			String s[] = linha.split(";");
			int i = 0;
			for (String string : s) {
				if ((!string.isEmpty()) && i < lagTabAc) { // delimitar para não pegar a goto
					String[] res = string.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"); //separa numeros e letras
					if(res[0].charAt(0)=='a') 
						tabelaAction[contLinha][i++] = new Acao('a', 0);
					else tabelaAction[contLinha][i++] = new Acao(res[0].charAt(0),Integer.parseInt(res[1]));
				} else if (!string.isEmpty()) {
					tabelaGoto[contLinha][i - lagTabAc] = new Goto(String.valueOf(i-lagTabAc),Integer.parseInt(string));
					i++;
				}else{
					i++;
				}

			}

			contLinha++;
		}

	}
}
