/**
 * @author Ace Reyes
 * Created on July 29, 2023
 */

package wordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileReader {
	ArrayList<String> wordList = new ArrayList<>();
	
	public void read(String file) {
		File doc = new File(file);
		Scanner scan = null;
		boolean fileFound = false;
		
		try {
			scan = new Scanner(doc);
			fileFound = true;
		}
		catch(FileNotFoundException e) {
			System.out.println("File '" + file + "' not found\n");
		}
		
		if(fileFound) {
			while(scan.hasNext()) {
				String word = scan.next();
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i < word.length(); i++) {
					if(Character.isJavaIdentifierStart(word.charAt(i))){
						builder.append(word.charAt(i));
					}
				}
				if(builder.length() > 0) {
					wordList.add(builder.toString());
				}
			}
		}
	}
	
	public ArrayList<String> returnList(){
		return wordList;
	}
	
}
