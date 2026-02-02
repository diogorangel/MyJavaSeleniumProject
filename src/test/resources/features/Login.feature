#encoding: iso-8859-1
################################################
########## Scenarios: Automations Tests ################
################################################
@Login_AllTests
Feature: Automation Tests Scenarios

  @001_Login_StandardUser
  Scenario: Successful login with standard user
    Given I am on the SauceDemo login page
    When I enter valid credentials "standard_user" and "secret_sauce"
    Then I should be redirected to the products page with title "Products"