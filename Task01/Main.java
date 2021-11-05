import java.util.Scanner;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

// RPNStacker - Adhoc

class Main {

	public static double calc(double a, double b, String operation){
		switch(operation){
		case "*": 
			return a*b;
		case "/":
			return a/b;
		case "-":
			return a-b;
		case "+":
			return a+b;
		}
		return 0.0;
	}    

	public static void main(String args[]) {
		// Pilha para cálculo
		Stack<String> s = new Stack<String>();
		
		// Conjunto de operadores que pode ser expandido posteriormente
		Set<String> operadores = new HashSet<String>();
		
		operadores.add("*"); operadores.add("/"); operadores.add("-"); operadores.add("+");

		Scanner in = new Scanner(System.in);
		String operation; double a; double b;

		while(in.hasNextLine()){
			s.push(in.nextLine());
			if(operadores.contains(s.peek())){
				// Se input for operação: fazer cálculo com últimos números da pilha
				operation = s.pop();
				b = Double.parseDouble(s.pop());
				a = Double.parseDouble(s.pop());
				// Resultado parcial: System.out.println(calc(a,b,operation));
				s.push(""+calc(a,b,operation));
			} else if (s.peek().length() == 0 || s.peek().equals("=")) {
				// Linha em branco ou caractere de igual: efetuar cálculo
				s.pop();
				System.out.print("Resultado: ");
				System.out.println(s.peek());
				break;
			}

		}
	}
}

// Input:
// 4
// 8
// +
// 3
// *
