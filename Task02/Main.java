package projetos;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import projetos.Token;
import projetos.TokenType;

public class Main {
	
	// Método para calcular usando TokenType como switch-case
	public static double calc(double a, double b, TokenType operation){
		switch(operation){
			case STAR: 
				return a*b;
			case SLASH:
				return a/b;
			case MINUS:
				return a-b;
			case PLUS:
				return a+b;
			}
		return 0.0;
	}

    public static void main(String args[]) throws Exception {
    	Scanner in = new Scanner(System.in);
    	String Tokens = "";
    	Stack<Token> s = new Stack<Token>();
    	
    	while(in.hasNextLine()){
	    	String s_input = in.nextLine();
	    	
	    	// Variáveis para salvar operações e valores
	    	char   c_input = ' ';
	    	Double d_input = 0.0;
	    	TokenType tt = TokenType.EOF;
	    	
	    	
	    	
	    	if(s_input.matches("-?\\d+(\\.\\d+)?")) {

	    		// Identifica expressão regular de números
	    		d_input = Double.parseDouble(s_input);
	    		tt = TokenType.NUM;
	    		
	    		// Salva o número na pilha
	    		s.push(new Token(TokenType.NUM, s_input));
				Tokens = Tokens.concat(new Token(tt, s_input).toString()+"\n");
				
				// Debug
	    		// System.out.println(new Token(tt, s_input).toString());
	    		
	    	} else if (s_input.strip().length() == 1) {
	    		
	    		c_input = s_input.strip().charAt(0);
	    		switch(c_input) {
	    			case '-':
	    				tt = TokenType.MINUS; break;
	    			case '+':
	    				tt = TokenType.PLUS; break;
	    			case '/':
	    				tt = TokenType.SLASH; break;
	    			case '*':
	    				tt = TokenType.STAR; break;
	    			default:
	    				throw new Exception("Error: Unexpected character: "+s_input.strip().charAt(0));
	    		}
				
	    		s.push(new Token(tt, ""+c_input));
	    		
	    		Tokens = Tokens.concat(new Token(tt, ""+c_input).toString()+"\n");
	    		
	    		// Debug
	    		// System.out.println(new Token(tt, ""+c_input).toString());
	    		
	    	} else if(s_input.strip().length() == 0) {
	    		
	    		tt = TokenType.EOF;
	    		
	    		Tokens = Tokens.concat(new Token(tt, "").toString()+"\n");
	    		
	    		// Pega operação e dois valores
	    		TokenType operation = s.pop().type;
				Double b = Double.parseDouble(s.pop().lexeme);
				Double a = Double.parseDouble(s.pop().lexeme);
				
				// Realiza operação e coloca resultado na pilha
				s.push(new Token(TokenType.NUM, ""+calc(a,b,operation)));
				
	    		System.out.printf("Resultado: "+s.pop().lexeme+"\n");

	    		// Debug
	    		// System.out.println(new Token(tt, "").toString());
	    		
	    		break;
	    		
	    	} else {
	    		
	    		throw new Exception("Houve um erro na leitura da entrada: "+s_input);
	    		
	    	}
	    	
	    	// Token t = new Token(tt, s_input.strip().charAt(0)+"");
	    	// System.out.println(t.toString());
	    	
    	}
    	
    	System.out.print(Tokens);
    	in.close();
    	
    	return;
    }
}