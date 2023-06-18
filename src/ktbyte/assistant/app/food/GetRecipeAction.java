package ktbyte.assistant.app.food;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;
import java.io.*;
public class GetRecipeAction extends Action {

		String[] keywords = { "recommend", "food", "recipe", "random", "give" };
		double[] scores =   { 8,     5	    ,  5      ,  3    ,  3 };
		
		private static final String API_KEY = "1";
		
		@Override
		public void doCommand(String command){
	 
			// remove keywords from the command to extract the location
			// we want to query the weather for
			List<String> words = Arrays.asList(command.split(" "));
	 
			HttpRequest req = null;
			String location = words.get(words.size() - 1);
			
			if (words.contains("zip") || words.contains("zipcode")) {
				req = Unirest.get("https://themealdb.com/api/json/v1/1/random.php")
				             .queryString("zip", location)
						         .queryString("appid", API_KEY)
						         .queryString("units", "imperial");
				
			} else {
				req = Unirest.get("https://themealdb.com/api/json/v1/1/random.php")
				    .queryString("q", location)
						.queryString("appid", API_KEY)
						.queryString("units", "imperial");
			}
	 
	    System.out.println(req.getUrl());
			try {
			  HttpResponse<JsonNode> boom = req.asJson();
			  System.out.println(boom);
				JsonNode node = boom.getBody();
				System.out.println(node);
				handleResult(node);
			} catch (Exception e) {
				System.out.println("request error occurred: " + e);
			}
	 
		}
	 
		@Override
		public double getLikelihood(String command) {
			double score = 0;
			for (int i = 0; i < keywords.length; i++) {
				String keyword = keywords[i];
				if (command.contains(keyword)) {
					score += scores[i];
				}
			}
			return score;
		}
		
		public void getAllergen(JsonNode node) throws IOException, FileNotFoundException {
			
			Assistant assistant = Assistant.getInstance();
			
			JSONObject json = node.getObject();
			
			boolean l = AllergenDetector.al;
			String recipe = json.optJSONArray("meals").optJSONObject(0).optString("strInstructions");
			if (l) {
				String[] someAllergens = new String[5];
				someAllergens[0] = "milk";
				someAllergens[1] = "nut";
				someAllergens[2] = "egg";
				someAllergens[3] = "soy";
				someAllergens[4] = "gluten";
				
				for (int i = 0; i < 5; i ++) {
					if (recipe.contains(someAllergens[i])) {
						assistant.displayItem(new Response("********WARNING, this meal contains" + someAllergens[i] + "********"));
					}
				}
				System.out.println("allergen detected");
			}
		}
		
		private static void handleResult(JsonNode node) throws IOException, FileNotFoundException {
			  
			Assistant assistant = Assistant.getInstance();
			  
		 
			JSONObject json = node.getObject();
				
			String food = json.optJSONArray("meals").optJSONObject(0).optString("strMeal");
			String recipe = json.optJSONArray("meals").optJSONObject(0).optString("strInstructions");
		 
			
			//return result
			String s = "The recipe of a random food: " + recipe;
			System.out.println(s);
			assistant.displayItem(
					new Response("The recipe of " + food + " is: " + recipe));
			
			
			//write history
			File file = new File("Random recipe history");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.write("\n\n********searched:\n" + s);
			writer.close();
			
			
			//allergen check
			boolean l = AllergenDetector.al;
			recipe = json.optJSONArray("meals").optJSONObject(0).optString("strInstructions");
			if (l) {
				String[] someAllergens = new String[5];
				someAllergens[0] = "milk";
				someAllergens[1] = "nut";
				someAllergens[2] = "egg";
				someAllergens[3] = "soy";
				someAllergens[4] = "gluten";
				
				for (int i = 0; i < 5; i ++) {
					if (recipe.contains(someAllergens[i])) {
						assistant.displayItem(new Response("********WARNING, this meal contains " + someAllergens[i] + "********"));
					}
				}
				System.out.println("allergen detected");
			}
		}
}
