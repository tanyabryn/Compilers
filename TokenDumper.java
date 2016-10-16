package Assignment2;
import java.io.*;


public class TokenDumper {
	private Lexer lexer;

	public TokenDumper(String fileReader) throws FileNotFoundException{
		this.lexer = new Lexer(new FileReader(fileReader));
		System.out.println("gera lexer");
	}
	
	public Token getNextToken() throws IOException{
		Token t = lexer.yylex();
	    return t;
	}
}
