/**
 * @author Ace Reyes
 * Created on July 4, 2023
 */


package expressionCalculator;

public class InputValidator {
	String errorMessage = "Erroneous error: If you see this message, there is an error within this code";
	int parenthesisCount = 0;
	
	public boolean validate(String input) {
		boolean precedingNumber = false;
		boolean precedingOperator = false;
		boolean precedingAns = false;
		boolean precedingDecimal = false;
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
				precedingAns = false;
				precedingNumber = false;
				precedingOperator = (openParenthesis)? false : true;
				precedingDecimal = false;
			}
			
			if(c == '-' && precedingOperator) {
				errorMessage = "Format error: Operators must be preceded by numbers";
				return false;
			}
			else if(c == '-' && !precedingOperator) {
				precedingAns = false;
				precedingNumber = false;
				precedingOperator = true;
				precedingDecimal = false;
			}

			if(c == 'A' && precedingDecimal) {
				errorMessage = "Format error: Previous answers 'A' cannot be placed within decimal values";
				return false;
			}
			if(c == 'A' && precedingNumber) {
				errorMessage = "Format error: Previous answers 'A' must be preceded by an operator";
				return false;
			}
			else if(c == 'A' && !precedingNumber) {
				precedingAns = true;
				precedingNumber = true;
				precedingOperator = false;
				precedingDecimal = false;
				containsNumbers = true;
			}

			if(c == '.' && precedingDecimal) {
				errorMessage = "Format error: Decimals must be followed up with numbers";
				return false;
			}
			else if(c == '.' && !precedingDecimal) {
				 precedingDecimal = true;
				 precedingOperator = false;
			}
			if(c == '.' && precedingAns) {
				errorMessage = "Format error: Previous answers 'A' must exist as a separate number";
				return false;
			}
			
			if(c == ')' && !openParenthesis) {
				errorMessage = "Format error: Parenthesized expressions must begin with an opening parenthesis";
				return false;
			}
			else if(c == ')' && openParenthesis){
				precedingAns = false;
				precedingNumber = false;
				openParenthesis = parenthesisCounter();
			}
			
			if(c == '(') {
				openParenthesis = true;
				precedingAns = false;
				precedingNumber = false;
				precedingOperator = false;
				precedingDecimal = false;
				parenthesisCount++;
			}
			
			if(Character.isDigit(c) || c == 'A') {
				precedingAns = false;
				precedingNumber = true;
				precedingOperator = false;
				precedingDecimal = false;
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
