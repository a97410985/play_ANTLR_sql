grammar HahaSql;

prog: selectStmt+;

selectStmt: SELECT columnList FROM TABLE = NAME ';';

columnList: columnRef (',' columnRef)*;

columnRef: FUNCTION '(' NAME ')' | NAME;

SELECT: 'select';
FROM: 'from';

FUNCTION: 'max' | 'min';

NAME: [a-zA-Z]+;
WS: [ \t\r\n]+ -> skip;