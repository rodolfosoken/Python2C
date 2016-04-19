package lex;

import java.util.Hashtable;

public class TabelaSimbolos {
	private Hashtable<String, Token> tabela;
	protected TabelaSimbolos pai;

	public TabelaSimbolos(TabelaSimbolos pai) {
		tabela = new Hashtable<>();
		this.pai = pai;
	}

	public void put(String s, Token t) {
		tabela.put(s, t);
	}

	public Token get(String s) {
		for (TabelaSimbolos tab = this; tab != null; tab = tab.pai) {
			Token achado = tab.tabela.get(s);
			if (achado != null)
				return achado;
		}
		return null;
	}
}
