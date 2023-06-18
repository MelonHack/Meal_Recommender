package ktbyte.assistant.app.food;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class ClearRecipeAction extends Action {

	@Override
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		File file = new File("Random recipe history");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
			writer.write("");
			writer.close();
		} catch(Exception e) {
			System.out.println("Request error occured: " + e);
		}
		
		Assistant assistant = Assistant.getInstance();
		
		System.out.println("history cleared"); 
		assistant.displayItem(
				new Response("The history is cleared successfully."));
	}
	
	@Override
	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if (command.contains("clear")) {
			return 5;
		}
		return 0;
	}
}
