@Regression @Authorization
Feature: Testing authorization of user roles and groups

  Background:
    Given Launch the browser

  Scenario: Login as the provided user and verify the dashboard url according to the user
    Then Enter the username and password
    And Validate the dashboard URL according to the logged in user

  Scenario: Login as the provided user and verify the page section displayed according to the user
    Then Enter the username and password
    And Validate the page section display according to the user

  Scenario: Login as the provided user and try to alter through API and verify the response
    Then Login through the API
    And Validate the site accessibility of the dashboard after altering the api