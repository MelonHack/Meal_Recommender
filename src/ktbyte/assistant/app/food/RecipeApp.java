package ktbyte.assistant.app.food;

import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;


public class RecipeApp extends App {

	public List<Action> getActions() {
		return Arrays.asList(new GetRecipeAction(), new ClearRecipeAction(), new Intro(), new OpenHistory(), new AllergenDetector(), new AllergenDetectorDis());
	}
}
