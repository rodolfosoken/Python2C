package lex;

public class Tipo { //especifica constantes para serem 
					//usadas no switch case do analisador sintático
	public final static int 
	BR = 256,
	NUM = 257, 		//Numeros
	ID=258, 		//identificadores
	IGIG=259,		//igualigual
	MAIORIG = 260,  //maior igual
	MENORIG = 261,	//MENOR IGUAL
	DIFER = 262,	//DIFERENTE
	AND = 263,		//AND
	OR = 264, 		//OR
	NOT = 265, 		//NOT
	IN = 266,		//IN
	IS = 267,		//IS
	MAISIG = 268, 	//MAISIGUAL
	MENOSIG = 269,	//MENOSIGUAL
	VEZESIG = 270,	//VEZESIGUAL
	ARRIGUAL = 271,
	DIVIG = 272,	//DIVIDEIGUAL
	PERCIGUAL = 273, 
	EIGUAL = 274  , 
	OUIGUAL = 275 , 
	POTIGUAL = 276 , 
	MENORMENORIGUAL = 277  , 
	MAIORMAIORIGUAL =  278 , 
	VEZESVEZESIGUAL = 279 , 
	DIVDIVIGUAL = 280 ,
	IFT  = 281,
	DOISPONTOS= 282   ,
	ELIFT = 283 ,
	ELSET =  284,
	WHILET =  285,
	FORT  = 286,
	BRIDENT=  287 ,
	INPUT  = 289 ,
	PRINT  = 290 ,
	RANGE =  291,
	DEF  = 292 ;
}
