package ktbyte.assistant.app.food;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class Intro extends Action {
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		
		
		Assistant assistant = Assistant.getInstance();
		
		System.out.println("help popped up"); 
		assistant.displayItem(
				new Response("Enter \"recommend\" for a recommendation of food;\nEnter \"clear\" to clear history\nEnter \"history\" to access your recommend history "));
	}

	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if (command.contains("help")) {
			return 5;
		}
		return 0;
	}
}
