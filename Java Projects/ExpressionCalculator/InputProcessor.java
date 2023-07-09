/**
 * @author Ace Reyes
 * Created on July 4, 2023
 */


package calculator;

public class InputProcessor {
	InputValidator v = new InputValidator();
	Calculator c = new Calculator();
	public String process(String input) {
		boolean pass = v.validate(input);
		if(!pass) {
			return v.error();
		}
		return "valid"; //TODO: to be changed later to actually display a result
	}
}
