package ktbyte.assistant.app.food;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class AllergenDetector extends Action {
	
	public static boolean al = false;
	
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		
		Assistant assistant = Assistant.getInstance();
		
		al = true;
		
		System.out.println("allergen detector on");
		
		assistant.displayItem(new Response("allergen detector on"));
	}

	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if (command.equals(".")) {
			return 4;
		}
		return 0;
	}
}