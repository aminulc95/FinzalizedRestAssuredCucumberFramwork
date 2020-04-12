Feature: Perform API Validation Using Rest Assured Libraries

  @Run
  Scenario Outline: Testing GET Method
    Given I connect to URL "<URL>" with endpoint "<Endpoint>" and verify the status code is "<StatusCode>"
    Examples:
      | URL                             | Endpoint          | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |
      | http://dummy.restapiexample.com | /api/v1/employees | 200        |

  @Run
  Scenario Outline: Adding Details for Employee "<Name>" to Data Base then verify status is successful
    Given I connect to URL "<URL>" with endpoint "<Endpoint>" and POST with details such as Name="<Name>", Salary="<Salary>", Age="<Age>". Then verify the status code="<StatusCode>"
    Examples:
      | URL                             | Endpoint       | Name          | Salary | Age | StatusCode |
      | http://dummy.restapiexample.com | /api/v1/create | John Jackson  | 100000 | 20  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jack Jackson  | 200000 | 30  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jeff Jackson  | 300000 | 40  | 200        |
      | http://dummy.restapiexample.com | /api/v1/create | Jorge Jackson | 400000 | 50  | 200        |



