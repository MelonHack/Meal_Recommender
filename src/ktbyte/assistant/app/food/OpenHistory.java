package ktbyte.assistant.app.food;

import java.io.BufferedReader;
import java.io.FileReader;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class OpenHistory extends Action {
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		Assistant assistant = Assistant.getInstance();		
		
		try (BufferedReader br = new BufferedReader(new FileReader("Random recipe history"))) {
		   String line;
		   while ((line = br.readLine()) != null) {
			   assistant.displayItem(
				new Response(line));
		   }
		} catch (Exception e) {
			assistant.displayItem(new Response("an error occured"));
		}
		

		
		System.out.println("history opened"); 
		
	}

	
	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if (command.contains("history")) {
			return 5;
		}
		return 0;
	}
}