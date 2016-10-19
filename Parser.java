package Assignment2;
import java.io.*;
import java.util.Arrays;
import java.util.Vector;


public class Parser{

	static Token lookahead;
	static Token nextLookahead;
	public static TokenDumper td;
	private int errorCount = 0;
	public static ErrorMessages msg;

	
	
	
	
	public static void main(String[] args) throws IOException{
		System.out.println("Let the parsing begin!");
		td = new TokenDumper(args[0]);
		Parser parser = new Parser();
		msg = new ErrorMessages();
		parser.program();
		System.out.write('\n');
	}
	
	public Parser() throws IOException{
		lookahead = getNextToken();

	}
	
	public void isInFollow(TokenCode[] t) throws IOException {
        while(! Arrays.asList(t).contains(lookahead.getTokenCode())){
        	lookahead = getNextToken();
        }
    }
	
	public void handleError(TokenCode t, TokenCode[] set, int lineNumber, int column, String msg) throws IOException {
        errorCount++;
        int q = Integer.toString(lineNumber).length();
        column = column + 3 + q;
        String error = "^ " + msg;
        String line = td.getLine(lineNumber+1);
        String spaces = "";

        if(column > 0) {
            spaces = String.format("%" + column + "s", " ");
        }

        System.out.println("--------------------------------");
        System.out.println(lineNumber + " : " + line );
        System.out.println(spaces + error);
        System.out.println("--------------------------------");
        if(t != TokenCode.ERR_ILL_CHAR){
        	isInFollow(set);
        }
    }
	
	public Token getNextToken() throws IOException{
		Token result = td.nextToken();
		if(result.getTokenCode() == TokenCode.ERR_ILL_CHAR){
			System.out.println(" Villa Illegal Char!");
			handleError(TokenCode.ERR_ILL_CHAR, null, result.getLine(), result.getColumn(), "Illegal Character");
			result = td.nextToken();
		}
		return result;
	}

