package Assignment2;
import java.io.*;
import java.util.Arrays;
import java.util.Vector;


public class Parser {

	static Token lookahead;
	static Token nextLookahead;
	public static TokenDumper td;
	static int lineNumber = 0;
	static Vector<String> lineOfTokens;
	static boolean containsError = false;
	static TokenCode expected = null;
	static int columnNumber = 0;
	private TokenCode[] typeF = { TokenCode.IDENTIFIER};
	
	
	public static void main(String[] args) throws IOException{
		System.out.println("Let the parsing begin!");
		td = new TokenDumper(args[0]);
		Parser parser = new Parser();
		parser.program();
		System.out.write('\n');
	}
	
	public Parser() throws IOException{
		lookahead = td.getNextToken();

	}
	
	private void match(TokenCode t){
		/*if(lookahead.getLine() != lineNumber){
			if(containsError){
				System.out.print(lineNumber + " : ");
				for(int i = 0; i < lineOfTokens.size(); i++){
					System.out.print(lineOfTokens.get(i));
					
				}
			}
			lineNumber = lookahead.getLine();*/
			//lineOfTokens.removeAllElements();
		//}
		if(lookahead.getTokenCode() == t){
			try {
				//lineOfTokens.add(lookahead.getSymTabEntry().getLexeme());
				System.out.println(lookahead.getTokenCode());
				lookahead = td.getNextToken();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//containsError = true;
			//expected = t;
			//lineOfTokens.add(lookahead.getSymTabEntry().getLexeme());
			System.out.println(lookahead.getTokenCode());
			//System.out.println(lookahead.getSymTabEntry().getLexeme());
			System.out.println(t);
			System.out.println("Villa");
			System.out.println("Expected: " + t);
			System.out.println(td.getLine(lookahead.getLine()+1));
		}
	}
	
	private void program(){
		System.out.println("program");
		match(TokenCode.CLASS);
		match(TokenCode.IDENTIFIER);
		match(TokenCode.LBRACE);
		variableDeclarations();
		methodDeclarations();
		match(TokenCode.RBRACE);
		match(TokenCode.EOF);
	}
	private void variableDeclarations() {
		System.out.println("variableDeclarations");
		_variableDeclarations();
	}
	private void _variableDeclarations() {
		System.out.println("_variableDeclarations");
		if(type()){
			variableList();
			match(TokenCode.SEMICOLON);
			_variableDeclarations();
		}
		else{
			try {
				nextLookahead = td.getNextToken();
				System.out.println("hello" + nextLookahead.getTokenCode());
				if(Arrays.asList(typeF).contains(nextLookahead.getTokenCode())){
					System.out.println("okei");
					lookahead = nextLookahead;
					variableList();
					match(TokenCode.SEMICOLON);
					_variableDeclarations();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private boolean type(){
		System.out.println("type");
		if(lookahead.getTokenCode() == TokenCode.INT){
			match(TokenCode.INT);
			return true;
		}
		else if(lookahead.getTokenCode() == TokenCode.REAL){
			match(TokenCode.REAL);
			return true;
		}
		return false;
			
	}
    private void variableList() {
    	System.out.println("variableList");
    	variable();
    	_variableList();
    }
    
    private void _variableList(){
    	System.out.println("_variableList");
    	if(lookahead.getTokenCode() == TokenCode.COMMA){
    		match(TokenCode.COMMA);
        	variable();
        	_variableList();
    	}

    }
    
    private void variable () {
    	System.out.println("variable");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		_variable();
    	}
    }
    private void _variable(){
    	System.out.println("_variable");
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET);
    		match(TokenCode.NUMBER);
    		match(TokenCode.RBRACKET);
    	}
    }
    private void methodDeclarations () {
    	System.out.println("methodDeclarations");
    	methodDeclaration();
    	moreMethodDeclarations();
    }
    private void moreMethodDeclarations() {
    	System.out.println("moreMethodDeclarations");

    	_moreMethodDeclarations();
    }
    
    private void _moreMethodDeclarations(){
    	System.out.println("_moreMethodDeclarations");
    	if(methodDeclaration()){
    		_moreMethodDeclarations();
    	}

    }
    private boolean methodDeclaration(){
    	System.out.println("methodDeclaration");
    	if(lookahead.getTokenCode() == TokenCode.STATIC){
    		match(TokenCode.STATIC);
    		methodReturnType();
    		match(TokenCode.IDENTIFIER);
    		match(TokenCode.LPAREN);
    		parameters();
    		match(TokenCode.RPAREN);
    		match(TokenCode.LBRACE);
    		variableDeclarations();
    		statementList();
    		match(TokenCode.RBRACE);
    		return true;
    	}
    	return false;
    }
    private void methodReturnType() {
    	System.out.println("methodReturnType");
    	if(type())
    		
    	if(lookahead.getTokenCode() == TokenCode.VOID){
    		match(TokenCode.VOID);
    	}
    }
    private void parameters () {
    	System.out.println("parameters");

    	parameterList();
    }
    private void parameterList() {
    	System.out.println("parameterList");
    	type();
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		_parameterList();
    	}  	
    }
    private void _parameterList() {
    	System.out.println("_parameterList");
    	if(lookahead.getTokenCode() == TokenCode.COMMA){
    		match(TokenCode.COMMA);
    		type();
    		match(TokenCode.IDENTIFIER);
    		parameterList();
    	}
    }
    private void statementList() {
    	System.out.println("statementList");
    	_statementList();
    }
    private void _statementList() {
    	System.out.println("_statementList");
    	if(statement()){
    		_statementList();
    	}
    }
    private boolean statement(){
    	System.out.println("statement");
    	if(idStartingStatement()){
    		return true;
    	}
    	else if(lookahead.getTokenCode() == TokenCode.IF){
    		match(TokenCode.IF);
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
    		expression();
    		match(TokenCode.SEMICOLON);
    		incrDecVar();
    		match(TokenCode.RPAREN);
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
    	System.out.println("idStartingStatement");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		restOfStartingStatement();
    		return true;
    	}
    	return false;
    }
    private void restOfStartingStatement(){
    	System.out.println("restOfStartingStatement");
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
    		match(TokenCode.SEMICOLON);
    	}
    	else if(lookahead.getTokenCode() == TokenCode.INCDECOP){
    		match(TokenCode.INCDECOP);
    		match(TokenCode.SEMICOLON);
    	}
    	
    }
    private void _restOfStartingStatement(){
    	System.out.println("_restOfStartingStatement");
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
    	System.out.println("optionalExpression");
		expression();
	}

