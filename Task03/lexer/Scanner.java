/* *******************************************************************
 * Copyright (c) 2021 Universidade Federal de Pernambuco (UFPE).
 * 
 * This file is part of the Compilers course at UFPE.
 * 
 * During the 1970s and 1980s, Hewlett-Packard used RPN in all 
 * of their desktop and hand-held calculators, and continued to 
 * use it in some models into the 2020s. In computer science, 
 * reverse Polish notation is used in stack-oriented programming languages 
 * such as Forth, STOIC, PostScript, RPL and Joy.
 *  
 * Contributors: 
 *     Henrique Rebelo      initial design and implementation 
 *     http://www.cin.ufpe.br/~hemr/
 * ******************************************************************/

package postfix.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author Henrique Rebelo
 */
public class Scanner {

	private final String source;
	private final List<Token> tokens = new ArrayList<>();
	private final HashMap<String, String> hm;

	public Scanner(String source, HashMap<String, String> hm) {
		this.source = source;
		this.hm = hm;
	}

	/**
	 * Scan the source program [as a String]
	 * store the tokens as a List<Token> 
	 * 
	 * @param program
	 * @return the list of tokens
	 */
	public List<Token> scan() {
		return this.scan(this.source);
	}
	
	/**
	 * Scan the given program [as a String]
	 * store the tokens as a List<Token> 
	 * 
	 * @param program
	 * @return the list of tokens
	 */
	public List<Token> scan(String program) {
		StringTokenizer tokenizer = new StringTokenizer(program, Token.TOKENIZER_DELIMITER); 
		// processing each tokenized word
		while (tokenizer.hasMoreElements()) {
			String tokenStr = tokenizer.nextToken();
			this.tokens.add(this.getToken(tokenStr));
		}
		this.tokens.add(new Token(TokenType.EOF, "")); // EOF
		
		return this.tokens;
	}

	// -------------------------------------------------------------
	// HELPERS METHODS
	// -------------------------------------------------------------

	private Token getToken(String token) {
		Token ret = null;
		if(Regex.isNum(token)) {
			ret = new Token(TokenType.NUM, token);
		}
		else if(Regex.isOP(token)) {
			ret = new Token(Regex.getOPTokenType(token), token);
		}
		else if(token.length() > 0 && hm.containsKey(token)) {
			ret = new Token(TokenType.NUM, hm.get(token));
		}
		else {
			throw new LexError(token+" cannot be resolved!\n");
		}
		return ret;
	}
}