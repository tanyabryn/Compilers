package Assignment2;

public class SyncronizingSets {
	public TokenCode[] program = { TokenCode.EOF};
	public TokenCode[] variableDeclarations = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE, TokenCode.STATIC};
	public TokenCode[] _variableDeclarations = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE, TokenCode.STATIC };
	public TokenCode[] type = { TokenCode.IDENTIFIER};
	public TokenCode[] variableList = { TokenCode.SEMICOLON };
	public TokenCode[] _variableList = { TokenCode.SEMICOLON };
	public TokenCode[] variable = { TokenCode.COMMA};
	public TokenCode[] _variable = { TokenCode.COMMA};
	public TokenCode[] methodDeclarations = { TokenCode.RBRACE};
	public TokenCode[] moreMethodDeclarations = { TokenCode.RBRACE};
	public TokenCode[] _moreMethodDeclarations = { TokenCode.RBRACE};
	public TokenCode[] methodDeclaration = { TokenCode.STATIC};
	public TokenCode[] methodReturnType = { TokenCode.IDENTIFIER};
	public TokenCode[] parameters = { TokenCode.RPAREN};
	public TokenCode[] parameterList = { TokenCode.RPAREN };
	public TokenCode[] _parameterList = { TokenCode.RPAREN};
	public TokenCode[] statementList = { TokenCode.RBRACE};
	public TokenCode[] _statementList = { TokenCode.RBRACE};
	public TokenCode[] statement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE };
	public TokenCode[] idStartingStatement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] restOfIdStartingStatement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] _restOfIdStartingStatement = { TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] optionalExpression = { TokenCode.SEMICOLON};
	public TokenCode[] statementBlock = {TokenCode.ELSE, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] incrDecrVar = {TokenCode.RPAREN};
	public TokenCode[] optionalElse = {TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] expressionList = {TokenCode.RPAREN};
	public TokenCode[] moreExpressions = {TokenCode.RPAREN};
	public TokenCode[] expression = {TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] _expression = {TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE };
	public TokenCode[] simpleExpression = {TokenCode.RELOP, TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] _simpleExpression = {TokenCode.RELOP, TokenCode.RBRACKET, TokenCode.RPAREN, TokenCode.COMMA, TokenCode.SEMICOLON, TokenCode.IF, TokenCode.FOR, TokenCode.RETURN, TokenCode.BREAK, TokenCode.CONTINUE, TokenCode.IDENTIFIER, TokenCode.LBRACE};
	public TokenCode[] term = {TokenCode.ADDOP};
	public TokenCode[] _term = {TokenCode.ADDOP};
	public TokenCode[] factor = {TokenCode.MULOP};
	public TokenCode[] idStartingFactor = {TokenCode.MULOP};
	public TokenCode[] restOfIdStartingFactor = {TokenCode.MULOP};
	public TokenCode[] variableLoc = {TokenCode.INCDECOP, TokenCode.ASSIGNOP};
	public TokenCode[] sign = {TokenCode.NUMBER, TokenCode.LPAREN, TokenCode.NOT, TokenCode.IDENTIFIER};
}

