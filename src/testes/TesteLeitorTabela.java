package testes;

import java.io.IOException;

import sintaxe.Acao;
import sintaxe.Goto;
import sintaxe.lex.LeitorTabela;

public class TesteLeitorTabela {
	public static void main(String[] args) throws IOException {
		LeitorTabela leitor = new LeitorTabela("src/testes/Pasta1.csv");
		leitor.criaTabelaAction();
		Acao[][] action = leitor.tabelaAction;
		Goto[][] go = leitor.tabelaGoto;

		for (int i = 0; i < action.length; i++) {
			for (int j = 0; j < action[i].length; j++) {
				if (action[i][j] != null)
					System.out.println(action[i][j]);
			}
		}
		System.out.println("-------------");
		for (int k = 0; k < go.length; k++) {
			for (int k2 = 0; k2 < go[k].length; k2++) {
				if (go[k][k2] != null)
					System.out.println(go[k][k2]);

			}
		}
	}

}
