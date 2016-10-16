package Assignment2;

public class Token {
	  private TokenCode m_tc;
	  private DataType m_dt;
	  private OpType m_ot;
	  private SymbolTableEntry m_ste;
	  private int m_l;
	  private int m_c;

	  public Token(TokenCode tc, int line, int column) {
	    this(tc, DataType.NONE, OpType.NONE, line, column);
	  }

	  public Token(TokenCode tc, DataType dt, OpType ot, int line, int column) {
	    this(tc, dt, ot, null, line, column);
	  }

	  public Token(TokenCode tc, DataType dt, OpType ot, SymbolTableEntry ste, int line, int column) {
	    m_tc = tc;
	    m_dt = dt;
	    m_ot = ot;
	    m_ste = ste;
	    m_l = line;
	    m_c = column;
	  }

	  public static Token createRaw(TokenCode keywordTokenCode, int line, int column) {
	    return new Token(keywordTokenCode, DataType.NONE, OpType.NONE, line, column);
	  }

	  public static Token createOp(TokenCode opTokenCode, OpType opType, int line, int column) {
	    return new Token(opTokenCode, DataType.OP, opType, line, column);
	  }

	  public static Token createId(SymbolTableEntry symTabEntry, int line, int column) {
	    return new Token(TokenCode.IDENTIFIER, DataType.ID, OpType.NONE, symTabEntry, line, column);
	  }

	  public static Token createInt(SymbolTableEntry symTabEntry, int line, int column) {
	    return new Token(TokenCode.NUMBER, DataType.INT, OpType.NONE, symTabEntry, line, column);
	  }

	  public static Token createReal(SymbolTableEntry symTabEntry, int line, int column) {
	    return new Token(TokenCode.NUMBER, DataType.REAL, OpType.NONE, symTabEntry, line, column);
	  }

	  public static Token createRelOp(String lexeme, int line, int column) {
	    OpType opType = OpType.NONE;
	    if (lexeme.equals("=="))
	      opType = OpType.EQUAL;
	    else if (lexeme.equals("!="))
	      opType = OpType.NOT_EQUAL;
	    else if (lexeme.equals("<"))
	      opType = OpType.LT;
	    else if (lexeme.equals(">"))
	      opType = OpType.GT;
	    else if (lexeme.equals("<="))
	      opType = OpType.LTE;
	    else if (lexeme.equals("=="))
	      opType = OpType.GTE;
	    return Token.createOp(TokenCode.RELOP, opType, line, column);
	  }

	  public static Token createMulOp(String lexeme, int line, int column) {
	    OpType opType = OpType.NONE;
	    if (lexeme.equals("*"))
	      opType = OpType.MULT;
	    else if (lexeme.equals("/"))
	      opType = OpType.DIV;
	    else if (lexeme.equals("%"))
	      opType = OpType.MOD;
	    else if (lexeme.equals("&&"))
	      opType = OpType.AND;
	    return Token.createOp(TokenCode.MULOP, opType, line, column);
	  }


	  public static Token createAddOp(String lexeme, int line, int column) {
	    OpType opType = OpType.NONE;
	    if (lexeme.equals("+"))
	      opType = OpType.PLUS;
	    else if (lexeme.equals("-"))
	      opType = OpType.MINUS;
	    else if (lexeme.equals("||"))
	      opType = OpType.OR;
	    return Token.createOp(TokenCode.ADDOP, opType, line, column);
	  }

	  public TokenCode getTokenCode() {
	    return m_tc;
	  }

	  public DataType getDataType() {
	    return m_dt;
	  }

	  public OpType getOpType() {
	    return m_ot;
	  }

	  public SymbolTableEntry getSymTabEntry() {
	    return m_ste;
	  }
	  
	  public int getLine() {
		  return m_l;
	  }
	  
	  public int getColumn() {
		  return m_c;
	  }
	}