	private boolean statementBlock(){
    	System.out.println("statementBlock");
		if(lookahead.getTokenCode() == TokenCode.LBRACE) {
			match(TokenCode.LBRACE);
			statementList();
			match(TokenCode.RBRACE);
			return true;
		}
		return false;
	}

	private void incrDecVar(){
    	System.out.println("incrDecVar");
		variableLoc();
		if(lookahead.getTokenCode() == TokenCode.INCDECOP) {
			match(TokenCode.INCDECOP);
		}
	}

	private void optionalElse(){
    	System.out.println("optionalElse");
		if(lookahead.getTokenCode() == TokenCode.ELSE) {
			match(TokenCode.ELSE);
			statementBlock();
		}
	}

	private void expressionList(){
    	System.out.println("expressionList");
		expression();
		moreExpressions();
	}

	private void moreExpressions(){
    	System.out.println("moreExpressions");
		if(lookahead.getTokenCode() == TokenCode.COMMA) {
			match(TokenCode.COMMA);
			expression();
			moreExpressions();
		}
	}
	private void expression(){
    	System.out.println("expression");
		simpleExpression();
		_expression();
	}

	private void _expression() {
    	System.out.println("_expression");
		if(lookahead.getTokenCode() == TokenCode.RELOP) {
			match(TokenCode.RELOP);
			simpleExpression();
		}
	}

    
    private void simpleExpression(){
    	System.out.println("simpleExpression");
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
    	System.out.println("_simpleExpression");
    	if(lookahead.getTokenCode() == TokenCode.ADDOP){
    		match(TokenCode.ADDOP);
    		term();
    		_simpleExpression();
    	}
    }
    private void term(){
    	System.out.println("term");
    	factor();
    	_term();
    }
    private void _term(){
    	System.out.println("_term");
    	if(lookahead.getTokenCode() == TokenCode.MULOP){
    		match(TokenCode.MULOP);
    		factor();
    		_term();
    	}
    }
    private void factor(){
    	System.out.println("factor");
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
    	System.out.println("idStartingFactor");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		restOfIdStartingFactor();
    	}
    }
    private void restOfIdStartingFactor(){
    	System.out.println("restOfIdStartingFactor");
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
    	System.out.println("variableLoc");
    	if(lookahead.getTokenCode() == TokenCode.IDENTIFIER){
    		match(TokenCode.IDENTIFIER);
    		_variableLoc();
    	}
    }
    private void _variableLoc(){
    	System.out.println("_variableLoc");
    	if(lookahead.getTokenCode() == TokenCode.LBRACKET){
    		match(TokenCode.LBRACKET);
    		expression();
    		match(TokenCode.RBRACKET);
    	}
    }
   
    private boolean sign(){
    	System.out.println("sign");
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
