package Assignment2;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class TokenDumper {
	private Lexer lexer;
	private List<String> lines;

	public TokenDumper(String fileReader) throws FileNotFoundException, IOException{
		this.lexer = new Lexer(new FileReader(fileReader));
		this.lines = Files.readAllLines(Paths.get(fileReader), Charset.defaultCharset());
		System.out.println("gera lexer");
	}
	
	public Token nextToken() throws IOException{
		Token t = lexer.yylex();
		if(t.getTokenCode() == TokenCode.IDENTIFIER){
			System.out.println(t.getSymTabEntry().getLexeme());
		}
	    return t;
	}
	public String getLine(int lineNumber) {
		String res = lines.get(lineNumber - 1);
		return res.replaceAll("^\\s+","");
	}
}
