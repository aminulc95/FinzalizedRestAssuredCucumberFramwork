package StepDefinitions;


import cucumber.api.java.en.Given;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;


public class ExampleRestAssuredStepDefs{

    @Given("^I connect to URL \"([^\"]*)\" with endpoint \"([^\"]*)\" and verify the status code is \"([^\"]*)\"$")
    public void iConnectToURLWithEndpointAndVerifyTheStatusCodeIs(String URL, String EndPoint, int StatusCode) throws Throwable {
        RestAssured.baseURI = URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(EndPoint);
        ResponseBody body = response.getBody();
        System.out.println("Response Body is: \n" + body.asString());
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }

    @Given("^I connect to URL \"([^\"]*)\" with endpoint \"([^\"]*)\" and POST with details such as Name=\"([^\"]*)\", Salary=\"([^\"]*)\", Age=\"([^\"]*)\"\\. Then verify the status code=\"([^\"]*)\"$")
    public void iConnectToURLWithEndpointAndPOSTWithDetailsSuchAsNameSalaryAgeThenVerifyTheStatusCode(String URL, String EndPoint, String Name, String Salary, String Age, int StatusCode) throws JSONException {
        RestAssured.baseURI = URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", Name);
        requestParams.put("salary", Salary);
        requestParams.put("age", Age);
        System.out.println("Request body is: \n" + PrintJson(requestParams.toString()));
        request.body(requestParams.toString());
        Response response = request.post(EndPoint);
        System.out.println("Response body is: \n" + PrintJson(response.asString()));
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }


    public String PrintJson(Object JsonObjectToPrint){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(String.valueOf(JsonObjectToPrint));
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

}
