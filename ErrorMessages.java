package Assignment2;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages {
    public Map<TokenCode, String> errors;

    public ErrorMessages(){
        errors = new HashMap<>();
        errors.put(TokenCode.IDENTIFIER, "Expected an identifier");
        errors.put(TokenCode.NUMBER, "Expected a number");
        errors.put(TokenCode.INCDECOP, "Expected '++' or '--'");
        errors.put(TokenCode.RELOP, "Expected '==' or '!=' or '<=' or '>=' or '<' or '>'");
        errors.put(TokenCode.MULOP, "Expected '*' or '%' or '/' or '&'");
        errors.put(TokenCode.ADDOP, "Expected '+'");
        errors.put(TokenCode.ASSIGNOP, "Expected '='");
        errors.put(TokenCode.CLASS, "Expected 'class'");
        errors.put(TokenCode.STATIC, "Expected 'static'");
        errors.put(TokenCode.VOID, "Expected 'void'");
        errors.put(TokenCode.IF, "Expected 'if'");
        errors.put(TokenCode.ELSE, "Expected 'else'");
        errors.put(TokenCode.FOR, "Expected 'for'");
        errors.put(TokenCode.RETURN, "Expected 'return'");
        errors.put(TokenCode.BREAK, "Expected 'break'");
        errors.put(TokenCode.CONTINUE, "Expected 'continue'");
        errors.put(TokenCode.LBRACE, "Expected '{'");
        errors.put(TokenCode.RBRACE, "Expected '}'");
        errors.put(TokenCode.LBRACKET, "Expected '['");
        errors.put(TokenCode.RBRACKET, "Expected ']'");
        errors.put(TokenCode.LPAREN, "Expected '('");
        errors.put(TokenCode.SEMICOLON, "Expected ';'");
        errors.put(TokenCode.COMMA, "Expected ','");
        errors.put(TokenCode.NOT, "Expected '!'");
        errors.put(TokenCode.INT, "Expected 'int'");
        errors.put(TokenCode.REAL, "Expected 'real'");
        errors.put(TokenCode.EOF, "Expected 'end of file'");
    }
}
