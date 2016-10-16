package Assignment2;
import java.io.*;


public class Parser {

	static Token lookahead;
	public static TokenDumper td;
	
	public static void main(String[] args) throws IOException{
		System.out.println("Let the parsing begin!");
		td = new TokenDumper(args[0]);
		System.out.println("tokendumper buinn");
		Parser parser = new Parser();

		System.out.println("hello");
		parser.program();
		System.out.write('\n');
	}
	
	public Parser() throws IOException{
		System.out.println("smida parser");
		lookahead = td.getNextToken();

	}
	
	private void match(TokenCode t){
		if(lookahead.getTokenCode() == t){
			try {
				System.out.print(t.toString());
				lookahead = td.getNextToken();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			System.out.println(t.toString());
			System.out.println("Villa");
		}
	}
	
	private void program(){
		match(TokenCode.CLASS);
		match(TokenCode.IDENTIFIER);
		match(TokenCode.LBRACE);
		variableDeclarations();
		methodDeclarations();
		match(TokenCode.RBRACE);
		match(TokenCode.EOF);
	}
	private void variableDeclarations() {
		_variableDeclarations();
	}
	private void _variableDeclarations() {
		type();
		variableList();
		if(lookahead.getTokenCode() == TokenCode.SEMICOLON){
			match(TokenCode.SEMICOLON);
			_variableDeclarations();
		}
	}
	private void type(){
		if(lookahead.getTokenCode() == TokenCode.INT){
			match(TokenCode.INT);
		}
		else if(lookahead.getTokenCode() == TokenCode.REAL){
			match(TokenCode.REAL);
		}
			
	}
    private void variableList() {
    	variable();
    	_variableList();
    }
    
    private void _variableList(){
    	if(lookahead.getTokenCode() == TokenCode.COMMA){
    		match(TokenCode.COMMA);
        	variable();
        	_variableList();
    	}

    }
    
    private void variable () {
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		_variable();
    	}
    }
    private void _variable(){
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET);
    		match(TokenCode.NUMBER);
    		match(TokenCode.RBRACKET);
    	}
    }
    private void methodDeclarations () {
    	methodDeclaration();
    	moreMethodDeclarations();
    }
    private void moreMethodDeclarations() {
    	_moreMethodDeclarations();
    }
    
    private void _moreMethodDeclarations(){
    	if(methodDeclaration()){
    		_moreMethodDeclarations();
    	}

    }
    private boolean methodDeclaration(){
    	if(lookahead.getTokenCode() == TokenCode.STATIC){
    		match(TokenCode.STATIC);
    		methodReturnType();
    		match(TokenCode.IDENTIFIER);
    		match(TokenCode.LPAREN);
    		parameters();
    		match(TokenCode.RPAREN);
    		match(TokenCode.LBRACKET);
    		variableDeclarations();
    		statementList();
    		match(TokenCode.RBRACKET);
    		return true;
    	}
    	return false;
    }
    private void methodReturnType() {
    	type();
    	if(lookahead.getTokenCode() == TokenCode.VOID){
    		match(TokenCode.VOID);
    	}
    }
    private void parameters () {
    	parameterList();
    }
    private void parameterList() {
    	type();
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		_parameterList();
    	}  	
    }
    private void _parameterList() {
    	if(lookahead.getTokenCode() == TokenCode.COMMA){
    		match(TokenCode.COMMA);
    		type();
    		match(TokenCode.IDENTIFIER);
    		parameterList();
    	}
    }
    private void statementList() {
    	_statementList();
    }
    private void _statementList() {
    	if(statement()){
    		_statementList();
    	}
    }
    private boolean statement(){
    	if(idStartingStatement()){
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.IF){
    		match(TokenCode.IF);
    		match(TokenCode.IDENTIFIER);
    		match(TokenCode.LPAREN);
    		expression();
    		match(TokenCode.RPAREN);
    		statementBlock();
    		optionalElse();
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.FOR){
    		match(TokenCode.FOR);
    		match(TokenCode.LPAREN);
    		variableLoc();
    		match(TokenCode.ASSIGNOP);
    		expression();
    		match(TokenCode.SEMICOLON);
    		statementBlock();
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.RETURN){
    		match(TokenCode.RETURN);
    		optionalExpression();
    		match(TokenCode.SEMICOLON);
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.BREAK){
    		match(TokenCode.BREAK);
    		match(TokenCode.SEMICOLON);
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.CONTINUE){
    		match(TokenCode.CONTINUE);
    		match(TokenCode.SEMICOLON);
    		return true;
    	}
    	else if(statementBlock()){
        	return true;
    	}
    	else{
    		return false;
    	}

    }
    private boolean idStartingStatement(){
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		restOfStartingStatement();
    		return true;
    	}
    	return false;
    }
    private void restOfStartingStatement(){
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET);
    		expression();
    		match(TokenCode.RBRACKET);
    		_restOfStartingStatement();
    	}
    	else if(lookahead.getTokenCode() == TokenCode.ASSIGNOP){
    		match(TokenCode.ASSIGNOP);
    		expression();
    		match(TokenCode.SEMICOLON);
    	}
    	else if(lookahead.getTokenCode() == TokenCode.LPAREN){
    		match(TokenCode.LPAREN);
    		expressionList();
    		match(TokenCode.RPAREN);
    	}
    	else if(lookahead.getTokenCode() == TokenCode.INCDECOP){
    		match(TokenCode.INCDECOP);
    	}
    	
    }
    private void _restOfStartingStatement(){
    	if(lookahead.getTokenCode() == TokenCode.ASSIGNOP){
    		match(TokenCode.ASSIGNOP);
    		expression();
    		match(TokenCode.SEMICOLON);
    	}
    	else if(lookahead.getTokenCode() == TokenCode.INCDECOP){
    		match(TokenCode.INCDECOP);
    	}
    }


	private void optionalExpression(){
		expression();
	}

	private boolean statementBlock(){
		if(lookahead.getTokenCode() == TokenCode.LBRACE) {
			match(TokenCode.LBRACE);
			statementList();
			match(TokenCode.RBRACE);
			return true;
		}
		return false;
	}

	private void incrDecVar(){
		variableLoc();
		if(lookahead.getTokenCode() == TokenCode.INCDECOP) {
			match(TokenCode.INCDECOP);
		}
	}

	private void optionalElse(){
		if(lookahead.getTokenCode() == TokenCode.ELSE) {
			match(TokenCode.ELSE);
			statementBlock();
		}
	}

	private void expressionList(){
		expression();
		moreExpressions();
	}

	private void moreExpressions(){
		if(lookahead.getTokenCode() == TokenCode.COMMA) {
			match(TokenCode.COMMA);
			expression();
			moreExpressions();
		}
	}
	private void expression(){
		simpleExpression();
		_expression();
	}

	private void _expression() {
		if(lookahead.getTokenCode() == TokenCode.RELOP) {
			match(TokenCode.RELOP);
			simpleExpression();
		}
	}

    
    private void simpleExpression(){
    	if(sign()){
    		term();
    		_simpleExpression();
    	}
    	else{
    		term();
    		_simpleExpression();
    	}
    }
    
    private void _simpleExpression(){
    	if(lookahead.getTokenCode() == TokenCode.ADDOP){
    		match(TokenCode.ADDOP);
    		term();
    		_simpleExpression();
    	}
    }
    private void term(){
    	factor();
    	_term();
    }
    private void _term(){
    	if(lookahead.getTokenCode() == TokenCode.MULOP){
    		match(TokenCode.MULOP);
    		factor();
    		_term();
    	}
    }
    private void factor(){
    	if(lookahead.getTokenCode() == TokenCode.NUMBER){
    		match(TokenCode.NUMBER);
    	}
    	else if (lookahead.getTokenCode() == TokenCode.LPAREN){
    		match(TokenCode.LPAREN);
    		expression();
    		match(TokenCode.RPAREN);
    	}
    	else if (lookahead.getTokenCode() == TokenCode.NOT){
    		match(TokenCode.NOT);
    		factor();
    	}
    	else{
    		idStartingFactor();
    	}    	
    }
    private void idStartingFactor(){
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		restOfIdStartingFactor();
    	}
    }
    private void restOfIdStartingFactor(){
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET);
    		expression();
    		match(TokenCode.RBRACKET);
    	}
    	else if(lookahead.getTokenCode() == TokenCode.LPAREN){
    		match(TokenCode.LPAREN);
    		expressionList();
    		match(TokenCode.RPAREN);
    	}
    }
    private void variableLoc(){
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		_variableLoc();
    	}
    }
    private void _variableLoc(){
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET);
    		expression();
    		match(TokenCode.RBRACKET);
    	}
    }
   
    private boolean sign(){
    	if(lookahead.getOpType() == OpType.PLUS){
    		match(TokenCode.ADDOP);
    		return true;
    	}
    	else if(lookahead.getOpType() == OpType.MINUS){
    		match(TokenCode.ADDOP);
    		return true;
    	}
    	return false;
    }
}
