package ktbyte.assistant.app.food;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class AllergenDetectorDis extends Action {
	
	public void doCommand(String command) {
		// TODO Auto-generated method stub
			
			Assistant assistant = Assistant.getInstance();
		
			AllergenDetector.al = false;
			
			System.out.println("allergen detector off");
			
			assistant.displayItem(new Response("allergen detector off"));
	}

	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if (command.equals(" ")) {
			return 4;
		}
		return 0;
	}
}