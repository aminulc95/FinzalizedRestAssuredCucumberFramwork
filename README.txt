Designed and implemented by Aminul Chowdhury
Github IRL [https://github.com/aminulc95/FinzalizedRestAssuredCucumberFramwork.git]

This framework is for API testing
-------------------------------------------------------------------------------------------------------------------------------

FilePaths
    -StepDefs [src\main\java\StepDefinitions]
    -Utils [src\main\java\Utils]
    -FeatureFiles [src\test\java\FeatureFiles]
    -Auto Generated Reports [reports\cucumber-html-report\index.html]
-------------------------------------------------------------------------------------------------------------------------------

MyRunner.java details
    @RunWith(Cucumber.class)                                        <- Run details
    @CucumberOptions(                                               <- CucumberOptions used to set properties for runner class
           plugin = {"html:reports/cucumber-html-report",           <- Maven surefire plugin config used for generating report
                   "json:reports/cucumber.json",
                   "pretty"},
           tags = {"@runit,@Run","~@ignore"},                       <- Tags that will be looked for in feature files in order to run scenarios
           features = {"src/test/java/FeatureFiles"},               <- Feature files parent directory
           glue = {"StepDefinitions", "Utils"}                      <- StepDefs parent directory
    )
-------------------------------------------------------------------------------------------------------------------------------

    @Given("^I connect to URL \"([^\"]*)\" with endpoint \"([^\"]*)\" and verify the status code is \"([^\"]*)\"$")                     <- Cucumber glue tag
    public void iConnectToURLWithEndpointAndVerifyTheStatusCodeIs(String URL, String EndPoint, int StatusCode) throws Throwable {       <- Java method name
        RestAssured.baseURI = URL;                                                                                                      <- Syntax baseURL is set as 'URL' is passed from feature file
        RequestSpecification httpRequest = RestAssured.given();                                                                         <- Syntax to create a request
        Response response = httpRequest.get(EndPoint);                                                                                  <- Syntax to send request to Endpoint and save data as object 'response'
        ResponseBody body = response.getBody();                                                                                         <- Used to print response body in console
        System.out.println("Response Body is: \n" + body.asString());
        Assert.assertEquals(StatusCode, response.getStatusCode());                                                                      <- Verify Status code is as expected
    }
-------------------------------------------------------------------------------------------------------------------------------

    @Given("^I connect to URL \"([^\"]*)\" with endpoint \"([^\"]*)\" and POST with details such as Name=\"([^\"]*)\", Salary=\"([^\"]*)\", Age=\"([^\"]*)\"\\. Then verify the status code=\"([^\"]*)\"$")                    <- Cucumber glue tag
    public void iConnectToURLWithEndpointAndPOSTWithDetailsSuchAsNameSalaryAgeThenVerifyTheStatusCode(String URL, String EndPoint, String Name, String Salary, String Age, int StatusCode) throws JSONException {              <- Java method name
        RestAssured.baseURI = URL;                                                                                                                                                                                             <- Syntax baseURL is set as 'URL' is passed from feature file
        RequestSpecification request = RestAssured.given();                                                                                                                                                                    <- Syntax to create a request
        JSONObject requestParams = new JSONObject();                                                                                                                                                                           <- Creating a Json object in order to be able to interact with API
        requestParams.put("name", Name);                                                                                                                                                                                       <- Adding a parameter to the Json object
        requestParams.put("salary", Salary);
        requestParams.put("age", Age);
        System.out.println("Request body is: \n" + PrintJson(requestParams.toString()));                                                                                                                                       <- Used to print request body in console
        request.body(requestParams.toString());
        Response response = request.post(EndPoint);                                                                                                                                                                            <- Syntax to send request to Endpoint and save data as object 'response'
        System.out.println("Response body is: \n" + PrintJson(response.asString()));                                                                                                                                           <- Used to print response body in console
        Assert.assertEquals(StatusCode, response.getStatusCode());                                                                                                                                                             <- Verify Status code is as expected
    }
-------------------------------------------------------------------------------------------------------------------------------
Feature: Perform API Validation Using Rest Assured Libraries

  @Run                                                                                                                                                                                          <- @Run required to run via MyRunner (@'Tag' can be modified in MyRunner.java)
  Scenario Outline: Testing GET Method
    Given I connect to URL "<URL>" with endpoint "<Endpoint>" and verify the status code is "<StatusCode>"
    Examples:
      | URL                             | Endpoint          | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |

  @Run                                                                                                                                                                                          <- @Run required to run via MyRunner (@'Tag' can be modified in MyRunner.java)
  Scenario Outline: Adding Details for Employee "<Name>" to Data Base then verify status is successful
    Given I connect to URL "<URL>" with endpoint "<Endpoint>" and POST with details such as Name="<Name>", Salary="<Salary>", Age="<Age>". Then verify the status code="<StatusCode>"
    Examples:
      | URL                             | Endpoint       | Name          | Salary | Age | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/create | John Jackson  | 100000 | 20  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jack Jackson  | 200000 | 30  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jeff Jackson  | 300000 | 40  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jorge Jackson | 400000 | 50  | 200        |
-------------------------------------------------------------------------------------------------------------------------------

HTML report will generate post test execution and be saved in above mentioned path. Right click and open with browser to view
