/**
 * @author Ace Reyes
 * Created on July 4, 2023
 */


package calculator;

public class InputValidator {			
	String errorMessage = "Erroneous error: If you see this message, there is an error within this code";
	int parenthesisCount = 0;
	
	public boolean validate(String input) {
		boolean precedingNumber = false;
		boolean precedingOperator = false;
		boolean openParenthesis = false;
		boolean containsNumbers = false;
		
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			
			if(c == ' ') {
				continue;
			}
			
			if(Character.isAlphabetic(c) && c != 'A') {
				errorMessage = "Invalid input: Input cannot contain unrecognized letters";
				return false;
			}
			
			if(!Character.isDigit(c) && (c!='+' && c!='-' && c!='/' && c!='*' && c!='^' && c!='%' && c!='(' && c!=')' && c!='A')) {
				errorMessage = "Invalid input: Input contains unknown characters";
				return false;
			}
			
			if(!precedingNumber && (c=='+' || c=='/' || c=='*' || c=='^' || c=='%')) {
				errorMessage = "Format error: Operators must be preceded by numbers";
				return false;
			}
			else if(precedingNumber && (c=='+' || c=='/' || c=='*' || c=='^' || c=='%')){
				precedingNumber = false;
				precedingOperator = (openParenthesis)? false : true;
			}
			
			if(c == '-' && precedingOperator) {
				errorMessage = "Format error: Operators must be preceded by numbers";
				return false;
			}
			else if(c == '-' && !precedingOperator) {
				precedingOperator = true;
			}
			
			if(c == ')' && !openParenthesis) {
				errorMessage = "Format error: Parenthesized expressions must begin with an opening parenthesis";
				return false;
			}
			else if(c == ')' && openParenthesis){
				openParenthesis = parenthesisCounter();
			}
			
			if(c == '(') {
				openParenthesis = true;
				precedingOperator = false;
				parenthesisCount++;
			}
			
			if(Character.isDigit(c) || c == 'A') {
				precedingNumber = true;
				precedingOperator = false;
				containsNumbers = true;
			}
		}
		
		if(!containsNumbers) {
			errorMessage = "Invalid input: Input must contain numbers";
			return false;
		}
		if(precedingOperator) {
			errorMessage = "Format error: Expressions cannot have hanging operators";
			return false;
		}
		if(openParenthesis) {
			errorMessage = "Format error: Expressions must fully close all parenthesis";
			return false;
		}
		
		return true;
	}
	
	private boolean parenthesisCounter() { //This function is separated to prevent over-nesting of code
		if(parenthesisCount > 0) {
			parenthesisCount--;
		}
		if(parenthesisCount == 0) {
			return false;
		}
		return true;
	}
	
	public String error() {
		return errorMessage;
	}
}
