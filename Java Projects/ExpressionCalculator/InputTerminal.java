/**
 * @author Ace Reyes
 * Created on July 4, 2023
 */


package expressionCalculator;

import java.util.Scanner;

public class InputTerminal {
	public static void main(String[] args) {
		InputProcessor f = new InputProcessor();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.println(
		"=======================================================================================" +
		"\n|The Expression Calculator|" +
		"\n" +
		"\nWelcome!" +
		"\n" + 
		"\nIn order to use this calculator, input expressions into the terminal" +
		"\nExample expressions include: 1 + 2, 3 * 12, 5(5 - 2)" +
		"\n" +
		"\nThis calculator only recognizes numbers, operators, parenthesis, and 'A'" +
		"\nRecognized operators: +, -, /, *, ^, %" +
		"\nThe character 'A' stands for the result of the previous input" +
		"\n" +
		"\nIn order to exit, type 'Exit'" +
		"\n=======================================================================================");
		
		while(true) {
			String input = scan.nextLine();
			if(input.equals("Exit")) {
				System.out.println("Exiting calculator...");
				break;
			}
			System.out.println(f.process(input));
		}
	}
}
