package Utils;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;

public class Base{

	public static String PrintStringToJson(Object JsonObjectToPrint) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(String.valueOf(JsonObjectToPrint));
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}

}