	private void match(TokenCode t, TokenCode[] set, int line, int column, String message) throws IOException{
		if(lookahead.getTokenCode() == t){
			try {
				//lineOfTokens.add(lookahead.getSymTabEntry().getLexeme());
				System.out.println(lookahead.getTokenCode());
				if(nextLookahead == null){
					lookahead = getNextToken();
				}
				else{
					lookahead = nextLookahead;
					nextLookahead = null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
			handleError(t, set, line, column, message);			
		}
	}
	
	private void program() throws IOException{
		//System.out.println("program");
		match(TokenCode.CLASS, SyncronizingSets.program, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.CLASS));
		match(TokenCode.IDENTIFIER, SyncronizingSets.program, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
		match(TokenCode.LBRACE, SyncronizingSets.program, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACE));
		variableDeclarations();
		methodDeclarations();
		match(TokenCode.RBRACE, SyncronizingSets.program, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACE));
		match(TokenCode.EOF, SyncronizingSets.program, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.EOF));
	}
	private void variableDeclarations() throws IOException {
		//System.out.println("variableDeclarations");
		_variableDeclarations();
	}
	private void _variableDeclarations() throws IOException {
		//System.out.println("_variableDeclarations");
		if(type()){
			variableList();
			match(TokenCode.SEMICOLON, SyncronizingSets._variableDeclarations, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));

			_variableDeclarations();
		}
		else if(typeError()){
			variableList();
			match(TokenCode.SEMICOLON, SyncronizingSets._variableDeclarations, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));

			_variableDeclarations();
			/*nextLookahead = td.getNextToken();
				System.out.println("hello1" + nextLookahead.getTokenCode());
				if(Arrays.asList(SyncronizingSets.type).contains(nextLookahead.getTokenCode())){
					System.out.println("okei");
					match(null);
					lookahead = nextLookahead;
					nextLookahead = null;
					variableList();
					match(TokenCode.SEMICOLON);
					_variableDeclarations();
					
				}
			*/
		}
	}

	private boolean typeError() throws IOException{
		nextLookahead = getNextToken();
		System.out.println("toki a undan: " + nextLookahead.getTokenCode());
		System.out.println("toki a eftir: " + lookahead.getTokenCode());

		if(Arrays.asList(SyncronizingSets.type).contains(nextLookahead.getTokenCode())){
			System.out.println("Vantar typu");
			lookahead = nextLookahead;
			nextLookahead = null;
			return true;
		}
		return false;
	}
	private boolean type() throws IOException{
		//System.out.println("type");
		if(lookahead.getTokenCode() == TokenCode.INT){
			match(TokenCode.INT, SyncronizingSets.type, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.INT));
			return true;
		}
		else if(lookahead.getTokenCode() == TokenCode.REAL){
			match(TokenCode.REAL, SyncronizingSets.type, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.REAL));

			return true;
		}
	/*	else if(Arrays.asList(SyncronizingSets.type).contains(lookahead.getTokenCode())){
			System.out.println("Vantar typu");
			return true;
		}*/
		return false;
			
	}
    private void variableList() throws IOException {
    	//System.out.println("variableList");
    	variable();
    	_variableList();
    }
    
    private void _variableList() throws IOException{
    	//System.out.println("_variableList");
    	if(lookahead.getTokenCode() == TokenCode.COMMA){
			match(TokenCode.COMMA, SyncronizingSets._variableList, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.COMMA));
        	variable();
        	_variableList();
    	}

    }
    
    private void variable () throws IOException {
    	//System.out.println("variable");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
			match(TokenCode.IDENTIFIER, SyncronizingSets.variable, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
    		_variable();
    	}
    }
    private void _variable() throws IOException{
    	//System.out.println("_variable");
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
			match(TokenCode.LBRACKET, SyncronizingSets._variable, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACKET));

			match(TokenCode.NUMBER, SyncronizingSets._variable, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.NUMBER));
			match(TokenCode.RBRACKET, SyncronizingSets._variable, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACKET));
    	}
    }
    private void methodDeclarations () throws IOException {
    	//System.out.println("methodDeclarations");
    	methodDeclaration();
    	moreMethodDeclarations();
    }
    private void moreMethodDeclarations() throws IOException {
    	//System.out.println("moreMethodDeclarations");

    	_moreMethodDeclarations();
    }
    
    private void _moreMethodDeclarations() throws IOException{
    	//System.out.println("_moreMethodDeclarations");
    	if(methodDeclaration()){
    		_moreMethodDeclarations();
    	}

    }
    private boolean methodDeclaration() throws IOException{
    	//System.out.println("methodDeclaration");
    	if(lookahead.getTokenCode() == TokenCode.STATIC){
			match(TokenCode.STATIC, SyncronizingSets.methodDeclaration, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACKET));
    		methodReturnType();
			match(TokenCode.IDENTIFIER, SyncronizingSets.methodDeclaration, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
			match(TokenCode.LPAREN, SyncronizingSets.methodDeclaration, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LPAREN));
    		parameters();
			match(TokenCode.RPAREN, SyncronizingSets.methodDeclaration, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RPAREN));
			match(TokenCode.LBRACE, SyncronizingSets.methodDeclaration, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACE));
    		variableDeclarations();
    		statementList();
			match(TokenCode.RBRACE, SyncronizingSets.methodDeclaration, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACE));
    		return true;
    	}
    	return false;
    }
    
    // LAGA UPP @A ERROR!!!
    private void methodReturnType() throws IOException {
    	//System.out.println("methodReturnType");
    	if(type()){}
    		
    	else if(lookahead.getTokenCode() == TokenCode.VOID){
			match(TokenCode.VOID, SyncronizingSets.methodReturnType, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.VOID));
    	}
    }
    private void parameters () throws IOException {
    	parameterList();
    }
    private void parameterList() throws IOException {
    	type();
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
			match(TokenCode.IDENTIFIER, SyncronizingSets.parameterList, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
    		_parameterList();
    	}  	
    }
    private void _parameterList() throws IOException {
    	//System.out.println("_parameterList");
    	if(lookahead.getTokenCode() == TokenCode.COMMA){
			match(TokenCode.COMMA, SyncronizingSets._parameterList, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.COMMA));
    		type();
			match(TokenCode.IDENTIFIER, SyncronizingSets._parameterList, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
    		parameterList();
    	}
    }
    private void statementList() throws IOException {
    	//System.out.println("statementList");
    	_statementList();
    }
    private void _statementList() throws IOException {
    	//System.out.println("_statementList");
    	if(statement()){
    		_statementList();
    	}
    }
    private boolean statement() throws IOException{
    	//System.out.println("statement");
    	if(idStartingStatement()){
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.IF){
			match(TokenCode.IF, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IF));
			match(TokenCode.LPAREN, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LPAREN));
    		expression();
			match(TokenCode.RPAREN, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RPAREN));
    		statementBlock();
    		optionalElse();
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.FOR){
			match(TokenCode.FOR, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.FOR));
			match(TokenCode.LPAREN, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LPAREN));
    		variableLoc();
			match(TokenCode.ASSIGNOP, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ASSIGNOP));
    		expression();
			match(TokenCode.SEMICOLON, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    		expression();
			match(TokenCode.SEMICOLON, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    		incrDecVar();
			match(TokenCode.RPAREN, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RPAREN));
    		statementBlock();
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.RETURN){
			match(TokenCode.RETURN, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RETURN));
    		optionalExpression();
			match(TokenCode.SEMICOLON, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.BREAK){
			match(TokenCode.BREAK, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.BREAK));
			match(TokenCode.SEMICOLON, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.CONTINUE){
			match(TokenCode.CONTINUE, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.CONTINUE));
			match(TokenCode.SEMICOLON, SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    		return true;
    	}
    	else if(statementBlock()){
        	return true;
    	}
		return false;

    }
    private boolean idStartingStatement() throws IOException{
    	//System.out.println("idStartingStatement");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
			match(TokenCode.IDENTIFIER, SyncronizingSets.idStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
    		restOfStartingStatement();
    		return true;
    	}
    	return false;
    }
    private void restOfStartingStatement() throws IOException{
    	//System.out.println("restOfStartingStatement");
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
			match(TokenCode.LBRACKET, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACKET));
    		expression();
			match(TokenCode.RBRACKET, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACKET));
    		_restOfStartingStatement();
    	}
    	else if(lookahead.getTokenCode() == TokenCode.ASSIGNOP){
			match(TokenCode.ASSIGNOP, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ASSIGNOP));
    		expression();	
			match(TokenCode.SEMICOLON, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));

    	}

    	else if(lookahead.getTokenCode() == TokenCode.LPAREN){
			match(TokenCode.LPAREN, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LPAREN));
    		expressionList();
			match(TokenCode.RPAREN, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RPAREN));
			match(TokenCode.SEMICOLON, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    	}
    	else if(lookahead.getTokenCode() == TokenCode.INCDECOP){
			match(TokenCode.INCDECOP, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.INCDECOP));
			match(TokenCode.SEMICOLON, SyncronizingSets.restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    	}
    	else{
    		handleError(lookahead.getTokenCode(), SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), "Invalid statement");	
    	}
    }
    private void _restOfStartingStatement() throws IOException{
    	//System.out.println("_restOfStartingStatement");
    	if(lookahead.getTokenCode() == TokenCode.ASSIGNOP){
    		match(TokenCode.ASSIGNOP, SyncronizingSets._restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ASSIGNOP));
    		expression();
    		match(TokenCode.SEMICOLON, SyncronizingSets._restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.SEMICOLON));
    	}
    	else if(lookahead.getTokenCode() == TokenCode.INCDECOP){
    		match(TokenCode.INCDECOP, SyncronizingSets._restOfIdStartingStatement, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.INCDECOP));
    	}
    	else{
    		handleError(lookahead.getTokenCode(), SyncronizingSets.statement, lookahead.getLine(), lookahead.getColumn(), "Invalid statement");	
    	}
    }


	private void optionalExpression() throws IOException{
    	System.out.println("optionalExpression");
		expression();
	}

	private boolean statementBlock() throws IOException{
    	System.out.println("statementBlock");
		if(lookahead.getTokenCode() == TokenCode.LBRACE) {
			match(TokenCode.LBRACE, SyncronizingSets.statementBlock, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACE));
			statementList();
			match(TokenCode.RBRACE, SyncronizingSets.statementBlock, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACE));
			return true;
		}
		return false;
	}

	private void incrDecVar() throws IOException{
    	System.out.println("incrDecVar");
		variableLoc();
		if(lookahead.getTokenCode() == TokenCode.INCDECOP) {
			match(TokenCode.INCDECOP, SyncronizingSets.incrDecrVar, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.INCDECOP));

		}
	}

	private void optionalElse() throws IOException{
    	System.out.println("optionalElse");
		if(lookahead.getTokenCode() == TokenCode.ELSE) {
			match(TokenCode.ELSE, SyncronizingSets.optionalElse, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ELSE));
			if(!statementBlock()){
	    		handleError(TokenCode.LBRACE, SyncronizingSets.optionalElse, lookahead.getLine(), lookahead.getColumn(), "hallo");	
				
			}
		}
	}

	private void expressionList() throws IOException{
    	System.out.println("expressionList");
		expression();
		moreExpressions();
	}

	private void moreExpressions() throws IOException{
    	System.out.println("moreExpressions");
		if(lookahead.getTokenCode() == TokenCode.COMMA) {
			match(TokenCode.COMMA, SyncronizingSets.moreExpressions, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.COMMA));
			expression();
			moreExpressions();
		}
	}
	private void expression() throws IOException{
    	System.out.println("expression");
		simpleExpression();
		_expression();
	}

	private void _expression() throws IOException {
    	System.out.println("_expression");
		if(lookahead.getTokenCode() == TokenCode.RELOP) {
			match(TokenCode.RELOP, SyncronizingSets._expression, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RELOP));
			if(!simpleExpression()){
	    		handleError(TokenCode.LBRACE, SyncronizingSets._expression, lookahead.getLine(), lookahead.getColumn(), "Error in expression");	
			}
		}
	}

    
    private boolean simpleExpression() throws IOException{
    	System.out.println("simpleExpression");
    	if(sign()){
    		term();
    		_simpleExpression();
    		return true;
    	}
    	else if(term()){
    		_simpleExpression();
    		return true;
    	}
		return false;
    }
    
    private void _simpleExpression() throws IOException{
    	System.out.println("_simpleExpression");
    	if(lookahead.getTokenCode() == TokenCode.ADDOP){
    		match(TokenCode.ADDOP, SyncronizingSets._simpleExpression, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ADDOP));
    		term();
    		_simpleExpression();
    	}
    }
    private boolean term() throws IOException{
    	System.out.println("term");
    	if(factor()){
    		_term();
    		return true;
    	}
    	else{
    		_term();
    		return false;
    	}
    }
    private void _term() throws IOException{
    	System.out.println("_term");
    	if(lookahead.getTokenCode() == TokenCode.MULOP){
    		match(TokenCode.MULOP, SyncronizingSets._term, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.MULOP));
    		factor();
    		_term();
    	}
    }
    private boolean factor() throws IOException{
    	System.out.println("factor");
    	if(lookahead.getTokenCode() == TokenCode.NUMBER){
    		match(TokenCode.NUMBER, SyncronizingSets.factor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.NUMBER));
    		return true;
    	}
    	else if (lookahead.getTokenCode() == TokenCode.LPAREN){
    		match(TokenCode.LPAREN, SyncronizingSets.factor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LPAREN));
    		expression();
    		match(TokenCode.RPAREN, SyncronizingSets.factor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RPAREN));
    		return true;
    	}
    	else if (lookahead.getTokenCode() == TokenCode.NOT){
    		match(TokenCode.NOT, SyncronizingSets.factor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.NOT));
    		factor();
    		return true;
    	}
    	else if (idStartingFactor()){
    		return true;
    	}
    	return false;
    }
    private boolean idStartingFactor() throws IOException{
    	//System.out.println("idStartingFactor");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER, SyncronizingSets.idStartingFactor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
    		restOfIdStartingFactor();
    		return true;
    	}
		return false;
    }
    private void restOfIdStartingFactor() throws IOException{
    	//System.out.println("restOfIdStartingFactor");
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET, SyncronizingSets.restOfIdStartingFactor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACKET));
    		expression();
    		match(TokenCode.RBRACKET, SyncronizingSets.restOfIdStartingFactor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACKET));
    	}
    	else if(lookahead.getTokenCode() == TokenCode.LPAREN){
    		match(TokenCode.LPAREN, SyncronizingSets.restOfIdStartingFactor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LPAREN));
    		expressionList();
    		match(TokenCode.RPAREN, SyncronizingSets.restOfIdStartingFactor, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RPAREN));
    	}
    }
    private void variableLoc() throws IOException{
    	//System.out.println("variableLoc");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER, SyncronizingSets.variableLoc, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.IDENTIFIER));
    		_variableLoc();
    	}
    }
    private void _variableLoc() throws IOException{
    	//System.out.println("_variableLoc");
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET, SyncronizingSets._variableLoc, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.LBRACKET));
    		expression();
    		match(TokenCode.RBRACKET, SyncronizingSets._variableLoc, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.RBRACKET));
    	}
    }
   
    // Ekkert! 
    private boolean sign() throws IOException{
    	//System.out.println("sign");
    	if(lookahead.getOpType() == OpType.PLUS){
    		match(TokenCode.ADDOP, SyncronizingSets.sign, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ADDOP));
    		return true;
    	}
    	else if(lookahead.getOpType() == OpType.MINUS){
    		match(TokenCode.ADDOP, SyncronizingSets.sign, lookahead.getLine(), lookahead.getColumn(), msg.errors.get(TokenCode.ADDOP));    		return true;
    	}
    	return false;
    }
    
}