package Assignment2;

public class SyncronizingSets {
	public static final TokenCode[] program = { TokenCode.EOF};
	public static final TokenCode[] variableDeclarations = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE, TokenCode.STATIC};
	public static final TokenCode[] _variableDeclarations = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE, TokenCode.STATIC };
	public static final TokenCode[] type = { TokenCode.IDENTIFIER};
	public static final TokenCode[] variableList = { TokenCode.SEMICOLON };
	public static final TokenCode[] _variableList = { TokenCode.SEMICOLON };
	public static final TokenCode[] variable = { TokenCode.COMMA};
	public static final TokenCode[] _variable = { TokenCode.COMMA};
	public static final TokenCode[] methodDeclarations = { TokenCode.RBRACE};
	public static final TokenCode[] moreMethodDeclarations = { TokenCode.RBRACE};
	public static final TokenCode[] _moreMethodDeclarations = { TokenCode.RBRACE};
	public static final TokenCode[] methodDeclaration = { TokenCode.STATIC};
	public static final TokenCode[] methodReturnType = { TokenCode.IDENTIFIER};
	public static final TokenCode[] parameters = { TokenCode.RPAREN};
	public static final TokenCode[] parameterList = { TokenCode.RPAREN };
	public static final TokenCode[] _parameterList = { TokenCode.RPAREN};
	public static final TokenCode[] statementList = { TokenCode.RBRACE};
	public static final TokenCode[] _statementList = { TokenCode.RBRACE};
	public static final TokenCode[] statement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE };
	public static final TokenCode[] idStartingStatement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] restOfIdStartingStatement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] _restOfIdStartingStatement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] optionalExpression = { TokenCode.SEMICOLON};
	public static final TokenCode[] statementBlock = {TokenCode.ELSE, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] incrDecrVar = {TokenCode.RPAREN, TokenCode.LBRACE};
	public static final TokenCode[] optionalElse = {TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] expressionList = {TokenCode.RPAREN};
	public static final TokenCode[] moreExpressions = {TokenCode.RPAREN};
	public static final TokenCode[] expression = {TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] _expression = {TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE };
	public static final TokenCode[] simpleExpression = {TokenCode.RELOP, TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] _simpleExpression = {TokenCode.RELOP, TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public static final TokenCode[] term = {TokenCode.ADDOP};
	public static final TokenCode[] _term = {TokenCode.ADDOP, TokenCode.IDENTIFIER, TokenCode.NUMBER, TokenCode.LPAREN };
	public static final TokenCode[] factor = {TokenCode.MULOP};
	public static final TokenCode[] idStartingFactor = {TokenCode.MULOP, TokenCode.IDENTIFIER };
	public static final TokenCode[] restOfIdStartingFactor = {TokenCode.MULOP};
	public static final TokenCode[] variableLoc = {TokenCode.INCDECOP, TokenCode.ASSIGNOP};
	public static final TokenCode[] sign = {TokenCode.NUMBER, TokenCode.LPAREN, TokenCode.NOT, TokenCode.IDENTIFIER};
	
	
	
}

