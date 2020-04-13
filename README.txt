Github URL [https://github.com/aminulc95/FinzalizedRestAssuredCucumberFramwork.git]

This framework is for automating REST services using Cucumber/Java/RestAssured
-------------------------------------------------------------------------------------------------------------------------------

To Run taged sceanrios from feature files navigate to Runner.java and press start
-------------------------------------------------------------------------------------------------------------------------------

FilePaths
    -StepDefs [src\main\java\StepDefinitions]
    -Base [src\main\java\Utils\Base.java]
    -Runner [src\main\java\Utils\Runner.java]
    -FeatureFiles [src\test\java\FeatureFiles]
    -Auto Generated Reports [reports\cucumber-html-report\index.html]
-------------------------------------------------------------------------------------------------------------------------------

Base.java used to store all common functions. can be called in any java class by 'extending Base' then calling methods using Base.'methodName'
-------------------------------------------------------------------------------------------------------------------------------

Runner.java details
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

    @Given("^I connect to URL=\"([^\"]*)\" with Endpoint=\"([^\"]*)\"\\. Then verify StatusCode=\"([^\"]*)\"$")   	<- Cucumber glue tag
    public void iConnectToURLWithEndpointThenVerifyStatusCode(String URL, String EndPoint, int StatusCode) {		<- Java method name
        //Request
        RestAssured.baseURI = URL;																					<- Syntax baseURL is set as 'URL' is passed from feature file
        RequestSpecification httpRequest = RestAssured.given();														<- Syntax to create a request
        Response response = httpRequest.get(EndPoint);																<- Syntax to initiate a GET request
        //Response
        System.out.println("Response body is: \n" + Base.PrintStringToJson(response.asString()));					<- Used to print response body in console
        //Assertion
        Assert.assertEquals(StatusCode, response.getStatusCode());													<- Verify Status code is as expected
    }
-------------------------------------------------------------------------------------------------------------------------------

@Run																										<- @Run placed at top to run all scenarios in feature file via Runner (@'Tag' can be modified in Runner.java)
Feature: Perform API Validation Using Rest Assured Libraries

																											<- @Run place at top of scenario to run single scenario in feature file via Runner (@'Tag' can be modified in Runner.java)
  Scenario Outline: Scenario1 Performing GET method to get all employee data
    Given I connect to URL="<URL>" with Endpoint="<Endpoint>". Then verify StatusCode="<StatusCode>"
    Examples:
      | URL                             | Endpoint          | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |

-------------------------------------------------------------------------------------------------------------------------------

HTML report will generate post test execution and be saved in above mentioned path. Right click and open with browser to view
