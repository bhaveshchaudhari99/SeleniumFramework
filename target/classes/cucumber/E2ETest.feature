@tag
Feature: E2E Test Case
 
  Background:
	Given Navigate to baseUrl
 
  @Regression
  Scenario Outline: Positive E2E test scenario
    Given Login with username <username> and password <password>
    When added product <product> to cart and checkout	 
    Then verify the details "***"

    Examples: 
      | username      | password     | product    |
      | yog@gmail.com | Yogesh@12345 | Nikon D300 |	
