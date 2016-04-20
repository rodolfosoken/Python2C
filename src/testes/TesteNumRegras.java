package testes;

import java.io.IOException;

import sintaxe.lex.NumRegras;

public class TesteNumRegras {
	public static void main(String[] args) throws IOException {
		NumRegras num = new NumRegras("src/testes/testeRegrasNum.txt");
		int regras[][] = num.numeraRegras();
		for (int i = 0; i < regras.length; i++) {
			System.out.println("Regra"+ i +" "+ regras[i][0]+": "+regras[i][1]);
			
		}
	}

}
