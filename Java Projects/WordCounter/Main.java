/**
 * @author Ace Reyes
 * Created on July 29, 2023
 */

package wordCounter;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] files) {
		FileReader processor = new FileReader();
		SearchTree tree = new SearchTree();
		
		for(String file: files) {
			
			//==Scan file==
			System.out.println("Scanning file: " + file + "...\n");
			long startTime = System.currentTimeMillis();
			processor.read(file);
			ArrayList<String> list = processor.returnList();
			tree.analyze(list);
			long endTime = System.currentTimeMillis();
			//=============
			
			//==Extract and output result==
			long totalTime = endTime - startTime;
			System.out.println("Time taken to fully process file: " + totalTime + " milliseconds\n");
			System.out.println(tree.getResult());
			//=============================
		}
	}
}
