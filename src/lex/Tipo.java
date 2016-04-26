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
	DIVDIV = 279,
	VEZESVEZES = 280 ,
	MENORMENORIGUAL = 281  , 
	MAIORMAIORIGUAL =  282 , 
	VEZESVEZESIGUAL = 283 , 
	DIVDIVIGUAL = 284 ,
	IFT  = 285,
	DOISPONTOS= 286   ,
	ELIFT = 287 ,
	ELSET =  288,
	WHILET =  289,
	FORT  = 290,
	BRIDENT=  291 ,
	INPUT  = 292 ,
	PRINT  = 293 ,
	RANGE =  294,
	DEF  = 295 ;
}
