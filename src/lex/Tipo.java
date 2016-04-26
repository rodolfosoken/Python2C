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
	MENORMENOR = 277,
	MAIORMAIOR = 278,
	MENORMENORIGUAL = 279  , 
	MAIORMAIORIGUAL =  280 , 
	VEZESVEZESIGUAL = 281 , 
	DIVDIVIGUAL = 282 ,
	IFT  = 283,
	DOISPONTOS= 284   ,
	ELIFT = 285 ,
	ELSET =  286,
	WHILET =  287,
	FORT  = 288,
	BRIDENT=  289 ,
	INPUT  = 290 ,
	PRINT  = 291 ,
	RANGE =  292,
	DEF  = 293 ;
}
