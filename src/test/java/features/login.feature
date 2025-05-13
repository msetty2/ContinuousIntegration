Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given I open the login page
    When I enter valid username and password
    And I click on the login button
    Then I should be redirected to the homepage
