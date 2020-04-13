package StepDefinitions;


import Utils.Base;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;


public class ExampleRestAssuredStepDefs  {

    String EndPoint;

    @Given("^I connect to URL=\"([^\"]*)\" with Endpoint=\"([^\"]*)\"\\. Then verify StatusCode=\"([^\"]*)\"$")
    public void iConnectToURLWithEndpointThenVerifyStatusCode(String URL, String EndPoint, int StatusCode) {
        //Request
        RestAssured.baseURI = URL;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(EndPoint);
        //Response
        System.out.println("Response body is: \n" + Base.PrintStringToJson(response.asString()));
        //Assertion
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }

    @Given("^I connect to URL=\"([^\"]*)\" with Endpoint=\"([^\"]*)\" and get employee data for employee with ID= \"([^\"]*)\"\\. Then verify StatusCode=\"([^\"]*)\"$")
    public void iConnectToURLWithEndpointAndGetEmployeeDataForEmployeeWithIDThenVerifyTheStatusCode(String URL, String EndPoint, String ID, int StatusCode) throws Throwable {
        //Request
        RestAssured.baseURI = URL;
        EndPoint = EndPoint + ID;
        RequestSpecification httpRequest = RestAssured.given();
        System.out.println("Request sent to URL: " + URL + EndPoint);
        Response response = httpRequest.get(EndPoint);
        //Response
        System.out.println("Response body is: \n" + Base.PrintStringToJson(response.asString()));
        //Assertion
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }

    @Given("^I connect to URL=\"([^\"]*)\" with Endpoint=\"([^\"]*)\" and POST with details such as Name=\"([^\"]*)\", Salary=\"([^\"]*)\", Age=\"([^\"]*)\"\\. Then verify StatusCode=\"([^\"]*)\"$")
    public void iConnectToURLWithEndpointAndPOSTWithDetailsSuchAsNameSalaryAgeThenVerifyStatusCode(String URL, String EndPoint, String Name, String Salary, String Age, int StatusCode) throws Throwable {
        //Request
        RestAssured.baseURI = URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", Name);
        requestParams.put("salary", Salary);
        requestParams.put("age", Age);
        System.out.println("Request body is: \n" + Base.PrintStringToJson(requestParams.toString()));
        request.body(requestParams.toString());
        Response response = request.post(EndPoint);
        //Response
        System.out.println("Response body is: \n" + Base.PrintStringToJson(response.asString()));
        //Assertion
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }

    @Given("^I connect to URL=\"([^\"]*)\" with Endpoint=\"([^\"]*)\" and for employee with ID=\"([^\"]*)\" I PUT with details such as Name=\"([^\"]*)\", Salary=\"([^\"]*)\", Age=\"([^\"]*)\"\\. Then verify StatusCode=\"([^\"]*)\"$")
    public void iConnectToURLWithEndpointAndForEmployeeWithIDIPUTWithDetailsSuchAsNameSalaryAgeThenVerifyStatusCode(String URL, String EndPoint, String ID, String Name, String Salary, String Age, int StatusCode) throws Throwable {
        //Request
        RestAssured.baseURI = URL;
        EndPoint = EndPoint + ID;
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", Name);
        requestParams.put("salary", Salary);
        requestParams.put("age", Age);
        System.out.println("Request sent to URL: " + URL + EndPoint);
        System.out.println("Request body is: \n" + Base.PrintStringToJson(requestParams.toString()));
        request.body(requestParams.toString());
        Response response = request.put(EndPoint);
        //Response
        System.out.println("Response body is: \n" + Base.PrintStringToJson(response.asString()));
        //Assertion
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }

    @Given("^I connect to URL=\"([^\"]*)\" with Endpoint=\"([^\"]*)\" and for employee with ID=\"([^\"]*)\" I delete records\\. Then verify StatusCode=\"([^\"]*)\"$")
    public void iConnectToURLWithEndpointAndForEmployeeWithIDIDeleteRecordsThenVerifyStatusCode(String URL, String EndPoint, String ID, int StatusCode) throws Throwable {
        //Request
        RestAssured.baseURI = URL;
        EndPoint = EndPoint + ID;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.delete(EndPoint);
        //Response
        System.out.println("Response body is: \n" + Base.PrintStringToJson(response.asString()));
        //Assertion
        Assert.assertEquals(StatusCode, response.getStatusCode());
    }

}
