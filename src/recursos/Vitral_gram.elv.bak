/* Reglas Lexicográficas Auxiliares */



%  /* Reglas Lexicográficas */

br	1	;
numero	2	;
letra	3	;
igualigual	4	;
maiorigual	5	;
menorigual	6	;
diferente	7	;
and	8	;
or	9	;
not	11	;
in	12	;
is	13	;
maisigual	14	;
menosigual	15	;
vezesigual	16	;
arrigual	17	;
divideigual	18	;
percigual	19	;
eigual	21	;
ouigual	22	;
potigual	23	;
menormenor	24	;
maiormaior	25	;
divdiv	26	;
vezesvezes	27	;
menormenorigual	28	;
maiormaiorigual	29	;
vezesvezesigual	31	;
divdivigual	32	;
ift	33	;
doispontos	34	;
elift	35	;
elset	36	;
whilet	37	;
fort	38	;
brident	39	;
input	41	;
print	42	;
range	43	;
def	44	;
parabr	45	;
parfec	46	;
multiplica	47	;
mais	48	;
virgula	49	;
menos	51	;
ponto	52	;
divide	53	;
pontovirgula	54	;
maior	55	;
igual	56	;
menor	57	;
colabr	58	;
colfec	59	;
pot	61	;
ebin	62	;


%%  /* Reglas Sintácticas */


begin S -> Comandos;
Comandos -> Comando br | Comando pontovirgula Comandos | Comando br Comandos | Comando brident Comandos;
Comando -> Expr_star | IncExpr | IF | While | For | Func | Def;

Expr_star -> Var_star | Var_star igual Expr_star | Var_star Op Expr_star ;
ExprComp -> Var_star OpComp ExprComp_star;
ExprComp_star -> Var_star OpComp ExprComp_star | Var_star;
IncExpr ->Var OpIg VarNum ;

Var_star -> VarNum virgula Var_star | colabr Var_star colfec |parabr Var_star parfec | VarNum | Var colabr VarNum colfec;
VarNum -> NUM | Var ;
Var -> Id;
NUM -> Int | Float;
IntId -> Int | Id;
Float -> Int ponto Int;
Int -> numero Int |numero;
Id -> letra IntId | letra;

Op -> mais | menos  | divide | multiplica| divdiv | vezesvezes| pot | ebin | menormenor | maiormaior | OpComp ;
OpComp -> menor | maior | igualigual |  maiorigual | menorigual  | diferente | and | or | not | in | is;
OpIg -> maisigual | menosigual | vezesigual | arrigual | divideigual | percigual | eigual | ouigual | potigual | menormenorigual | maiormaiorigual | vezesvezesigual | divdivigual;

IF -> ift ExprComp doispontos  Corpo Elif Else;
Elif -> elift ExprComp doispontos Corpo Elif | ;
Else -> elset ExprComp doispontos Corpo | ;

While ->  whilet ExprComp doispontos Corpo Else ;
For -> fort Id in FuncRange  doispontos Corpo | fort Id in colabr Var_star colfec doispontos Corpo | fort Id in parabr Var_star parfec  doispontos Corpo ;

Corpo -> brident Comandos | Comando br |  Comando pontovirgula;

Func -> input parabr ExprComp_star parfec  | print parabr Expr_star parfec | Id parabr Expr_star parafec | Id parabr parafec |FuncRange | Func virgula Func | colabr Func colfec  | parabr Func parfec | Id igual Func ;
FuncRange -> range parabr IntId parfec | range parabr IntId virgula IntId parfec | range parabr IntId virgula  IntId virgula  IntId parfec;

Def -> def Id parabr Var_star parfec doispontos Corpo | def Id parabr parfec doispontos  Corpo;





%%  /* Reglas Semánticas */



%%  /* Operadores de Código Intermedio */



%%

