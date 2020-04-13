@Run
Feature: Perform API Validation Using Rest Assured Libraries


  Scenario Outline: Scenario1 Performing GET method to get all employee data
    Given I connect to URL="<URL>" with Endpoint="<Endpoint>". Then verify StatusCode="<StatusCode>"
    Examples:
      | URL                             | Endpoint          | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |


  Scenario Outline: Scenario2 Performing GET method to get specific employee with ID="<ID>"
    Given I connect to URL="<URL>" with Endpoint="<Endpoint>" and get employee data for employee with ID= "<ID>". Then verify StatusCode="<StatusCode>"
    Examples:
      | URL                             | Endpoint          | ID | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/employee/ | 4  | 400        |
      | http://dummy.restapiexample.com | /api/v1/employee/ | 5  | 400        |
      | http://dummy.restapiexample.com | /api/v1/employee/ | 6  | 400        |


  Scenario Outline: Scenario3 Adding details for employee Name="<Name>" to data base then verify StatusCode="<StatusCode>"
    Given I connect to URL="<URL>" with Endpoint="<Endpoint>" and POST with details such as Name="<Name>", Salary="<Salary>", Age="<Age>". Then verify StatusCode="<StatusCode>"
    Examples:
      | URL                             | Endpoint       | Name         | Salary | Age | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/create | John Jackson | 100000 | 20  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jack Jackson | 200000 | 30  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jeff Jackson | 300000 | 40  | 200        |


  Scenario Outline: Scenario4 Update details for employee Name="<Name>" to data base then verify StatusCode="<StatusCode>"
    Given I connect to URL="<URL>" with Endpoint="<Endpoint>" and for employee with ID="<ID>" I PUT with details such as Name="<Name>", Salary="<Salary>", Age="<Age>". Then verify StatusCode="<StatusCode>"
    Examples:
      | URL                             | Endpoint        | ID | Name         | Salary | Age | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/update/ | 1  | John Jackson | 100000 | 20  | 200        |
      | http://dummy.restapiexample.com | /api/v1/update/ | 2  | Jack Jackson | 100000 | 20  | 200        |
      | http://dummy.restapiexample.com | /api/v1/update/ | 3  | Jeff Jackson | 100000 | 20  | 200        |



  Scenario Outline: Scenario5 Delete details for employee ID="<ID>" from data base then verify StatusCode="<StatusCode>"
    Given I connect to URL="<URL>" with Endpoint="<Endpoint>" and for employee with ID="<ID>" I delete records. Then verify StatusCode="<StatusCode>"
    Examples:
      | URL                             | Endpoint        | ID | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/delete/ | 1  | 200        |
      | http://dummy.restapiexample.com | /api/v1/delete/ | 2  | 200        |
      | http://dummy.restapiexample.com | /api/v1/delete/ | 3  | 200        |



