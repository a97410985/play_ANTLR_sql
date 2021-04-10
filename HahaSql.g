grammar HahaSql;

tokens {
	COLUMN
}

prog: selectStmt+;

selectStmt: SELECT columnList FROM tableRef ';';

columnList: columnRef (',' columnRef)*;

columnRef: FUNCTION '(' NAME ')' | NAME;

tableRef: NAME;

SELECT: 'select';
FROM: 'from';

FUNCTION: 'max' | 'min';

NAME: [a-zA-Z]+;
WS: [ \t\r\n]+ -> skip;