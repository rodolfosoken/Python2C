package lex;

public class Tipo { //especifica constantes para serem 
					//usadas no switch case do analisador sintático
	public final static int 
	NUM = 256, 		//Numeros
	ID=257, 		//identificadores
	//Palavras reservadas
	TRUE=258, 		
	FALSE = 259,
	IF = 260, 
	WHILE = 261, //todo o resto da tabela vai aqui.
	FLOAT = 262;
}
